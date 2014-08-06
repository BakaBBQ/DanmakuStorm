package com.github.bakabbq;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by LBQ on 7/12/14.
 */
public class StageBoundary {
    public Body upperBody, lowerBody, leftBody, rightBody;
    IDanmakuWorld ground;
    public StageBoundary(IDanmakuWorld ground){
        this.ground = ground;
    }

    float[] vertices = new float[]{
            50, 463,
            431, 463,
            431, 13,
            50,13
    };
    public void createBoundaries(){
        createUpperBody();
    }

    void createUpperBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
// Set our body's starting position in the world

// Create our body in the world using our body definition
        upperBody = ground.getWorld().createBody(bodyDef);

// Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(431 - 50, 30);
        circle.setRadius(6f);

// Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit

// Create our fixture and attach it to the body
        Fixture fixture = upperBody.createFixture(fixtureDef);
    }
}
