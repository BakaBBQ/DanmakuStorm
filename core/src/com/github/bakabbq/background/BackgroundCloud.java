package com.github.bakabbq.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by LBQ on 8/29/14.
 */
public class BackgroundCloud {
    // to be replaced by assetManager
    public static Texture cloudTexture = new Texture("backgrounds/cloud1.png");
    public static Texture cloudTexture2 = new Texture("backgrounds/cloud2.png");
    public Decal decal;
    public BackgroundCloud(int kind, int y){
        TextureRegion tr;
        if(kind == 2)
            tr = new TextureRegion(BackgroundCloud.cloudTexture2);
        else
            tr = new TextureRegion(BackgroundCloud.cloudTexture);
        decal = Decal.newDecal(tr, true);
        decal.setPosition(MathUtils.random(5,40), y, 60);
        decal.rotateX(10);
    }

    public void update(){
    }
}
