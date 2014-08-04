package com.github.bakabbq.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Created by LBQ on 8/4/14.
 */
public class BulletKnife extends BulletDef{

    public BulletKnife(int colorId) {
        super(colorId);
    }

    @Override
    public void setTextureIndex() {
        bulletSheet = new Texture(Gdx.files.internal("bullets/bullet1.png"));
        textureX = colorId * 32;
        textureY = 32 * 3;
        xOffset = -32/5;
        yOffset = -32/5;
    }

    @Override
    public TextureRegion updateTexture() {
        TextureRegion tr;
        tr = new TextureRegion(bulletSheet,textureX, textureY, 32, 32);
        this.texture = tr;
        return tr;
    }

    @Override
    public Shape getShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.2f, 0.8f);
        return shape;
    }
}
