package com.github.bakabbq.bullets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by LBQ on 5/27/14.
 */
public class Bullet {
    public static BulletDef debugBullet = new BulletDef();
    public static BulletAmulets amuletBullet = new BulletAmulets();
    public static BulletKunai kunaiBullet = new BulletKunai();
    public static BulletOval ovalBullet = new BulletOval();
    public Body body;
    public BulletDef bd;

    Fixture fixture;
    public Bullet(BulletDef bd, World world, float x, float y, float angle){
        this.bd = bd;
        BodyDef bodydef = new BodyDef();
        bodydef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodydef);
        FixtureDef fd = bd.fixtureD;
        this.fixture = body.createFixture(fd);
        body.setTransform(x, y, angle);
    }



    public TextureRegion getTexture(){
        return bd.texture;
    }

    public float getX(){
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void setSpeed(float angle, float speed){
        float forceAngle = (angle + 270f) / 180f * (float)Math.PI;
        body.applyLinearImpulse(MathUtils.cos(forceAngle) * speed, MathUtils.sin(forceAngle) * speed, body.getPosition().x, body.getPosition().y, true);
    }

    public void setSpeed(float speed){
        float forceAngle = (body.getAngle() + 270f) / 180f * (float)Math.PI;
        body.applyLinearImpulse(MathUtils.cos(forceAngle) * speed, MathUtils.sin(forceAngle) * speed, body.getPosition().x, body.getPosition().y, true);
    }
}
