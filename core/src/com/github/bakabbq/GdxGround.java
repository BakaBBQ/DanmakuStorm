package com.github.bakabbq;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.github.bakabbq.bullets.Bullet;
import com.github.bakabbq.bullets.BulletDef;
import com.github.bakabbq.bullets.Laser;
import com.github.bakabbq.bullets.PlayerBullet;
import com.github.bakabbq.effects.BulletCreationEffect;
import com.github.bakabbq.effects.ExplosionEffect;
import com.github.bakabbq.effects.ThEffect;
import com.github.bakabbq.items.ThItem;
import com.github.bakabbq.shooters.BulletShooter;
import com.github.bakabbq.shooters.EnemyShooter;
import com.github.bakabbq.shooters.bosses.ThBoss;
import com.github.bakabbq.shooters.bosses.testsanae.TestSanae;
import com.github.bakabbq.shooters.players.DanmakuOption;
import com.github.bakabbq.shooters.players.DanmakuPlayer;
import com.github.bakabbq.shooters.players.IControlHelper;
import com.github.bakabbq.shooters.players.PlayerGrazeCounter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;





// GdxGround, more like a playground, huh?
public class GdxGround extends ApplicationAdapter implements IDanmakuWorld{
    public World world;
    public Environment environment;
    public SpriteBatch background;
    SpriteBatch batch;

    SpriteBatch ui;
    Texture bulletImage;
    TextureRegion majong;
    TextureRegion circularBullet;
    FPSLogger logger = new FPSLogger();
    OrthographicCamera camera;
    public DanmakuPlayer player;
    TextureRegion[] enemyFrames;
    ModelBatch modelBatch;
    Model model;
    ModelInstance modelInstance;
    PerspectiveCamera imc;
    ParticleEffectPool particlePool;
    Texture menuBackground;
    BitmapFont fontMincho;
    BitmapFont fontSavoye;

    PlayerGrazeCounter grazeCounter;
    ShapeRenderer shapeRenderer;

    ImmediateModeRenderer renderer;

    public IControlHelper controlHelper;

    Texture test;


    Array<Bullet> bullets = new Array<Bullet>() {
    };
    Array<PlayerBullet> playerBullets = new Array() {
    };
    Array<BulletShooter> shooters = new Array<BulletShooter>() {
    };
    Array<ThItem> items = new Array();
    Array<EnemyShooter> enemies = new Array();
    Array<ThBoss> bosses = new Array();
    Array<ThEffect> effects = new Array();
    Array<ExplosionEffect> explosionEffects = new Array();
    Array<Laser> lasers = new Array<Laser>();


    BulletCollisionListener collisionListener;
    Texture backgroundImage;
    BodyDef playerDef = new BodyDef();
    Body playerBody;
    float MAX_VELOCITY = 100f;
    private Decal decal;
    private DecalBatch decalBatch;


    @Override
    public void create() {

        batch = new SpriteBatch();
        background = new SpriteBatch();
        ui = new SpriteBatch();
        bulletImage = new Texture(Gdx.files.internal("bullets/bullet1.png"));
        majong = new TextureRegion(bulletImage, 0, 112, 16, 16);
        circularBullet = new TextureRegion(bulletImage, 0, 32, 16, 16);
        menuBackground = new Texture(Gdx.files.internal("menus/front.png"));

        setupShader();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);

        camera.zoom = 0.2f;
        //magic
        camera.position.x -= 320 - 320 / 5 + 128 / 5 - 10;
        camera.position.y -= 240 - 240 / 5 + (480 - 462) / 5;
        camera.update();


        fontMincho = new BitmapFont(Gdx.files.internal("fonts/YuMincho.fnt"));
        fontSavoye = new BitmapFont(Gdx.files.internal("fonts/savoye_let.fnt"));


        backgroundImage = new Texture(Gdx.files.internal("backgrounds/stg6bg.png"));

        modelBatch = new ModelBatch();
        world = new World(new Vector2(0, 0), true);
        create_player_body();

        imc = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        imc.position.set(10f, 10f, 10f);
        imc.near = 1f;
        imc.far = 300f;
        imc.update();

        CameraGroupStrategy cameraGroupStrategy = new CameraGroupStrategy(imc);


        decalBatch = new DecalBatch(cameraGroupStrategy);
        decal = Decal.newDecal(circularBullet, true);
        decal.setPosition(0, 0, 0);
        decal.setScale(1f);


        player = new DanmakuPlayer(this);
        grazeCounter = new PlayerGrazeCounter(player);

        test = new Texture(Gdx.files.internal("backgrounds/stage01a.png"));


        collisionListener = new BulletCollisionListener();
        world.setContactListener(collisionListener);
        shapeRenderer = new ShapeRenderer();
        for (int i = 0; i < 30; i++) {
            addBullet(Bullet.debugBullet, 200f, 200f, i * 12).setSpeed(10000 * 2);
        }

