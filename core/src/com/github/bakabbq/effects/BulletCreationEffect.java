package com.github.bakabbq.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by LBQ on 7/4/14.
 */
public class BulletCreationEffect extends ThEffect{
    public BulletCreationEffect(){
        super();
    }

    @Override
    public void initTexture(){
        this.texture = new TextureRegion(new Texture(Gdx.files.internal("bullets/bullet1.png")), 0, 208, 32, 32);
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
        this.zoomX -= 0.03f;
        this.zoomY -= 0.03f;
        if (this.zoomX <= 0.0f){
            enterDispose();
        }
    }
}