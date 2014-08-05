package com.github.bakabbq.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.github.bakabbq.shooters.bosses.ThBoss;

/**
 * Created by LBQ on 8/5/14.
 *
 * Yeah ThEffect is a failure..
 */
public class BossEffects {
    Sprite rotatingHexagram;
    public int timer;
    public BossEffects(){
        rotatingHexagram = new Sprite(new TextureRegion(new Texture(Gdx.files.internal("bullets/system.png")), 128, 64 + 16, 128, 128));
        rotatingHexagram.setOriginCenter();
        timer = 0;
    }

    public void update(ThBoss boss, SpriteBatch batch){
        if(boss == null)
            return;
        timer++;
        rotatingHexagram.rotate(1f + Math.abs((60 - timer % 120)) / 60f);
        rotatingHexagram.setScale((1f + MathUtils.sin(timer / 20f)*0.2f) * 2);
        rotatingHexagram.setPosition(boss.getX() + 146,boss.getY() + 240);
        //(MathUtils.sin(timer / 10f) * 120 + 255f)/255f)
        rotatingHexagram.draw(batch,1f - Math.abs(MathUtils.sin(timer / 20f)*0.2f));
    }
}
