package com.github.bakabbq.shooters.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.github.bakabbq.BulletCollisionListener;
import com.github.bakabbq.GdxGround;
import com.github.bakabbq.Pixel;
import com.github.bakabbq.bullets.Bullet;
import com.github.bakabbq.bullets.BulletDef;
import com.github.bakabbq.bullets.PlayerBullet;

/**
 * Created by LBQ on 5/28/14.
 */
public class DanmakuPlayer {
    public static Vector2[][] option_positions = new Vector2[][]{
            {new Vector2(0, -20f)},
            {new Vector2(-20, -15f), new Vector2(20, -15f)},
            {new Vector2(-20, -15f), new Vector2(20, -15f), new Vector2(0, -20f)},
            {new Vector2(-20, -15f), new Vector2(20, -15f), new Vector2(-10f, -20f), new Vector2(10f, -20f)},
    };

    public static Vector2[][] optionSlow_positions = new Vector2[][]{
            {new Vector2(0, -15f)},
            {new Vector2(-15, -10f), new Vector2(15, -10f)},
            {new Vector2(-15, -10f), new Vector2(15, -10f), new Vector2(0, -15f)},
            {new Vector2(-15, -10f), new Vector2(15, -10f), new Vector2(-10f, -15f), new Vector2(10f, -15f)},
    };
    private final float MAX_VELOCITY = 100f;
    public Texture textureSheet;
    public TextureRegion[][] slicedSheet;
    public GdxGround ground;
    public Body playerBody;
    public Array<DanmakuOption> options = new Array() {
    };
    public int power;
    public boolean slowMode = false; // this is quite useless since this updates every frame.__.
    int timer;
    int moveState; // 0 => still, 2 => down, 4 => left, 6 => right, 8 => up
    int moveTimer;

    public DanmakuPlayer(GdxGround ground) {
        this.ground = ground;
        timer = 0;
        moveTimer = 0;
        power = 400;
        textureSheet = new Texture(Gdx.files.internal("players/reimu.png"));
        slicedSheet = TextureRegion.split(textureSheet, 32, 48);

        createBody();
        addOption();
        addOption();
        addOption();
        addOption();
    }

    public void addOption() {
        DanmakuOption newOption = new DanmakuOption(this, options.size, getOptionsCnt());
        options.add(newOption);
        newOption.refresh(options.size);
        newOption.stopMoving();
    }

    int setState(int x) {
        if (moveState == x)
            return 0;
        int ori = moveState;
        moveState = x;
        moveTimer = 0;
        return ori;
    }

    private void createBody() {
        BodyDef playerDef = new BodyDef();
        playerDef.type = BodyDef.BodyType.DynamicBody;
        playerDef.position.set(0, 0);
        playerBody = ground.world.createBody(playerDef);
        playerBody.setLinearDamping(40f);
        CircleShape circle = new CircleShape();
        circle.setRadius(0.07f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.4f;
        fixtureDef.filter.categoryBits = BulletCollisionListener.PLAYER;
        fixtureDef.filter.maskBits = (short) (BulletCollisionListener.ENEMY_BULLET | BulletCollisionListener.ENEMY);
        Fixture fixture = playerBody.createFixture(fixtureDef);
        circle.dispose();
    }

    public void update() {
        timer++;
        if (this.timer % 3 == 0)
            moveTimer++;
        updateControls();
        updateShoot();
    }

    public void updateShoot() {
        if (timer % 4 == 0 && Gdx.input.isKeyPressed(Input.Keys.Z))
            for (DanmakuOption singleOption : options)
                singleOption.shoot(PlayerBullet.reimuHoming);

        if (timer % 12 == 0 && Gdx.input.isKeyPressed(Input.Keys.Z))
            shoot(PlayerBullet.reimuAmulet, 0, (int) Pixel.m2p(30));


    }

    public void updateControls() {
        Vector2 vel = this.playerBody.getLinearVelocity();
        Vector2 pos = this.playerBody.getPosition();


        //slow mode when pressing shift
        float generalV = 0.5f;

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            if (slowMode == false)
                onSlowMode();
            generalV = 0.3f;

        } else {
            slowMode = false;
        }
        //this.playerBody.setLinearVelocity(0f,0f);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && vel.x > -MAX_VELOCITY) {
            setState(4);
            this.playerBody.applyLinearImpulse(-generalV, 0, pos.x, pos.y, true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && vel.x < MAX_VELOCITY) {
            setState(6);
            this.playerBody.applyLinearImpulse(generalV, 0, pos.x, pos.y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && vel.x < MAX_VELOCITY) {
            this.playerBody.applyLinearImpulse(0, generalV, pos.x, pos.y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && vel.x < MAX_VELOCITY) {
            this.playerBody.applyLinearImpulse(0, -generalV, pos.x, pos.y, true);
        }

        if (Math.abs(playerBody.getLinearVelocity().x) <= 1 && Math.abs(playerBody.getLinearVelocity().y) <= 1)
            setState(0);
    }


    public int getOptionsCnt() {
        return this.power / 100;
    }

    public void increasePower(int amount) {
        if (this.power + amount >= 500) {
            this.power = 500;
        } else {
            power += amount;
        }
    }


    public float getX() {
        return this.playerBody.getPosition().x;
    }

    public float getY() {
        return this.playerBody.getPosition().y;
    }

    public TextureRegion getTexture() {
        int frame = 0;
        int row = 0;
        switch (moveState) {
            case 4:
                row = 1;
                frame = moveTimer >= 5 ? ((moveTimer % 2) + 5) : moveTimer;
                break;
            case 6:
                row = 2;
                frame = moveTimer >= 5 ? ((moveTimer % 2) + 5) : moveTimer;
                break;
            default:
                row = 0;
                frame = moveTimer % 8;
                break;
        }
        return slicedSheet[row][frame];
    }

    void onSlowMode() {
        slowMode = true;
        for (DanmakuOption singleOption : options) {
            singleOption.refresh(options.size);
        }
    }

    public void shoot(BulletDef bd) {
        ground.addPlayerBullet(bd, getX(), getY(), 180).setSpeed(10000);
    }

    public void shoot(BulletDef bd, int xOff, int yOff) {
        ground.addPlayerBullet(bd, getX() + xOff, getY() + yOff, 180).setSpeed(600);
    }

    public void shoot(BulletDef bd, int xOff, int yOff, int speed) {
        ground.addPlayerBullet(bd, getX() + xOff, getY() + yOff, 180).setSpeed(speed);
    }


}
