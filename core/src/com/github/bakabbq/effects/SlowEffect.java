package com.github.bakabbq.effects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by LBQ on 6/28/14.
 */
public class SlowEffect extends ThEffect{
    @Override
    public void initTexture(){
        this.texture = new TextureRegion(mainTexture, 0, 16, 64, 64);
    }

    @Override
    public void onCreation(){
        this.opacity += 0.2f;
        if(this.opacity >= 1.0f){
            this.opacity = 1.0f;
            nextState();
        }
    }

    @Override
    public void onDispose(){
        this.opacity -= 0.2f;
        if(this.opacity <= 0.0f){
            this.opacity = 0.0f;
            nextState();
        }
    }

    @Override
    public void onMainUpdate(){

    }

    @Override
    public void generalUpdate(){
        this.angle++;
        this.x = ground.player.getX();
        this.y = ground.player.getY();
    }
}
