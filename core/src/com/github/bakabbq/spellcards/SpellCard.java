package com.github.bakabbq.spellcards;

import com.github.bakabbq.shooters.bosses.ThBoss;

/**
 * Created by LBQ on 6/9/14.
 */
public class SpellCard {
    public String name = "";
    public float timeOut = 60f;
    public ThBoss owner;
    public boolean spell = false;
    public int timer;

    public SpellCard(ThBoss owner) {
        this.owner = owner;
        this.timer = 0;
    }

    public void update() {
        timer++;
        mainLoop();
    }

    public void mainLoop() {
        //Needs to be overwritten
    }
}
