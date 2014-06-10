package com.github.bakabbq;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.github.bakabbq.bullets.Bullet;
import com.github.bakabbq.bullets.BulletDef;
import com.github.bakabbq.bullets.PlayerBullet;
import com.github.bakabbq.shooters.BulletShooter;
import com.github.bakabbq.shooters.DebugShooter;
import com.github.bakabbq.shooters.EnemyShooter;
import com.github.bakabbq.shooters.players.DanmakuOption;
import com.github.bakabbq.shooters.players.DanmakuPlayer;

public class GdxGround extends ApplicationAdapter {
    public World world;
    public Environment environment;
    SpriteBatch background;
    SpriteBatch batch;
    SpriteBatch ui;
    Texture bulletImage;
    TextureRegion majong;
    TextureRegion circularBullet;
    FPSLogger logger = new FPSLogger();
    OrthographicCamera camera;
    DanmakuPlayer player;
    TextureRegion[] enemyFrames;
    ModelBatch modelBatch;
    Model model;
    ModelInstance modelInstance;
    PerspectiveCamera imc;
    ParticleEffectPool particlePool;
    Texture menuBackground;
    Array<Bullet> bullets = new Array<Bullet>() {
    };
    Array<PlayerBullet> playerBullets = new Array() {
    };
    Array<BulletShooter> shooters = new Array<BulletShooter>() {
    };
    Array<ParticleEffectPool.PooledEffect> effects = new Array();
    Array<EnemyShooter> enemies = new Array();
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
        menuBackground = new Texture(Gdx.files.internal("menus/menuBackground.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);
        camera.zoom = 0.2f;
        camera.position.x -= 320 - 320 / 5;
        camera.position.y -= 240 - 240 / 5;
        camera.update();


        backgroundImage = new Texture(Gdx.files.internal("backgrounds/stg6bg.png"));

        modelBatch = new ModelBatch();
        world = new World(new Vector2(0, 0), true);
        create_player_body();

        imc = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        imc.position.set(10f, 10f, 10f);
        //imc.lookAt(0, 0, 0);
        imc.near = 1f;
        imc.far = 300f;
        imc.update();

        CameraGroupStrategy cameraGroupStrategy = new CameraGroupStrategy(imc);


        decalBatch = new DecalBatch(cameraGroupStrategy);
        decal = Decal.newDecal(circularBullet, true);
        decal.setPosition(0, 0, 0);
        decal.setScale(1f);


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

        for (int i = 0; i < 30; i++) {
            addBullet(Bullet.debugBullet, 200f, 200f, i * 12).setSpeed(10000 * 2);
        }
        addShooter(new DebugShooter(this), 200, 200);


        for(int k = 0; k < 50; k++){
            addEnemy(new EnemyShooter(this),20 + k * 100, 100);
        }

    }

    private void create_player_body() {
    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        imc.update();

        decal.lookAt(imc.position, imc.up);
        decalBatch.add(decal);
        decalBatch.flush();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (EnemyShooter singleEnemy : enemies) {
            //batch.draw(majong, singleEnemy.getX(), singleEnemy.getY());
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


        batch.end();

        ui.begin();
        //ui.draw(menuBackground, 0, 0);
        ui.end();

        world.step(1 / 60f, 6, 2);
        if (collisionListener.goBack) {
            playerBody.setTransform(320, 240, 100);
            collisionListener.goBack = false;
        }
        //playerMovement();
        for (DanmakuOption singleOption : player.options) {
            singleOption.update();
        }
        player.update();
        updateShooters();
        removeGarbageBullets();


    }

    private void removeGarbageBullets() {
        for (Bullet singleBullet : bullets) {
            singleBullet.update();
            if (singleBullet.getX() > 700 || singleBullet.getX() < -100 || singleBullet.getY() > 580 || singleBullet.getY() < -100)
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


    public Bullet addBullet(BulletDef bd, float x, float y, float angle) {
        Bullet bullet;
        bullet = new Bullet(bd, world, x, y, angle);
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

    public void destroyBullet(Bullet b) {
        world.destroyBody(b.body);
        bullets.removeValue(b, false);
    }
}
