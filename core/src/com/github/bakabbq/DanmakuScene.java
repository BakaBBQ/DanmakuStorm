package com.github.bakabbq;

import com.github.bakabbq.background.ThBackground;
import com.github.bakabbq.shooters.bosses.ThBoss;
import com.github.bakabbq.spellcards.SpellCard;

/**
 * Created by LBQ on 7/14/14.
 */
public class DanmakuScene {
    public Class boss;
    public Class spellCard;
    public Class background;
    public DanmakuScene(Class boss, Class spellCard, Class background){
        this.boss = boss;
        this.spellCard = spellCard;
        this.background = background;
    }
}
