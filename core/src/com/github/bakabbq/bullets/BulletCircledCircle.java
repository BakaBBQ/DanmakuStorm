package com.github.bakabbq.bullets;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Created by LBQ on 2014/8/27.
 */
public class BulletCircledCircle extends BulletDef {
    public BulletCircledCircle(int colorId) {
        super(colorId);
    }

    @Override
    public void setTextureIndex() {
        textureX = colorId;
        textureY = 2;
    }

    @Override
    public Shape getShape() {
        CircleShape shape = new CircleShape();
        shape.setRadius(1.5f);
        return shape;
    }
}
