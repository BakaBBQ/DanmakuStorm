package com.github.bakabbq.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by LBQ on 8/26/14.
 */
public class BigBullet extends BulletDef {
    protected Texture bulletSheet = new Texture(Gdx.files.internal("bullets/bullet2.png"));
    public BigBullet(int colorId) {
        super(colorId);
    }

    @Override
    public void initCreationTexture(){
        Texture bulletSheetori = new Texture(Gdx.files.internal("bullets/bullet1.png"));
        int startX;
        startX = colorId * 32;
        onCreationTexture = new TextureRegion(bulletSheetori,startX,13 * 16,32,32);
    }

    public void setTextureIndex() {
        textureX = this.colorId;
        textureY = 7;
    }

    @Override
    public TextureRegion updateTexture() {
        TextureRegion tr;
        tr = new TextureRegion(bulletSheet,textureX, textureY, 32, 32);
        this.texture = tr;
        return tr;
    }
}
