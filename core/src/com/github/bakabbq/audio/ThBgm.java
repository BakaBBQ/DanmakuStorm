package com.github.bakabbq.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by LBQ on 7/17/14.
 *
 * ThBgm Class is supposed to be Overwritten for ALL new bgm instances
 */
public class ThBgm {
    public Music bgm;
    public ThBgm(String shortPath){
        bgm = Gdx.audio.newMusic(Gdx.files.internal("bgm/" + shortPath));
    }

    // The Name
    public String getName(){
        return "Unnamed";
    }

    // The Comment
    public String getComment(){
        return "Comment Here";
    }
}
