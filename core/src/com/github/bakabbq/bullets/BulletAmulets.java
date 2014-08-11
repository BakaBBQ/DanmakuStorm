package com.github.bakabbq.bullets;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import datas.Pixel;

/**
 * Created by LBQ on 5/27/14.
 */
public class BulletAmulets extends BulletDef {
    public BulletAmulets(int colorId) {
        super(colorId);
    }

    public void setTextureIndex() {
        textureX = this.colorId;
        textureY = 7;
    }

    @Override
    public Shape getShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Pixel.m2p(5), Pixel.m2p(7));
        return shape;
    }
}
