package com.github.bakabbq.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.github.bakabbq.BulletCollisionListener;
import com.github.bakabbq.shooters.BulletShooter;

/**
 * Created by LBQ on 7/2/14.
 */
public class Laser{
    public float cntDown;
    public BulletShooter owner;
    public TextureRegion texture;
    public Body body;



    float angle;
    int colorIndex;

    public float tx, ty; // Target x and target y
    public Laser(float angle, int colorIndex, BulletShooter owner){

        float rad = ((angle * (float) Math.PI / 180f));
        this.tx = 100 * MathUtils.sin(rad) + owner.getX() ;
        this.ty = -100 * MathUtils.cos(rad) + owner.getY() ;
        this.owner = owner;
        Gdx.app.log("Laser", "tx "+this.tx + " ty " + this.ty);

        initTexture(colorIndex);
        createBody(owner.getX(), owner.getY(), angle);
    }

    public void createBody(float x, float y, float angle){
        World world = owner.ground.world;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x,y);
        this.body = world.createBody(bodyDef);
        body.setUserData(this);
        PolygonShape shape = new PolygonShape();
        Vector2[] vertices = new Vector2[4];

        vertices[0] = new Vector2(0.6f, 0f);
        vertices[1] = new Vector2(-0.6f,0f);
        vertices[2] = new Vector2(-1f, -100f);
        vertices[3] = new Vector2(1f, -100f);
        shape.set(vertices);
        //shape.setAsBox(1,100);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 10f;
        fixtureDef.friction = 0.4f;
        fixtureDef.filter.categoryBits = BulletCollisionListener.ENEMY_BULLET;
        fixtureDef.filter.maskBits = (short)(BulletCollisionListener.PLAYER | 0x001);
        Fixture fixture = body.createFixture(fixtureDef);
        float rad = ((angle * (float) Math.PI / 180f));
        body.setTransform(owner.getX(),owner.getY(),rad);
    }

    public void initTexture(int colorIndex){
        this.texture = new TextureRegion(getMainTexture(), 16 * colorIndex, 0 , 16, 16);
    }

    public Texture getMainTexture(){
        return new Texture(Gdx.files.internal("bullets/bullet1.png"));
    }
    public boolean isActive(){
        return cntDown <= 0;
    }

    public void update(){
        this.cntDown--;
        if (this.cntDown <= 0){
            this.cntDown = 0f;
        }
    }

    public Color getLineColor(){
        return new Color(180f/255f, 0f, 0f, 1);
    }
}
