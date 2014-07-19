package com.github.bakabbq.audio;

import com.github.bakabbq.IDanmakuWorld;

/**
 * Created by LBQ on 7/17/14.
 *
 * Music Box, the actual sound player
 */
public class MusicBox {
    IDanmakuWorld world;

    ThBgm currentBgm;
    public MusicBox(IDanmakuWorld world){
        this.world = world;
    }

    public void playSe(ThSe se){
        se.play();
    }

    public void switchBgm(ThBgm bgm){
        if(currentBgm!=null)
            currentBgm.bgm.dispose();
        currentBgm = bgm;
        currentBgm.bgm.setLooping(true);
        currentBgm.bgm.play();
    }
}
