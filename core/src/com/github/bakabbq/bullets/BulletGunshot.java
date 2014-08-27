package com.github.bakabbq.bullets;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Created by LBQ on 2014/8/27.
 */
public class BulletGunshot extends BulletDef {
    public BulletGunshot(int colorId) {
        super(colorId);
    }

    @Override
    public void setTextureIndex() {
        textureX = colorId;
        textureY = 8;
    }

    @Override
    public Shape getShape() {
        Vector2[] vertices = new Vector2[5];

        vertices[0] = new Vector2(1.2f, -0.5f);
        vertices[1] = new Vector2(1.2f, 0.3f);
        vertices[2] = new Vector2(0f, 0.5f);
        vertices[3] = new Vector2(-1.2f, 0.3f);
        vertices[4] = new Vector2(-1.2f, -0.5f);
        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        return shape;
    }
}
