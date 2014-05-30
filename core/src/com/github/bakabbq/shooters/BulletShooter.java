package com.github.bakabbq.shooters;

import com.github.bakabbq.GdxGround;
import com.github.bakabbq.bullets.*;

/**
 * Created by LBQ on 5/27/14.
 */
public class BulletShooter {
    GdxGround ground;
    public BulletDef defaultBullet = Bullet.debugBullet;
    int timer;

    public float x, y;
    public BulletShooter(GdxGround ground){
        this.ground = ground;
        timer = 0;
    }


    //Delegate method
    public void shoot(BulletDef bd, float angle, int speed){
        ground.addBullet(bd, this.x, this.y, angle).setSpeed(speed);
    }

    public void shoot(float angle, int speed){
        shoot(defaultBullet,angle, speed);
    }

    public void nwayShoot(int ways, int angleFix, int speed){
        if(ways <= 0)
            ways = 1;
        for(int i = 0; i < ways ; i ++){
            shoot(i * 360 / ways + angleFix, speed);
        }
    }

    public void update(){
        timer += 1;
        if(timer >= Integer.MAX_VALUE)
            timer = 0;
    }
}
