package com.github.bakabbq.spellcards;

import com.github.bakabbq.BulletScript;
import com.github.bakabbq.shooters.bosses.ThBoss;

/**
 * Created by LBQ on 8/20/14.
 */
public class JRubySpellCard extends SpellCard{
    BulletScript script;
    public JRubySpellCard(ThBoss owner, BulletScript script) {
        super(owner);
        this.script = script;
        this.script.setOwner(this.owner);
    }

    @Override
    public void mainLoop(){
        script.update();
    }
}
