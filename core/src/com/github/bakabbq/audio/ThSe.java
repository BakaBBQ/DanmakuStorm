package com.github.bakabbq.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by LBQ on 7/17/14.
 */
public class ThSe {
    Sound se;

    public ThSe(String shortPath) {
        se = Gdx.audio.newSound(Gdx.files.internal("se/" + shortPath));
    }

    public void play() {
        se.play();
    }
}