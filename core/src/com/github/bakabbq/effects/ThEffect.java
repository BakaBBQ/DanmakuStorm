package com.github.bakabbq.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.bakabbq.GdxGround;

/**
 * Created by LBQ on 6/28/14.
 *
 * So I finally decided to use a class for effects
 */
public class ThEffect {
    public float x,y;
    public int timer = 0;
    public float zoomX, zoomY = 1f;
    public float angle = 0f;
    public float opacity = 0f;

    public boolean disposeFlag = false;

    public Texture mainTexture = new Texture(Gdx.files.internal("bullets/system.png"));
    public TextureRegion texture;

    public GdxGround ground;

    public int state;

    public ThEffect(){
        initTexture();
        this.state = 0;
    }

    public void initTexture(){
        //Needs to be override
    }

    public void update(){
        timer++;
        mainLoop();
    }

    /*
    Creation
    MainUpdate
    Dispose
     */
    public void onCreation(){

    }

    public void onDispose(){

    }

    public void onMainUpdate(){

    }

    public void nextState(){
        state++;
        if(state>=3)
            dispose();
    }

    public void enterDispose(){
        state = 2;
    }

    public void mainLoop(){
        // to be override
        switch(state){
            case 0:
                onCreation();
                break;
            case 1:
                onMainUpdate();
                break;
            case 2:
                onDispose();
                break;
        }
        generalUpdate();
    }

    public void generalUpdate(){

    }

    public void dispose(){
        disposeFlag = true;
    }
}
