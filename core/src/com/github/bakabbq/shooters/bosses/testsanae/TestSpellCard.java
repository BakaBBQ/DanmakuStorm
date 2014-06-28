package com.github.bakabbq.shooters.bosses.testsanae;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.github.bakabbq.StateHelper;
import com.github.bakabbq.bullets.Bullet;
import com.github.bakabbq.bullets.BulletDef;
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
        if(timer % 60 == 0)
            for(int i = 0; i < 40 ; i ++){
                owner.shoot(Bullet.amuletBullet, MathUtils.random(0,360), MathUtils.random(20,180));
            }

    } // void mainLoop
}
