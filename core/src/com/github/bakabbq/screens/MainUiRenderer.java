package com.github.bakabbq.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by LBQ on 8/7/14.
 */
public class MainUiRenderer {
    Texture frame;
    Texture uiText;
    public MainUiRenderer(){
        frame = new Texture(Gdx.files.internal("menus/front.png"));
        uiText = new Texture(Gdx.files.internal("menus/menuUi.png"));
    }

    public void render(SpriteBatch batch){
        batch.draw(frame,0,0);
        batch.draw(uiText,0,0);
    }
}
