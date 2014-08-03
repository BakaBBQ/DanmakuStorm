package com.github.bakabbq.shooters.bosses.kanako;

import com.badlogic.gdx.math.MathUtils;
import com.github.bakabbq.bullets.Bullet;
import com.github.bakabbq.bullets.BulletAmulets;
import com.github.bakabbq.bullets.BulletOval;
import com.github.bakabbq.shooters.bosses.ThBoss;
import com.github.bakabbq.spellcards.SpellCard;

/**
 * Created by LBQ on 8/2/14.
 */
public class NonSpell1 extends SpellCard{
    public NonSpell1(ThBoss owner) {
        super(owner);
    }

    @Override
    public void updateAttributes(){
        this.name = "non spell";

    }

    @Override
    public void mainLoop(){

        if(timer % 20 == 0){
            owner.angledSpreadShot(new BulletAmulets(Bullet.COLOR_BLUE), 3, timer % 360, 25, 60);
            owner.angledSpreadShot(new BulletAmulets(Bullet.COLOR_GREEN), 3, 360 - timer % 360, 25, 60);


        }

        if(timer % 22 == 0){
            owner.angledSpreadShot(new BulletAmulets(Bullet.COLOR_DARKRED), 4, timer % 180 * 2, 25, 70);
            owner.angledSpreadShot(new BulletAmulets(Bullet.COLOR_YELLOW), 4, 360 - timer % 180 * 2, 25, 80);
        }

    } // void mainLoop
}
