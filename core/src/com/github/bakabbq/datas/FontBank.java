package com.github.bakabbq.datas;

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
    public BitmapFont digital;
    public BitmapFont calisto;
    public BitmapFont calistoBig;
    public BitmapFont trajanPro;
    public BitmapFont scoreInfo;
    public BitmapFont spellName;

    public FontBank() {
        yuMincho = new BitmapFont(Gdx.files.internal("fonts/YuMincho.fnt"));
        savoye = new BitmapFont(Gdx.files.internal("fonts/savoye_let.fnt"));
        log = loadFont("log.fnt");
        arial = loadFont("arial.fnt");
        digital = loadFont("digital.fnt");
        calisto = loadFont("calisto.fnt");
        trajanPro = loadFont("trajanPro.fnt");
        scoreInfo = loadFont("scoreInfo.fnt");
        calistoBig = loadFont("calistoBig.fnt");
        spellName = loadFont("spellFont.fnt");
    }

    BitmapFont loadFont(String str) {
        return new BitmapFont(Gdx.files.internal("fonts/" + str));
    }
}