        TestSanae sanae = new TestSanae(this);
        spawnBoss(sanae, 30, 30);

        renderer = new ImmediateModeRenderer20(false, true, 1);


        //addLaser(new Laser(180, 0, sanae));
    }

    private void create_player_body() {
    }

    ShaderProgram shader;
    void setupShader(){
        ShaderProgram.pedantic = false;
        shader = new ShaderProgram(
                Gdx.files.internal("testshaders/test.vert").readString(),
                Gdx.files.internal("testshaders/test.frag").readString()
        );
        if(!shader.isCompiled()) {
            Gdx.app.log("Problem loading shader:", shader.getLog());
        }
        background.setShader(shader);
    }

    @Override
    public void render() {
        camera.update();
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                (int) viewport.width, (int) viewport.height);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        background.begin();
        background.draw(backgroundImage,0,0);
        background.end();


        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (EnemyShooter singleEnemy : enemies) {
            batch.draw(
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
            singleEnemy.update();
        }

        batch.draw(
                player.getTexture(),
                player.getX() - 8,
                player.getY() - 16,
                player.getTexture().getRegionWidth() / 2,
                player.getTexture().getRegionHeight() / 2,
                player.getTexture().getRegionWidth(),
                player.getTexture().getRegionHeight(),
                0.2f,
                0.2f,
                0

        );


        for (ThItem item : items) {
            batch.draw(
                    item.texture,
                    item.itemBody.getPosition().x,
                    item.itemBody.getPosition().y,
                    item.texture.getRegionWidth() / 2,
                    item.texture.getRegionHeight() / 2,
                    item.texture.getRegionWidth(),
                    item.texture.getRegionHeight(),
                    0.2f,
                    0.2f,
                    0
            );
        }


        for (ThBoss singleBoss : bosses) {
            batch.draw(
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


        Color c = batch.getColor();
        for (Bullet singleBullet : bullets) {

            batch.setColor(c.r, c.g, c.b, ((float) singleBullet.getAlpha()) / 255f);
            batch.draw(
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
        batch.setColor(c.r, c.g, c.b, 1);

        for (DanmakuOption singleOption : player.options) {
            batch.draw(
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


        for (ExplosionEffect singleEffect : explosionEffects) {

            batch.draw(
                    singleEffect.texture,
                    singleEffect.x,
                    singleEffect.y,
                    singleEffect.texture.getRegionWidth() / 2,
                    singleEffect.texture.getRegionHeight() / 2,
                    singleEffect.texture.getRegionWidth(),
                    singleEffect.texture.getRegionHeight(),
                    0.2f,
                    0.2f,
                    singleEffect.direction
            );
        }

        c = batch.getColor();
        for (ThEffect singleEffect : effects) {
            //Gdx.app.log("Effect", "RegionWidth " + singleEffect.texture.getRegionWidth() + " RegionHeight " + singleEffect.texture.getRegionHeight());
            batch.setColor(c.r, c.g, c.b, singleEffect.opacity);
            batch.draw(
                    singleEffect.texture,
                    singleEffect.x - singleEffect.texture.getRegionWidth() / 2 * singleEffect.zoomX,
                    singleEffect.y - singleEffect.texture.getRegionHeight() / 2 * singleEffect.zoomY,
                    singleEffect.texture.getRegionWidth() / 2 * singleEffect.zoomX,
                    singleEffect.texture.getRegionHeight() / 2 * singleEffect.zoomY,
                    singleEffect.texture.getRegionWidth() * singleEffect.zoomX,
                    singleEffect.texture.getRegionHeight() * singleEffect.zoomY,
                    0.2f,
                    0.2f,
                    singleEffect.angle
            );
        }
        batch.setColor(c.r, c.g, c.b, 1);

        for (Laser singleLaser : lasers){
            //batch.draw(majong, singleLaser.owner.getX(),singleLaser.owner.getY());
        }

        batch.end();



        ui.begin();
        fontSavoye.draw(ui,"" + player.grazeCnt, 40, 40);
        //fontMincho.draw(ui, "靈符「博麗二重大結界」", 50, 50);
        ui.draw(menuBackground, 0, 0);
        ui.end();

        world.step(1 / 60f, 6, 2);
        if (collisionListener.goBack) {
            playerBody.setTransform(320, 240, 100);
            collisionListener.goBack = false;
        }

        for (Bullet singleBullet : bullets) {
            if (singleBullet.destroyFlag) {
                singleBullet.dispose();
                bullets.removeValue(singleBullet, true);
            }
        }

        for (EnemyShooter singleEnemy : enemies) {
            if (singleEnemy.dead) {
                addExplosion(singleEnemy.getX(), singleEnemy.getY());
                singleEnemy.dispose();
                enemies.removeValue(singleEnemy, true);

            }
        }


        for (ThBoss singleBoss : bosses) {
            singleBoss.update();
        }

        for (ExplosionEffect e : explosionEffects) {
            e.update();
            if (e.timer >= 100) {
                explosionEffects.removeValue(e, true);
            }
        }

        //playerMovement();
        for (DanmakuOption singleOption : player.options) {
            singleOption.update();
        }
        player.update();
        updateShooters();
        removeGarbageBullets();

        for (ThEffect singleEffect : effects) {
            singleEffect.update();
            if (singleEffect.disposeFlag)
                effects.removeValue(singleEffect, true);
        }
        grazeCounter.update();


        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1,1,0,1);
        for (Laser singleLaser : lasers){
            shapeRenderer.line(singleLaser.owner.getX(),singleLaser.owner.getY(), singleLaser.tx, singleLaser.ty);
        }
        shapeRenderer.end();


        for(Laser singleLaser : lasers){
            singleLaser.update();
        }
    }

    private void removeGarbageBullets() {
        for (Bullet singleBullet : bullets) {
            singleBullet.update();
            if (singleBullet.getX() > 350 / 5 || singleBullet.getX() < -50 / 5 || singleBullet.getY() > 500 / 5 || singleBullet.getY() < -60 / 5)
                destroyBullet(singleBullet);
        }
    }

    private void updateShooters() {
        for (BulletShooter st : shooters) {
            st.update();
        }
    }

    private void playerMovement() {
    }

    public void progress(float cx, float cy, float r, float thickness, float amt, Color c, Texture lookup) {
        //start and end angles
        float start = 0f;
        float end = amt * 360f;

        lookup.bind();
        
        int segs = (int)(12 * Math.cbrt(r));
        end += 90f;
        start += 90f;
        float halfThick = thickness/2f;
        float step = 360f / segs;
        for (float angle=start; angle<(end+step); angle+=step) {
            float tc = 0.5f;
            if (angle==start)
                tc = 0f;
            else if (angle>=end)
                tc = 1f;

            float fx = MathUtils.cosDeg(angle);
            float fy = MathUtils.sinDeg(angle);

            float z = 0f;
            renderer.color(c.r, c.g, c.b, c.a);
            renderer.texCoord(tc, 1f);
            renderer.vertex(cx + fx * (r + halfThick), cy + fy * (r + halfThick), z);

            renderer.color(c.r, c.g, c.b, c.a);
            renderer.texCoord(tc, 0f);
            renderer.vertex(cx + fx * (r + -halfThick), cy + fy * (r + -halfThick), z);
        }
        renderer.end();
    }


    public Bullet addBullet(BulletDef bd, float x, float y, float angle) {
        Bullet bullet;
        bullet = new Bullet(bd, world, x, y, angle);
        //addEffect(new BulletCreationEffect(), x, y);
        bullets.add(bullet);
        return bullet;
    }

    public PlayerBullet addPlayerBullet(BulletDef bd, float x, float y, float angle) {
        PlayerBullet bullet;
        bullet = new PlayerBullet(bd, world, x, y, angle);
        bullets.add(bullet);
        return bullet;
    }

    public BulletShooter addShooter(BulletShooter bs) {
        shooters.add(bs);
        return bs;
    }

    @Override
    public World getWorld() {
        return this.world;
    }


    private static final int VIRTUAL_WIDTH = 640;
    private static final int VIRTUAL_HEIGHT = 480;
    private static final float ASPECT_RATIO =
            (float) VIRTUAL_WIDTH / (float) VIRTUAL_HEIGHT;

    Rectangle viewport;

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);
        if (aspectRatio > ASPECT_RATIO) {
            scale = (float) height / (float) VIRTUAL_HEIGHT;
            crop.x = (width - VIRTUAL_WIDTH * scale) / 2f;
        } else if (aspectRatio < ASPECT_RATIO) {
            scale = (float) width / (float) VIRTUAL_WIDTH;
            crop.y = (height - VIRTUAL_HEIGHT * scale) / 2f;
        } else {
            scale = (float) width / (float) VIRTUAL_WIDTH;
        }

        float w = (float) VIRTUAL_WIDTH * scale;
        float h = (float) VIRTUAL_HEIGHT * scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
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

    public void addExplosion(float x, float y) {
        ExplosionEffect e = new ExplosionEffect();
        e.x = x;
        e.y = y;
        explosionEffects.add(e);
    }


    public void addItem(float x, float y) {
        ThItem t = new ThItem(this);
        items.add(t);
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

    public DanmakuPlayer getPlayer(){
        return player;
    }

    @Override
    public Array<Bullet> getBullets() {
        return null;
    }
}
