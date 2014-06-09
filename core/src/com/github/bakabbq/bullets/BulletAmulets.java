package com.github.bakabbq.bullets;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Created by LBQ on 5/27/14.
 */
public class BulletAmulets extends BulletDef {
    public void setTextureIndex(){
        textureX = 1;
        textureY = 7;
    }
    @Override
    public Shape getShape(){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5,7);
        return shape;
    }
}
