package com.github.bakabbq.shooters;

import com.github.bakabbq.IDanmakuWorld;

/**
 * Created by LBQ on 8/26/14.
 */
public class SlaveShooter extends BulletShooter {
    EnemyShooter owner;
    public SlaveShooter(EnemyShooter owner) {
        super(owner.ground);
        this.owner = owner;
    }
}
