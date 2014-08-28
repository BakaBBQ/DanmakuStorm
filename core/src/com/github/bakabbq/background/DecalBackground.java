package com.github.bakabbq.background;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.math.MathUtils;
import com.github.bakabbq.DanmakuGame;
import com.github.bakabbq.IDanmakuWorld;

/**
 * Created by LBQ on 2014/8/28.
 */
public class DecalBackground extends ThBackground{
    public Decal testDecal;
    public DecalBatch dBatch;

    public DecalBackground(IDanmakuWorld ground) {
        super(ground);
        dBatch = new DecalBatch();
        Texture testBackground = new Texture("backgrounds/stg5bg.png");
        TextureRegion tr = new TextureRegion(testBackground);
        testDecal = Decal.newDecal(tr);
        CameraGroupStrategy cameraGroupStrategy = new CameraGroupStrategy(DanmakuGame.getInstance().camera);
        dBatch.setGroupStrategy(cameraGroupStrategy);
        testDecal.setPosition(5, 8, 1);
        testDecal.setScale(1f);
    }

    @Override
    void mainLoop(SpriteBatch batch){
        batch.end();

        batch.begin();
    }


    public void decalLoop(){
        testDecal.setPosition(MathUtils.random(10), MathUtils.random(10), MathUtils.random(10));
        testDecal.rotateZ(10);
        dBatch.add(testDecal);
        dBatch.flush();
    }

    public void setCam(Camera cam){
        CameraGroupStrategy cameraGroupStrategy = new CameraGroupStrategy(cam);
        dBatch.setGroupStrategy(cameraGroupStrategy);
    }
}
