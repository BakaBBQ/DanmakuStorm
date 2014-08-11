package com.github.bakabbq.shooters.bosses.testsanae;

import com.badlogic.gdx.math.MathUtils;
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
       if(timer % 60 == 0){
           owner.nwayShoot(30,MathUtils.random(0,360),60);
       }
    } // void mainLoop
}
