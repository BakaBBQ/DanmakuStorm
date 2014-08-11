package com.github.bakabbq.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.github.bakabbq.*;
import com.github.bakabbq.audio.AudioBank;
import com.github.bakabbq.audio.MusicBox;
import com.github.bakabbq.background.ThBackground;
import com.github.bakabbq.bullets.Bullet;
import com.github.bakabbq.bullets.BulletDef;
import com.github.bakabbq.bullets.Laser;
import com.github.bakabbq.bullets.PlayerBullet;
import com.github.bakabbq.effects.BossEffects;
import com.github.bakabbq.effects.ThEffect;
import com.github.bakabbq.shooters.BulletShooter;
import com.github.bakabbq.shooters.EnemyShooter;
import com.github.bakabbq.shooters.bosses.ThBoss;
import com.github.bakabbq.shooters.bosses.kanako.BossKanako;
import com.github.bakabbq.shooters.players.DanmakuOption;
import com.github.bakabbq.shooters.players.DanmakuPlayer;
import datas.FontBank;
import datas.ScreenshotTaker;

import java.util.Date;

/**
 * Created by LBQ on 7/14/14.
 *
 * PracticeScreen - The Main Screen of The Game
 *  initialized with a scene to practice
 */
public class PracticeScreen implements Screen, IDanmakuWorld{

    //the code definitely looks ugly down there
    DanmakuGame game; // game object, essential for rendering
    DanmakuScene scene; // the scene, tool class


    Array<Bullet> bullets; // array containing all bullets
    Array<Laser> lasers; // lasers

    Array<ThBoss> bosses; // boss array
    Array<EnemyShooter> enemies; //enemies, for possible slave spawns
    Array<BulletShooter> shooters;

    Array<ThEffect> effects; //effects array

    Array<DanmakuPlayer> players;


    //Ui Components
    TextureRegion menuBackground;

    //Audio Components
    MusicBox musicBox;

    //Background SpriteBatch
    SpriteBatch backgroundBatch;

    //Background stuff
    ThBackground background;



    //Box2d stuffs
    public World world;
    public BulletCollisionListener collisionListener;


    //Particle Effect Stuffs
    ParticleEffectPool particleEffectPool;
    Array<ParticleEffectPool.PooledEffect> pooledEffects = new Array();

    //BossEffect - yeah.. stupid ThEffect
    BossEffects bossEffects;

    Date gameTimer;




    public PracticeScreen(DanmakuGame game, DanmakuScene scene){
        this.game = game;
        this.scene = scene;


        backgroundBatch = new SpriteBatch();
        initUiContainer();
        initObjectContainers();
        loadUiComponents();
        initAudioComponents();
        initScene();
        initParticle();
        setupZoom();
    }

    MainUiRenderer uiRenderer;
    void initUiContainer(){
        uiRenderer = new MainUiRenderer();
    }

    void initParticle(){
        ParticleEffect slaveEffect = new ParticleEffect();
        slaveEffect.load(Gdx.files.internal("particles/bullet_slave"), Gdx.files.internal("particles"));
        particleEffectPool = new ParticleEffectPool(slaveEffect, 1, 2);
    }



    DebugValues debugValues;
    void initObjectContainers(){
        bullets = new Array<Bullet>();
        lasers = new Array<Laser>();

        debugValues = new DebugValues();

        bosses = new Array<ThBoss>();
        enemies = new Array<EnemyShooter>();

        effects = new Array<ThEffect>();

        players = new Array<DanmakuPlayer>();

        shooters = new Array<BulletShooter>();
    }

    void loadUiComponents(){
        menuBackground = new TextureRegion(new Texture(Gdx.files.internal("menus/front.png")));
    }

    ThBoss boss;
    void initScene(){
        world = new World(new Vector2(0,0), true);
        collisionListener = new BulletCollisionListener();
        world.setContactListener(collisionListener);

        boss = new BossKanako(this);
        boss.setX(237/10);
        boss.setY(62);
        bosses.add(boss);

        background = new ThBackground(this);
        bossEffects = new BossEffects();


        DanmakuPlayer player = new DanmakuPlayer(this);
        player.setPos(237/10,30/5);
        players.add(player);
    }

    void initAudioComponents(){
        musicBox = new MusicBox(this);
    }

    /*
        Box2d can never have quick objects, so i HAVE TO USE A STUPID WAY - use the camera to zoom, i really hate this...
     */
    void setupZoom(){
        game.camera.zoom = 0.2f;

        // Magic
        game.camera.position.x -= 320 - 320 / 5 + 128 / 5 - 10;
        game.camera.position.y -= 240 - 240 / 5 + (480 - 462) / 5;

        game.camera.update();
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        long start;
		Gdx.gl.glViewport((int) game.viewport.x, (int) game.viewport.y,
						  (int) game.viewport.width, (int) game.viewport.height);
        start = System.currentTimeMillis();
        backgroundBatch.begin();
        background.update(backgroundBatch);
        bossEffects.update(boss,backgroundBatch);
        backgroundBatch.end();
        game.batch.begin();

        renderShooters();
        renderEffects();
        renderBullets();


        game.batch.end();

		//Gdx.gl.glViewport((int) game.viewport.x, (int) game.viewport.y,
						  //(int) game.viewport.width, (int) game.viewport.height);
        game.uiBatch.begin();
        //bossEffects.drawHpBar(bosses.first(),game.uiBatch);
        renderUI();

        game.uiBatch.end();
        debugValues.renderInterval = System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        update();

        world.step(1 / 60f, 6, 2);
        debugValues.updateInterval = System.currentTimeMillis() - start;

    }


