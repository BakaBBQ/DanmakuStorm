package com.github.bakabbq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by LBQ on 5/26/14.
 */
public class BulletCollisionListener implements ContactListener {
    public boolean goBack = false;
    @Override
    public void beginContact(Contact contact) {
        goBack = false;
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        if(bodyA.getLinearDamping() >= 5){
            //goBack = true;
        }
        if(bodyB.getLinearDamping() >= 5){
            //goBack = true;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
