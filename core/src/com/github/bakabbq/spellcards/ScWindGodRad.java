package com.github.bakabbq.spellcards;

import com.badlogic.gdx.math.MathUtils;
import com.github.bakabbq.bullets.Bullet;
import com.github.bakabbq.bullets.BulletAmulets;
import com.github.bakabbq.bullets.BulletDef;
import com.github.bakabbq.shooters.bosses.ThBoss;

/**
 * Created by LBQ on 8/15/14.
 */
public class ScWindGodRad extends SpellCard{
    public BulletAmulets yellowAmulet = new BulletAmulets(Bullet.COLOR_LEMONYELLOW){

        public boolean stop;
        @Override
        public void modifyBullet(Bullet bullet){
             bullet.setSpeed(-1);
        }
    };
    public BulletAmulets redAmulet = new BulletAmulets(Bullet.COLOR_DARKRED){
    };
    public BulletAmulets blueAmulet = new BulletAmulets(Bullet.COLOR_BLUE){
        @Override
        public void modifyBullet(Bullet bullet){
            bullet.setSpeed(-1);
        }
    };
    public ScWindGodRad(ThBoss owner) {
        super(owner);
    }

    @Override
    public void mainLoop(){
        if(timer % 20 == 0 && timer % 480 <= 240){
            owner.nWayAngeledSpreadShot(yellowAmulet,5,3,timer / 2,8,100);
        }

        if(timer % 19 == 00){
            owner.nWayAngeledSpreadShot(blueAmulet,4,2,timer / 3,8,120);
        }

        if(timer % 60 == 00 && timer >= 300){
            owner.nWayAngeledSpreadShot(redAmulet,6,2,MathUtils.random(0,359),8,80);
        }

        if(timer % 480 == 240){
            for(Bullet singleBullet : owner.ground.getBullets()){
                singleBullet.stop();
                singleBullet.pause = true;
            }
        }

        if(timer % 480 == 0){
            for(Bullet singleBullet : owner.ground.getBullets()){
                singleBullet.setSpeed(100);
            }
        }
    }
}
