package com.github.bakabbq.effects;

import com.github.bakabbq.DanmakuGame;

/**
 * Created by LBQ on 8/15/14.
 */
public class SpellEffect {
    DanmakuGame game;
    String currentSpellcardName;
    int timer;
    public SpellEffect(DanmakuGame game){
        this.game = game;
        timer = 0;
    }

    public void startSpell(String spellName){
        currentSpellcardName = spellName;
    }
}
