package com.github.bakabbq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by LBQ on 7/17/14.
 */
public class FontBank {
    public BitmapFont yuMincho;
    public BitmapFont savoye;
    public BitmapFont log;
    public BitmapFont arial;
    public FontBank(){
        yuMincho = new BitmapFont(Gdx.files.internal("fonts/YuMincho.fnt"));
        savoye = new BitmapFont(Gdx.files.internal("fonts/savoye_let.fnt"));
        log = loadFont("log.fnt");
        arial = loadFont("arial.fnt");
    }

    BitmapFont loadFont(String str){
        return new BitmapFont(Gdx.files.internal("fonts/" + str));
    }
}
