package com.github.bakabbq.bullets;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Created by LBQ on 5/27/14.
 */
public class BulletKunai extends BulletDef {
    @Override
    public void setTextureIndex() {
        textureX = 1;
        textureY = 5;
    }

    @Override
    public Shape getShape() {
        Vector2[] vertices = new Vector2[4];

        vertices[0] = new Vector2(0.8f, 0.8f);
        vertices[1] = new Vector2(0f, 0.8f);
        vertices[2] = new Vector2(-0.8f, 0f);
        vertices[3] = new Vector2(0f, -0.8f);
        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        return shape;
    }
}
