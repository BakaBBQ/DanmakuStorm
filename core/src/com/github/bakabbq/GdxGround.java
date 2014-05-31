package com.github.bakabbq;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.github.bakabbq.bullets.*;
import com.github.bakabbq.shooters.BulletShooter;
import com.github.bakabbq.shooters.DebugShooter;
import com.github.bakabbq.shooters.EnemyShooter;
import com.github.bakabbq.shooters.players.DanmakuOption;
import com.github.bakabbq.shooters.players.DanmakuPlayer;

public class GdxGround extends ApplicationAdapter {
    SpriteBatch background;
	SpriteBatch batch;
    SpriteBatch ui;
    Texture bulletImage;
    TextureRegion majong;
    TextureRegion circularBullet;
    public World world;
    OrthographicCamera camera;

    DanmakuPlayer player;

    TextureRegion[] enemyFrames;

    ModelBatch modelBatch;
    Model model;
    ModelInstance modelInstance;
    PerspectiveCamera imc;

    ParticleEffectPool particlePool;
    public Environment environment;

    Texture menuBackground;


    Array<Bullet> bullets = new Array<Bullet>(){};
    Array<PlayerBullet> playerBullets = new Array(){};
    Array<BulletShooter> shooters = new Array<BulletShooter>(){};
    Array<ParticleEffectPool.PooledEffect> effects = new Array();
    Array<EnemyShooter> enemies = new Array();
    BulletCollisionListener collisionListener;

