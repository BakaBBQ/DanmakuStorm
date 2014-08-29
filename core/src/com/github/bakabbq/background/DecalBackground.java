package com.github.bakabbq.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.github.bakabbq.DanmakuGame;
import com.github.bakabbq.IDanmakuWorld;

/**
 * Created by LBQ on 2014/8/28.
 */
public class DecalBackground extends ThBackground{
    public Decal testDecal;
    public Decal testDecal2;
    public Decal testDecal3;
    public Decal testDecal4;
    public DecalBatch dBatch;


    BackgroundCloud testCloud;
    Array<BackgroundCloud> clouds;

    public DecalBackground(IDanmakuWorld ground) {
        super(ground);
        dBatch = new DecalBatch();
        Texture testBackground = new Texture("backgrounds/textureDay1-fixed.png");
        testBackground.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
        TextureRegion tr = new TextureRegion(testBackground);
        testDecal = Decal.newDecal(tr);
        testDecal2 = Decal.newDecal(tr);
        testDecal3 = Decal.newDecal(tr);
        testDecal4 = Decal.newDecal(tr);
        testDecal2.setPosition(0,-256,0);
        testDecal.setPosition(0, 0, 0);
        testDecal3.setPosition(0,256,0);
        testDecal4.setPosition(0,512,0);

        clouds = new Array<BackgroundCloud>();

        //clouds.add(new BackgroundCloud(2, 180));
        //clouds.add(new BackgroundCloud(1, 265));
    }

    @Override
    void mainLoop(SpriteBatch batch){
        batch.end();

        batch.begin();
    }


    public void decalLoop(){
        this.timer++;
        dBatch.add(testDecal3);
        dBatch.add(testDecal4);
        dBatch.add(testDecal2);
        dBatch.add(testDecal);
        for(BackgroundCloud singleCloud : clouds){
            dBatch.add(singleCloud.decal);
        }
        dBatch.flush();
    }

    public void setCam(Camera cam){
        cam.position.set(60f, -60f + timer % 512, 240f);
        cam.lookAt(60f, timer % 512, 0f);
        if(timer == 0)
            newInterval();
        cam.update();
        CameraGroupStrategy cameraGroupStrategy = new CameraGroupStrategy(cam);
        dBatch.setGroupStrategy(cameraGroupStrategy);
    }

    void newInterval(){
        clouds.clear();
        clouds.add(new BackgroundCloud(MathUtils.random(1,2), MathUtils.random(200,250)));
    }
}
