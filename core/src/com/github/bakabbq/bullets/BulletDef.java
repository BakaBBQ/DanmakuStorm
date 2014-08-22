package com.github.bakabbq.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.github.bakabbq.BulletCollisionListener;
import com.github.bakabbq.IDanmakuWorld;

/**
 * Created by BBQ on 5/27/14.
 *
 * The Intention of BulletDef is designed for a bullet database
 * after initialization, use fixture for creating bodies and use getTexture() to get the graphics for rendering
 */
public class BulletDef {
    public int textureX = 1;
    public int textureY = 2;
    public int alpha = 255;
    public int angleFix = 0;
    public int origin_x = -1;
    public int origin_y = -1;
    public int xOffset = 0;
    public int yOffset = 0;
    public FixtureDef fixtureD; // the fixture for bullet creation
    public TextureRegion texture;
    public Sprite sprite;

    public int colorId;
    protected Texture bulletSheet = new Texture(Gdx.files.internal("bullets/bullet1.png"));

    public BulletDef(int colorId) {
        this.colorId = colorId;
        fixtureD = new FixtureDef();
        fixtureD.density = 0.7f;
        fixtureD.friction = 0.0f;
        fixtureD.restitution = 3f;
        fixtureD.shape = getShape();
        setTextureIndex();
        updateTexture();
        initCreationTexture();
        postInit();
    }

    public void postInit(){

    }

    public TextureRegion onCreationTexture;
    public void initCreationTexture(){
        int startX;
        startX = colorId * 32;
        onCreationTexture = new TextureRegion(bulletSheet,startX,13 * 16,32,32);
    }

    public void setTextureIndex() {
        textureX = colorId;
        textureY = 2;
    }

    public Shape getShape() {
        CircleShape shape = new CircleShape();
        shape.setRadius(1.6f);
        return shape;
    }

    public TextureRegion updateTexture() {
        TextureRegion tr;
        tr = new TextureRegion(bulletSheet, textureX * 16, textureY * 16, 16, 16);
        this.texture = tr;
        return tr;
    }

    public void modifySprite(Sprite spt) {

    }

    //called once per frame
    public void modifyBullet(Bullet bullet) {
        // to be overriden
    }

    //change the type of the bullet
    public void changeType(BulletDef type, Bullet bullet){
        this.texture = type.texture;
        type.fixtureD.filter.categoryBits = BulletCollisionListener.ENEMY_BULLET;
        type.fixtureD.filter.maskBits = (short)(BulletCollisionListener.PLAYER | 0x001 | BulletCollisionListener.PLAYER_BULLET);
        bullet.body.createFixture(type.fixtureD);
    }

    public void splitInto(BulletDef type, Bullet bullet, int ways){
        IDanmakuWorld world = bullet.danmakuWorld;
    }

}
