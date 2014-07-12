package com.github.bakabbq;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by LBQ on 7/12/14.
 */
public class StageBoundary {
    public Body upperBody, lowerBody, leftBody, rightBody;
    public StageBoundary(GdxGround ground){
        createBoundaries(0,0,384,449);
    }

    public void createBoundaries(int stageWorldX, int stageWorldY, int stageWidth, int stageHeight){

    }
}
