package com.github.bakabbq.background;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.bakabbq.GdxGround;

/**
 * Created by LBQ on 6/30/14.
 */
public class ThBackground {
    GdxGround ground;
    public ThBackground(GdxGround ground){
        this.ground = ground;
    }

    public void update(){
        render(ground.background);
    }

    public void render(SpriteBatch batch){

    }

}
