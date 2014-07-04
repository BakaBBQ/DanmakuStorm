package com.github.bakabbq.bullets;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Created by LBQ on 5/28/14.
 */
public class BulletOval extends BulletDef {
    public BulletOval(int colorId) {
        super(colorId);
    }

    @Override
    public void setTextureIndex() {
        this.textureX = 1;
        this.textureY = 4;
    }

    @Override
    public Shape getShape() {
        Vector2[] vertices = new Vector2[4];

        vertices[0] = new Vector2(3/5f, 0f);
        vertices[1] = new Vector2(0f, 4/5f);
        vertices[2] = new Vector2(-3/5f, 0f);
        vertices[3] = new Vector2(0f, -4/5f);
        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        return shape;
    }
}
