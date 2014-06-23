package com.github.bakabbq.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.github.bakabbq.BulletCollisionListener;
import com.github.bakabbq.GdxGround;

/**
 * Created by LBQ on 6/16/14.
 */
public class ThItem {
    /*
     * I need to simulate a LOT of things... damn...
     */

    //Destroy Flag - once marked, it will be garbage dumped
    public boolean destroyFlag;


    GdxGround ground;
    public TextureRegion texture;
    public Body itemBody;
    public ThItem(GdxGround ground){
        this.ground = ground;
        createBody();
        initTexture();
    }

    void createBody(){
        BodyDef itemDef = new BodyDef();
        itemDef.type = BodyDef.BodyType.DynamicBody;
        itemDef.position.set(0,0);
        itemBody = ground.world.createBody(itemDef);

        // User Data Update
        itemBody.setUserData(this);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = getShape();
        fixtureDef.density = 1;
        fixtureDef.friction = 0.1f;
        fixtureDef.filter.categoryBits = BulletCollisionListener.ITEMS;
        fixtureDef.filter.maskBits = (short) (BulletCollisionListener.ITEMS | BulletCollisionListener.PLAYER);
        Fixture fixture = itemBody.createFixture(fixtureDef);
    }

    public Shape getShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5 / 5, 1.4f);
        return shape;
    }

    void initTexture(){
        Texture itemSheet = new Texture(Gdx.files.internal("bullets/system.png"));
        texture = new TextureRegion(itemSheet, 13 * 16, 0, 16, 16);
    }

    public void setPos(float x, float y){
        itemBody.setTransform(x, y, itemBody.getAngle());
    }

    TextureRegion getTexture(){
        return this.texture;
    }

    void update(){

    }

}
