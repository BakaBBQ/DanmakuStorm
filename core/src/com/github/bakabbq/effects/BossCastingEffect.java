package com.github.bakabbq.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by LBQ on 6/28/14.
 */
public class BossCastingEffect extends ThEffect{
    @Override
    public void initTexture(){
        this.texture = new TextureRegion(mainTexture, 128, 64 + 16, 128, 128);
    }

    @Override
    public void onCreation(){

    }

    @Override
    public void onDispose(){

    }

    @Override
    public void onMainUpdate(){

    }

}
