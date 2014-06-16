package com.github.bakabbq.shooters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.github.bakabbq.BulletCollisionListener;
import com.github.bakabbq.GdxGround;

/**
 * Created by LBQ on 5/28/14.
 */
public class EnemyShooter extends BulletShooter {
    public Texture enemySheet = new Texture(Gdx.files.internal("enemy.png"));

    public int colorId;

    public int stateId; //state id: 0 => staying at the same position,  1 => moving left, 2 => moving right
    public int stateTimer; //stateTimer for deciding the sprite

    public Body enemyBody;
    public TextureRegion texture;

    public int textureId;

    public EnemyShooter(GdxGround ground) {
        super(ground);
        this.stateId = 0;
        this.stateTimer = 0;
        setTextureIndexes();
        updateTexture();
        createBody();
    }

    public void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 0);
        enemyBody = ground.world.createBody(bodyDef);
        enemyBody.setLinearDamping(30f);
        CircleShape circle = new CircleShape();
        circle.setRadius(1f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.4f;
        fixtureDef.filter.categoryBits = BulletCollisionListener.ENEMY;
        fixtureDef.filter.maskBits = (short) (BulletCollisionListener.PLAYER_BULLET | BulletCollisionListener.PLAYER);
        Fixture fixture = enemyBody.createFixture(fixtureDef);
        circle.dispose();
    }

    public float getX() {
        return enemyBody.getPosition().x;
    }

    public void setX(float x) {
        enemyBody.setTransform(x,getY(),enemyBody.getAngle());
    }

    public float getY() {
        return enemyBody.getPosition().y;
    }


    public void setY(float y) {
        enemyBody.setTransform(getX(),y,enemyBody.getAngle());
    }

    public void setTextureIndexes() {
        this.colorId = 0;
    }


    public void updateTexture() {
        int frameId;
        frameId = 0;
        switch (stateId) {
            case 0:
                frameId = stateTimer % 4;
                break;
            case 1:
            case 2:
                // no break
                frameId = stateTimer % 4 + 8;
                break;
        }
        TextureRegion result = new TextureRegion(enemySheet, 32 * frameId, 256 + 36 * this.colorId, 32, 32);
        if (stateId == 1)
            result.flip(true, false);
        texture = result;
    }

    public void update() {
        super.update();
        if (this.timer % 5 == 0)
            this.stateTimer += 1;
        updateTexture();
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public int setState(int x) {
        int ori;
        ori = this.stateId;
        this.stateId = x;
        this.stateTimer = 0;
        return ori;
    }
}