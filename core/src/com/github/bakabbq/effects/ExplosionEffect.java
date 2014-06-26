package com.github.bakabbq.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

/**
 * Created by LBQ on 6/23/14.
 */
public class ExplosionEffect {
    public Array<TextureRegion> textures = new Array();
    public TextureRegion texture = new TextureRegion();
    public int timer;
    public int direction;

    public float x;
    public float y;

    public ExplosionEffect(){
        this.timer = 0;
        this.direction = MathUtils.random(0,359);
        initTextures();
        initSingleTexture();
    }

    void initTextures(){
        Texture mainTexture = new Texture(Gdx.files.internal("bullets/system.png"));
        textures.add(new TextureRegion(mainTexture, 64*2, 16, 64, 64));
        textures.add(new TextureRegion(mainTexture, 64*3, 16, 64, 64));
        textures.add(new TextureRegion(mainTexture, 64*0, 16 + 64, 64, 64));
        textures.add(new TextureRegion(mainTexture, 64*1, 16 + 64, 64, 64));
    }

    void initSingleTexture(){
        this.texture = textures.get(MathUtils.random(0,3));
    }

    public void update(){
        timer+=3;
    }

}
