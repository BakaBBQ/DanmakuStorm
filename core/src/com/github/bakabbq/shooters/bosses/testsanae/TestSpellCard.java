package com.github.bakabbq.shooters.bosses.testsanae;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.github.bakabbq.StateHelper;
import com.github.bakabbq.bullets.*;
import com.github.bakabbq.shooters.bosses.ThBoss;
import com.github.bakabbq.spellcards.SpellCard;

/**
 * Created by LBQ on 6/27/14.
 */
public class TestSpellCard extends SpellCard{
    public TestSpellCard(ThBoss owner) {
        super(owner);
    }

    @Override
    public void updateAttributes(){
        this.name = "meow";

    }

    @Override
    public void mainLoop(){
        if(timer % 60 == 0 && timer % 120 != 0)
            owner.nwayShoot(new BulletAmulets(0){
                @Override
                public void modifyBullet(Bullet bullet){
                    if(bullet.timer == 60){
                        bullet.setSpeed(bullet.body.getAngle() + MathUtils.random(-60,60), 2f);
                        bullet.stop();
                    }

                    if(bullet.timer == 120){
                        bullet.setSpeed(-80f);
                    }

                    if(bullet.timer == 240){
                        bullet.setSpeed(100f);
                    }
                }
            }, 100, 0, 40);



        else if(timer%60==0 && timer % 120 == 0){
            owner.nwayShoot(new BulletKunai(0){
                @Override
                public void modifyBullet(Bullet bullet){
                    if(bullet.timer == 60){
                        bullet.stop();
                    }

                    if(bullet.timer == 120){
                        bullet.setSpeed(-80f);
                    }

                    if(bullet.timer == 240){
                        //bullet.setSpeed(100f);
                    }
                }
            }, 20, 0, 10);
        }





    } // void mainLoop
}
