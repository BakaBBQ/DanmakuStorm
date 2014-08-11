package com.github.bakabbq.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.github.bakabbq.GdxGround;
import com.github.bakabbq.IDanmakuWorld;

/**
 * Created by LBQ on 6/30/14.
 */
public class ThBackground {
    IDanmakuWorld ground;

    Texture texture1;
    Texture texture2;

    TiledDrawable tile1;
    TiledDrawable tile2;

    Sprite sprite1;
    Sprite sprite2;

    Sprite test;
    public int timer;
    public ThBackground(IDanmakuWorld ground){
        this.ground = ground;
        texture1 = new Texture(Gdx.files.internal("backgrounds/cdbg01a.png"));
        texture2 = new Texture(Gdx.files.internal("backgrounds/cdbg01b.png"));
        texture1.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        texture2.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        sprite1 = new Sprite(texture1,0,0,640 + 256,700 + 256);
        sprite2 = new Sprite(texture2,0,0,640 + 256,480 + 256);
        sprite2.setPosition(-256,-256);
        sprite1.setPosition(-256,-256);
        //sprite1.setAlpha(180f/255);

        sprite2.setAlpha(200f/255);
        //test.setScale(0.2f);

        tile1 = new TiledDrawable(new TextureRegion(texture1));
        tile2 = new TiledDrawable(new TextureRegion(texture2));

        timer = 0;
    }

    public void update(SpriteBatch batch){
        timer++;
        mainLoop(batch);
    }

    void mainLoop(SpriteBatch batch){
        sprite2.setPosition(-256 + timer % 256, -256 +timer % 256);
        sprite2.draw(batch);
        sprite1.setPosition(-256 + 256 - timer % 256, -256 +timer % 256);
        sprite1.draw(batch);
    }

    void drawBackgroundAtBatch(Texture texture, SpriteBatch batch, int x, int y){

    }

}
