package com.github.bakabbq.shooters.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.github.bakabbq.BulletCollisionListener;
import com.github.bakabbq.GdxGround;
import com.github.bakabbq.IDanmakuWorld;

/**
 * Created by LBQ on 7/1/14.
 */
public class PlayerGrazeCounter {
    IDanmakuWorld ground;
    DanmakuPlayer player;
    Body body;

    public final float GRAZE_COUNTER = 3.5f;
    public PlayerGrazeCounter(DanmakuPlayer player){
        this.player = player;
        this.ground = player.ground;
        createBody();
    }

    public void createBody(){
        World world = ground.getWorld();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.player.playerBody.getPosition());
        this.body = world.createBody(bodyDef);
        body.setUserData(this);
        CircleShape shape = new CircleShape();
        shape.setRadius(GRAZE_COUNTER);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;
        fixtureDef.isSensor = true;
        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void dispose(){
        ground.getWorld().destroyBody(body);
    }

    public void update(){
        this.body.setTransform(player.playerBody.getPosition(), 0);
    }
}
