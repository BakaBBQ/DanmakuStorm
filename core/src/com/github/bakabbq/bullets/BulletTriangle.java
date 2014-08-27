package com.github.bakabbq.bullets;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Created by LBQ on 2014/8/27.
 */
public class BulletTriangle extends BulletDef{
    public BulletTriangle(int colorId) {
        super(colorId);
    }

    @Override
    public void setTextureIndex() {
        textureX = colorId;
        textureY = 1;
    }

    @Override
    public Shape getShape() {
        Vector2[] vertices = new Vector2[3];

        vertices[0] = new Vector2(1f, 0.8f);
        vertices[1] = new Vector2(0f, 0.8f);
        vertices[2] = new Vector2(-1f, 0f);
        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        return shape;
    }
}