    void renderShooters(){

        //there should really be some kind of method called getBatch, shouldn't there=-=
        for (EnemyShooter singleEnemy : enemies) {
            if(singleEnemy.isSlave()){
                createEffect(singleEnemy.getX() + 5, singleEnemy.getY() + 6);
            } else {
                game.batch.draw(
                        singleEnemy.texture,
                        singleEnemy.getX() + 5,
                        singleEnemy.getY() + 6,
                        0,
                        0,
                        singleEnemy.texture.getRegionWidth(),
                        singleEnemy.texture.getRegionHeight(),
                        0.2f,
                        0.2f,
                        0
                );

            }
            singleEnemy.update();
        }

        for (DanmakuPlayer singlePlayer : players){
            game.batch.draw(
                    singlePlayer.getTexture(),
                    singlePlayer.getX() - 8,
                    singlePlayer.getY() - 16,
                    singlePlayer.getTexture().getRegionWidth() / 2,
                    singlePlayer.getTexture().getRegionHeight() / 2,
                    singlePlayer.getTexture().getRegionWidth(),
                    singlePlayer.getTexture().getRegionHeight(),
                    0.2f,
                    0.2f,
                    0

            );

            for (DanmakuOption singleOption : singlePlayer.options) {
                game.batch.draw(
                        singleOption.texture,
                        singleOption.x,
                        singleOption.y,
                        singleOption.texture.getRegionWidth() / 2,
                        singleOption.texture.getRegionHeight() / 2,
                        singleOption.texture.getRegionWidth(),
                        singleOption.texture.getRegionHeight(),
                        0.2f,
                        0.2f,
                        singleOption.angle
                );
            }
        }



        for (ThBoss singleBoss : bosses) {
            game.batch.draw(
                    singleBoss.texture,
                    singleBoss.getX() + 2,
                    singleBoss.getY() + 3,
                    0,
                    0,
                    singleBoss.getTexture().getRegionWidth(),
                    singleBoss.getTexture().getRegionHeight(),
                    0.2f,
                    0.2f,
                    0
            );
        }
    }

    void renderEffects(){
        Color c;
        c = game.batch.getColor();
        for (ThEffect singleEffect : effects) {
            //Gdx.app.log("Effect", "RegionWidth " + singleEffect.texture.getRegionWidth() + " RegionHeight " + singleEffect.texture.getRegionHeight());
            game.batch.setColor(c.r, c.g, c.b, singleEffect.opacity);
            game.batch.draw(
                    singleEffect.texture,
                    singleEffect.x + singleEffect.getXOffset() ,
                    singleEffect.y + singleEffect.getYOffset(),
                    singleEffect.texture.getRegionWidth() / 2 * singleEffect.zoomX,
                    singleEffect.texture.getRegionHeight() / 2 * singleEffect.zoomY,
                    singleEffect.texture.getRegionWidth() * singleEffect.zoomX,
                    singleEffect.texture.getRegionHeight() * singleEffect.zoomY,
                    0.2f,
                    0.2f,
                    singleEffect.angle
            );
        }
        game.batch.setColor(c.r, c.g, c.b, 1);
    }

    void renderBullets(){
        Color c = game.batch.getColor();
        for (Bullet singleBullet : bullets) {

            game.batch.setColor(c.r, c.g, c.b, ((float) singleBullet.getAlpha()) / 255f);
            game.batch.draw(
                    singleBullet.getTexture(),
                    singleBullet.getX() + singleBullet.getXOffset(),
                    singleBullet.getY() + singleBullet.getYOffset(),
                    singleBullet.getOriginX(),
                    singleBullet.getOriginY(),
                    singleBullet.getTexture().getRegionWidth(),
                    singleBullet.getTexture().getRegionHeight(),
                    0.2f,
                    0.2f,
                    singleBullet.body.getAngle() - 180 + singleBullet.getAngleFix()
            );


        }
        game.batch.setColor(c.r, c.g, c.b, 1);
    }

    void renderParticles(float delta){
        for (ParticleEffectPool.PooledEffect singleEffect : pooledEffects){
            singleEffect.draw(game.batch,delta);
            if(singleEffect.isComplete()){
                singleEffect.free();
                pooledEffects.removeValue(singleEffect,true);
            }
        }
    }

    void renderUI(){
        uiRenderer.render(game.uiBatch);
      //  getFontBank().arial.draw(game.uiBatch,"1 / 15", 100, 100);
        renderFps();
    }

