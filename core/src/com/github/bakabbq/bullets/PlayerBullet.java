package com.github.bakabbq.bullets;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by LBQ on 5/30/14.
 */
public class PlayerBullet extends Bullet {
    public static BulletReimuHoming reimuHoming = new BulletReimuHoming();
    public static ReimuAmulets reimuAmulet = new ReimuAmulets();
    public PlayerBullet(BulletDef bd, World world, float x, float y, float angle) {
        super(bd, world, x, y, angle);
        bd.fixtureD.density = 0.4f;
        bd.fixtureD.friction = 0.5f;
        this.fixture = body.createFixture(bd.fixtureD);
        body.setLinearDamping(0f);
        body.setTransform(x, y, angle);
    }
}
