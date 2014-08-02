package com.github.bakabbq.shooters;

import com.github.bakabbq.GdxGround;
import com.github.bakabbq.IDanmakuWorld;
import com.github.bakabbq.bullets.Bullet;
import com.github.bakabbq.bullets.BulletDef;

/**
 * Created by LBQ on 5/27/14.
 */
public class BulletShooter {
    public BulletDef defaultBullet = Bullet.debugBullet;
    public float x, y;
    public IDanmakuWorld ground;
    public int timer;

    public BulletShooter(IDanmakuWorld ground) {
        this.ground = ground;
        timer = 0;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }


    //Delegate method
    public void shoot(BulletDef bd, float angle, int speed) {
        ground.addBullet(bd, this.x, this.y, angle).setSpeed(speed);
    }


    public void createBullet(BulletDef bd, float x, float y, float angle, float speed, boolean rel){
        if(rel)
            ground.addBullet(bd, this.x + x, this.y + y, angle).setSpeed(speed);
        else
            ground.addBullet(bd, x, y, angle).setSpeed(speed);
    }

    public void shoot(float angle, int speed) {
        shoot(defaultBullet, angle, speed);
    }



    public void nwayShoot(BulletDef bd, int ways, int angleFix, int speed) {
        if (ways <= 0)
            ways = 1;
        for (int i = 0; i < ways; i++) {
            shoot(bd, i * 360 / ways + angleFix, speed);
        }
    }

    public void nwayShoot(int ways, int angleFix, int speed) {
        if (ways <= 0)
            ways = 1;
        for (int i = 0; i < ways; i++) {
            shoot(i * 360 / ways + angleFix, speed);
        }
    }

    public void update() {
        timer += 1;
        if (timer >= Integer.MAX_VALUE) {
            timer = 0;
        }
        updateShoot();
    }

    public void updateShoot(){
        //to be overwritten
    }
}
