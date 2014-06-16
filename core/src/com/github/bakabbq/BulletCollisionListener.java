package com.github.bakabbq;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by LBQ on 5/26/14.
 */
public class BulletCollisionListener implements ContactListener {
    public boolean goBack = false;

    public static short PLAYER = 0x002;
    public static short PLAYER_BULLET = 0x004;
    public static short ENEMY = 0x008;
    public static short ENEMY_BULLET = 0x010;
    public static short ITEMS = 0x012;
    @Override
    public void beginContact(Contact contact) {
        goBack = false;
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        if (bodyA.getLinearDamping() >= 5) {
            //goBack = true;
        }
        if (bodyB.getLinearDamping() >= 5) {
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
