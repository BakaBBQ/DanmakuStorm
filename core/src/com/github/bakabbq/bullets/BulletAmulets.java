package com.github.bakabbq.bullets;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.github.bakabbq.Pixel;

/**
 * Created by LBQ on 5/27/14.
 */
public class BulletAmulets extends BulletDef {
    public void setTextureIndex() {
        textureX = 1;
        textureY = 7;
    }

    @Override
    public Shape getShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Pixel.m2p(5), Pixel.m2p(7));
        return shape;
    }
}
