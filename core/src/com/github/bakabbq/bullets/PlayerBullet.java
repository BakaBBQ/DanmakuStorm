package com.github.bakabbq.bullets;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by LBQ on 5/30/14.
 */
public class PlayerBullet extends Bullet {
    public PlayerBullet(BulletDef bd, World world, float x, float y, float angle) {
        super(bd, world, x, y, angle);
        FixtureDef fd = bd.fixtureD;
        fd.filter.groupIndex = -1;
        this.fixture = body.createFixture(fd);
        body.setTransform(x, y, angle);
    }
}
