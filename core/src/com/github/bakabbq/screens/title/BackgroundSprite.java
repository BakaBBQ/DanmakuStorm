package com.github.bakabbq.screens.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by LBQ on 8/14/14.
 */
public class BackgroundSprite extends Sprite{
    int timer;

    public BackgroundSprite(){
        super(new Texture("title/titleBackground.png"),1280,480);
        Texture t = new Texture("title/titleBackground.png");
        t.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        this.setTexture(t);
        timer = 0;
    }

    public void update(SpriteBatch batch){
        timer++;
        this.setPosition(-600 + timer % 600, 0);
        this.draw(batch);
    }
}
