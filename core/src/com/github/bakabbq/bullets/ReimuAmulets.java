package com.github.bakabbq.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Created by LBQ on 5/30/14.
 */
public class ReimuAmulets extends BulletDef{
    public void setTextureIndex(){
        bulletSheet = new Texture(Gdx.files.internal("players/reimu.png"));
        textureX = 0;
        textureY = 10;
        alpha = 140;
        angleFix = 90;
        origin_x = 0;
        origin_y = 16;

        xOffset = 0;
        yOffset = -16 * 4;
    }

    @Override
    public Shape getShape(){


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(8,6);
        return shape;
    }

    @Override
    public TextureRegion updateTexture(){
        TextureRegion tr;
        tr = new TextureRegion(bulletSheet,0,11 * 16,16 * 4,16);
        this.texture = tr;
        return tr;
    }
}
