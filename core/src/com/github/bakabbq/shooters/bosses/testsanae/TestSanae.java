package com.github.bakabbq.shooters.bosses.testsanae;

import com.github.bakabbq.GdxGround;
import com.github.bakabbq.StateHelper;
import com.github.bakabbq.shooters.bosses.ThBoss;

/**
 * Created by LBQ on 6/27/14.
 */
public class TestSanae extends ThBoss{
    public TestSanae(GdxGround ground) {
        super(ground);
        stateId = StateHelper.IDLE;
    }


    @Override
    public int updateFrame(){
        return 3;
    }

    @Override
    public void initSpellCards() {
        spellCards.add(new TestSpellCard(this));
    }
}
