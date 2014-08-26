package com.github.bakabbq.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Created by LBQ on 8/26/14.
 */
public class BulletButterfly extends BigBullet {
    public BulletButterfly(int colorId) {
        super(colorId);
    }

    @Override
    public void setTextureIndex() {
        bulletSheet = new Texture(Gdx.files.internal("bullets/bullet2.png"));
        textureX = colorId * 32;
        textureY = 32 * 2;
        this.xOffset = -32/4;
        this.yOffset = -32/4;
    }

    @Override
    public Shape getShape() {
        CircleShape shape = new CircleShape();
        shape.setRadius(1.6f);
        return shape;
    }
}