    void renderFps(){
        int fps = Gdx.graphics.getFramesPerSecond();
        String debug = "Fps: " + fps + "\n"+
                        "Bullets: " + bullets.size + "\n" +
                       "RenderInterval: " + debugValues.renderInterval + "\n" +
                       "UpdateInterval: " + debugValues.updateInterval + "\n" +
                       "OverFlow?: " + (debugValues.renderInterval + debugValues.updateInterval > 16);
        //getFontBank().arial.draw(game.uiBatch,debug, 450 , 80);
        getFontBank().arial.drawMultiLine(game.uiBatch,debug, 450 , 160);
    }

    public FontBank getFontBank(){
        return game.fontBank;
    }



    // Called Once Per Frame
    public void update(){
        for (Bullet singleBullet : bullets) {
            singleBullet.update();
            if (singleBullet.destroyFlag) {
                singleBullet.dispose();
                bullets.removeValue(singleBullet, true);
            }
        }

        for (EnemyShooter singleEnemy : enemies) {
            if (singleEnemy.dead) {
                //addExplosion(singleEnemy.getX(), singleEnemy.getY());
                singleEnemy.dispose();
                enemies.removeValue(singleEnemy, true);

            }
        }

        for (ThBoss singleBoss : bosses) {
            singleBoss.update();
        }

        for (DanmakuPlayer singlePlayer : players){
            singlePlayer.update();
            for (DanmakuOption singleOption : singlePlayer.options) {
                singleOption.update();
            }
        }

        for (ThEffect singleEffect : effects) {
            singleEffect.update();
            if (singleEffect.disposeFlag)
                effects.removeValue(singleEffect, true);
        }
		removeGarbageBullets();



        updateScreenshot();

    }

    /**
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     */
    @Override
    public void show() {

    }

    /**
     */
    @Override
    public void hide() {

    }

    /**
     */
    @Override
    public void pause() {

    }

    /**
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }

    public void addItem(float x, float y) {
        return;
    }

    public BulletShooter addShooter(BulletShooter bs, float x, float y) {
        bs.x = x;
        bs.y = y;
        shooters.add(bs);
        return bs;
    }

    public EnemyShooter addEnemy(EnemyShooter es) {
        enemies.add(es);
        return es;
    }

    public EnemyShooter addEnemy(EnemyShooter es, float x, float y) {
        es.setX(x);
        es.setY(y);
        enemies.add(es);
        return es;
    }

    public ThBoss spawnBoss(ThBoss boss, float x, float y) {
        boss.setX(x);
        boss.setY(y);
        bosses.add(boss);
        return boss;
    }

    public void destroyBullet(Bullet b) {
        world.destroyBody(b.body);
        bullets.removeValue(b, false);
    }

    public void addEffect(ThEffect effect, float x, float y) {
        effect.x = x;
        effect.y = y;
        effects.add(effect);
    }

    public void clearEffect(ThEffect effect) {
        for (ThEffect singleEffect : effects) {
            if (singleEffect.getClass() == effect.getClass()) {
                singleEffect.enterDispose();
            }
        }
    }

    public void addLaser(Laser laser){
        lasers.add(laser);
    }

    public Bullet addBullet(BulletDef bd, float x, float y, float angle) {
        Bullet bullet;
        bullet = new Bullet(bd, world, x, y, angle);
        bullet.danmakuWorld = this;
        //addEffect(new BulletCreationEffect(), x, y);
        bullets.add(bullet);
        return bullet;
    }

    public PlayerBullet addPlayerBullet(BulletDef bd, float x, float y, float angle) {
        PlayerBullet bullet;
        bullet = new PlayerBullet(bd, world, x, y, angle);
        musicBox.playSe(AudioBank.bulletAppear);
        bullets.add(bullet);
        return bullet;
    }

    public BulletShooter addShooter(BulletShooter bs) {
        shooters.add(bs);
        return bs;
    }

    public void updateScreenshot(){
        if(Gdx.input.isKeyPressed(Input.Keys.P))
            ScreenshotTaker.saveScreenshot();
    }

    void createEffect(float x, float y){
        ParticleEffectPool.PooledEffect effect = particleEffectPool.obtain();
        effect.setPosition(100, 100);
        pooledEffects.add(effect);
    }



    @Override
    public World getWorld() {
        return world;
    }

    public DanmakuPlayer getPlayer(){
        return players.first();
    }


    //returns the timerFlow per frame, depends on grazing... : in seconds
    public int getTimeFlowFrames(){
        return 1 + getPlayer().grazeCnt / 70;
    }
	
	private void removeGarbageBullets() {
        for (Bullet singleBullet : bullets) {
            singleBullet.update();
            if (singleBullet.getX() > 350 / 5 || singleBullet.getX() < -50 / 3 || singleBullet.getY() > 500 / 5 || singleBullet.getY() < -60 / 5)
                destroyBullet(singleBullet);
        }
    }


}
