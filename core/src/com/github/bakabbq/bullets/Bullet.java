package com.github.bakabbq.bullets;

import com.badlogic.gdx.graphics.g2d.Sprite;
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
        setSprite();
    }

    public int getAngleFix(){
        return bd.angleFix;
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

    public int getXOffset(){return bd.xOffset;}
    public int getYOffset(){return bd.yOffset;}

    public int getOriginX(){
        if(bd.origin_x == -1)
            return getTexture().getRegionWidth() / 2;
        return bd.origin_x;
    }

    public int getOriginY(){
        if(bd.origin_y == -1)
            return getTexture().getRegionHeight() / 2;
        return bd.origin_y;
    }

    public void setSpeed(float angle, float speed){
        float forceAngle = (angle + 270f) / 180f * (float)Math.PI;
        body.applyLinearImpulse(MathUtils.cos(forceAngle) * speed, MathUtils.sin(forceAngle) * speed, body.getPosition().x, body.getPosition().y, true);
    }

    public void setSpeed(float speed){
        float forceAngle = (body.getAngle() + 270f) / 180f * (float)Math.PI;
        body.applyLinearImpulse(MathUtils.cos(forceAngle) * speed, MathUtils.sin(forceAngle) * speed, body.getPosition().x, body.getPosition().y, true);
    }

    public void update(){
        sprite.setPosition(body.getPosition().x, body.getPosition().y);
    }

    public Sprite getSprite(){
        return sprite;
    }

    public int getAlpha(){
        return bd.alpha;
    }

    public Sprite sprite;
    public void setSprite(){
        sprite = new Sprite(bd.texture);
        sprite.setOrigin(bd.texture.getRegionWidth()/2,bd.texture.getRegionHeight()/2);
        sprite.setRotation(180);
        bd.modifySprite(sprite);
    }
}
