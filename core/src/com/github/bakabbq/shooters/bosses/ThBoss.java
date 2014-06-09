package com.github.bakabbq.shooters.bosses;

import com.badlogic.gdx.utils.Array;
import com.github.bakabbq.GdxGround;
import com.github.bakabbq.shooters.EnemyShooter;
import com.github.bakabbq.spellcards.SpellCard;

/**
 * Created by LBQ on 6/9/14.
 */
public class ThBoss extends EnemyShooter{
    public String name = "";
    public Array<SpellCard> spellCards = new Array();
    public ThBoss(GdxGround ground) {
        super(ground);
        initSpellCards();
    }

    void initSpellCards(){

    }
}