    Texture backgroundImage;
	@Override
	public void create () {
        batch = new SpriteBatch();
        background = new SpriteBatch();
        ui = new SpriteBatch();
        bulletImage = new Texture(Gdx.files.internal("bullets/bullet1.png"));
        majong = new TextureRegion(bulletImage,0,112,16,16);
        circularBullet = new TextureRegion(bulletImage,0,32,16,16);
        menuBackground = new Texture(Gdx.files.internal("menus/menuBackground.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);
        camera.position.x -= 128;
        camera.position.y -= 15;
        camera.update();

        backgroundImage = new Texture(Gdx.files.internal("backgrounds/stg6bg.png"));

        modelBatch = new ModelBatch();
        world = new World(new Vector2(0,0), true);
        create_player_body();

        imc = new PerspectiveCamera(67,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        imc.position.set(10f, 10f, 10f);
        imc.lookAt(0, 0, 0);
        imc.near = 1f;
        imc.far = 300f;
        imc.update();

        player = new DanmakuPlayer(this);

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(5f, 5f, 5f,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        modelInstance = new ModelInstance(model);


        collisionListener = new BulletCollisionListener();
        world.setContactListener(collisionListener);

        for(int i = 0; i < 30 ; i ++){
            addBullet(Bullet.debugBullet, 200f, 200f, i * 12).setSpeed(10000 * 2);
        }
        //addShooter(new DebugShooter(this), 200, 200);

	}


    BodyDef playerDef = new BodyDef();
    Body playerBody ;
    private void create_player_body(){
/*
        playerDef.type = BodyDef.BodyType.DynamicBody;
        playerDef.position.set(0,0);
        playerBody = world.createBody(playerDef);
        playerBody.setLinearDamping(40f);
        CircleShape circle = new CircleShape();
        circle.setRadius(0.1f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.4f;
        Fixture fixture = playerBody.createFixture(fixtureDef);
        circle.dispose();
 */
    }
	@Override
	public void render () {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*
        modelBatch.begin(imc);
        modelBatch.render(modelInstance,environment);
        modelBatch.end();
         */


        /*
        background.begin();
        //background.draw(backgroundImage,0,0);
        background.end();
         */

        batch.setProjectionMatrix(camera.combined);
        batch.begin();



        batch.draw(player.getTexture(), player.getX() - 8, player.getY() - 16);
        //batch.draw(majong, player.getX(), player.getY());
        Color c = batch.getColor();
        for(Bullet singleBullet : bullets){

            batch.setColor(c.r, c.g, c.b, ((float) singleBullet.getAlpha()) / 255f);
            batch.draw(
                    singleBullet.getTexture(),
                    singleBullet.getX(),
                    singleBullet.getY(),
                    singleBullet.getTexture().getRegionWidth() / 2,
                    singleBullet.getTexture().getRegionHeight() / 2,
                    singleBullet.getTexture().getRegionWidth(),
                    singleBullet.getTexture().getRegionHeight(),
                    1f,
                    1f,
                    singleBullet.body.getAngle() - 180 + singleBullet.getAngleFix()
            );

            batch.draw(majong, singleBullet.getX(), singleBullet.getY());

            //singleBullet.getSprite().draw(batch);

        }
        batch.setColor(c.r, c.g, c.b, 1);

        for(DanmakuOption singleOption : player.options){
            batch.draw(
                    singleOption.texture,
                    singleOption.x,
                    singleOption.y,
                    singleOption.texture.getRegionWidth() / 2,
                    singleOption.texture.getRegionWidth() / 2,
                    singleOption.texture.getRegionWidth(),
                    singleOption.texture.getRegionHeight(),
                    1f,
                    1f,
                    singleOption.angle
                    );
        }

        for(EnemyShooter singleEnemy : enemies){

        }




        batch.end();

        ui.begin();
        ui.draw(menuBackground, 0, 0);
        ui.end();

        world.step(1/60f, 6, 2);
        if(collisionListener.goBack){
            playerBody.setTransform(320,240,100);
            collisionListener.goBack = false;
        }
        //playerMovement();
        for(DanmakuOption singleOption : player.options){
            singleOption.update();
        }
        player.update();
        updateShooters();
        removeGarbageBullets();


	}

    private void removeGarbageBullets(){
        for (Bullet singleBullet : bullets){
            singleBullet.update();
            if(singleBullet.getX() > 700 || singleBullet.getX() < -100 || singleBullet.getY() > 580 || singleBullet.getY() < -100)
                destroyBullet(singleBullet);
        }
    }

    private void updateShooters(){
        for (BulletShooter st : shooters){
            st.update();
        }
    }


    float MAX_VELOCITY = 100f;
    private void playerMovement(){
        /*
        Vector2 vel = this.playerBody.getLinearVelocity();
        Vector2 pos = this.playerBody.getPosition();


        //slow mode when pressing shift
        float generalV = 10f;
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            generalV = 1.4f;
        }
        //this.playerBody.setLinearVelocity(0f,0f);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && vel.x > -MAX_VELOCITY) {
            //this.playerBody.setLinearVelocity(-generalV, 0.0f);
            this.playerBody.applyLinearImpulse(-generalV, 0, pos.x, pos.y, true);
            //this.playerBody.setLinearVelocity(-0.80f,0f);
        }

// apply right impulse, but only if max velocity is not reached yet
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && vel.x < MAX_VELOCITY) {
            this.playerBody.applyLinearImpulse(generalV, 0, pos.x, pos.y, true);
            //this.playerBody.setLinearVelocity(generalV, 0.0f);
            //this.playerBody.setLinearVelocity(0.80f,0f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && vel.x < MAX_VELOCITY) {
            this.playerBody.applyLinearImpulse(0, generalV, pos.x, pos.y, true);
            //this.playerBody.setLinearVelocity(0.0f, generalV);
            //this.playerBody.setLinearVelocity(0.80f,0f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && vel.x < MAX_VELOCITY) {
            //this.playerBody.setLinearVelocity(0.0f, -generalV);
            this.playerBody.applyLinearImpulse(0, -generalV, pos.x, pos.y, true);
            //this.playerBody.setLinearVelocity(0.80f,0f);
        }
         */
    }




    /*
    private Body addBullet(float x, float y, float angle){
        BodyDef bulletDef = new BodyDef();
        bulletDef.type = BodyDef.BodyType.DynamicBody;
        bulletDef.position.set(x,y);
        Body bulletBody;
        bulletBody = world.createBody(bulletDef);
        FixtureDef bulletFixture = new FixtureDef();
        bulletFixture.density = 0.7f;
        bulletFixture.friction = 0.0f;
        //bulletFixture.restitution = 0.8f;
        bulletFixture.filter.groupIndex = -1;
        CircleShape shape = new CircleShape();
        shape.setRadius(8.0f);
        bulletFixture.shape = shape;
        shape.dispose();
        Fixture fixture = bulletBody.createFixture(bulletFixture);
        bulletBody.setTransform(bulletBody.getPosition().x,bulletBody.getPosition().y,angle);
        //bulletBody.applyLinearImpulse(0,-16.0f,bulletBody.getPosition().x,bulletBody.getPosition().y,true);
        float forceAngle = (bulletBody.getAngle() + 270f) / 180f * (float)Math.PI;
        int speed = 10000 * 10;
        bulletBody.applyLinearImpulse(MathUtils.cos(forceAngle) * speed, MathUtils.sin(forceAngle) * speed, bulletBody.getPosition().x, bulletBody.getPosition().y, true);
        bulletBodies.add(bulletBody);
        return bulletBody;
    }
     */

    public Bullet addBullet(BulletDef bd, float x, float y, float angle){
        Bullet bullet;
        bullet = new Bullet(bd, world, x, y, angle);
        bullets.add(bullet);
        return bullet;
    }

    public PlayerBullet addPlayerBullet(BulletDef bd, float x, float y, float angle){
        PlayerBullet bullet;
        bullet = new PlayerBullet(bd, world, x, y, angle);
        bullets.add(bullet);
        return bullet;
    }

    public BulletShooter addShooter(BulletShooter bs){
        shooters.add(bs);
        return bs;
    }

    public BulletShooter addShooter(BulletShooter bs, float x, float y){
        bs.x = x;
        bs.y = y;
        shooters.add(bs);
        return bs;
    }

    public EnemyShooter addEnemy(EnemyShooter es){
        enemies.add(es);
        return es;
    }

    public void destroyBullet(Bullet b){
        world.destroyBody(b.body);
        bullets.removeValue(b, false);
    }
}
