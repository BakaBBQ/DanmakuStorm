package com.github.bakabbq.shooters;

import com.badlogic.gdx.math.MathUtils;
import com.github.bakabbq.GdxGround;
import com.github.bakabbq.bullets.Bullet;

/**
 * Created by LBQ on 5/27/14.
 */
public class DebugShooter extends BulletShooter {

    public DebugShooter(GdxGround ground) {
        super(ground);
        defaultBullet = Bullet.debugBullet;
    }

    public void update() {
        super.update();
        if (timer % 60 == 0) {
            nwayShoot(60, MathUtils.random(0, 180), 100);
        }
    }
}
