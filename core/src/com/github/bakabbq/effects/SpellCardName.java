package com.github.bakabbq.effects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by LBQ on 7/17/14.
 */
public class SpellCardName {
    public String name;
    public int timer;

    public int xPos;
    public int yPos;

    public TextureRegion spellCardTexture;
    public SpellCardName(String name) {
        this.name = name;
        this.timer = 0;
    }

    public void update(){
        timer++;

    }
}
