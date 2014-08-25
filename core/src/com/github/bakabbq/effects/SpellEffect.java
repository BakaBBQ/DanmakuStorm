package com.github.bakabbq.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.bakabbq.DanmakuGame;
import com.github.bakabbq.IDanmakuWorld;

/**
 * Created by LBQ on 8/15/14.
 */
public class SpellEffect {
    DanmakuGame game;
    String currentSpellcardName;
    Texture spellCardTexture;
    boolean cleared;
    int timer;
    public SpellEffect(DanmakuGame game){
        this.game = game;
        timer = 0;
        spellCardTexture = new Texture("effects/spellcard_name.png");
    }

    public void startSpell(String spellName){
        cleared = false;
        currentSpellcardName = spellName;
        timer = 0;
    }
    public void clearSpell(){
        cleared = true;
    }

    public void update(){
        if(cleared)
            timer--;
            if(timer == -1)
                timer = 0;
        else
            timer++;
        updateSpellcardName();

    }

    public void updateSpellcardName(){
        int x, y;
        x = 250;
        y = 340 + Math.min(timer, 60);
        float alpha = Math.min(timer / 30f, 1f);
        //getBatch().begin();

        Color oriColor = getBatch().getColor();
        getBatch().setColor(oriColor.r,oriColor.g,oriColor.b,alpha);


        getBatch().draw(spellCardTexture, x, y);

        getBatch().setColor(oriColor);
        game.fontBank.yuMincho.draw(getBatch(), currentSpellcardName, x + 10, y + 10);
        //getBatch().end();
    }

    public SpriteBatch getBatch(){
        return game.uiBatch;
    }


}
