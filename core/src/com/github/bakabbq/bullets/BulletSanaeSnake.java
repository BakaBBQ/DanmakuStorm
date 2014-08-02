package com.github.bakabbq.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Created by LBQ on 7/20/14.
 */
public class BulletSanaeSnake extends BulletDef{
    public BulletSanaeSnake(int colorId) {
        super(colorId);
    }

    public void setTextureIndex() {
        bulletSheet = new Texture(Gdx.files.internal("bullets/sanae_shot.png"));
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
    public Shape getShape() {


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1.6f, 1.2f);
        return shape;
    }

    @Override
    public TextureRegion updateTexture() {
        TextureRegion tr;
        tr = new TextureRegion(bulletSheet,0, 0, 129 - 66, 184 - 174);
        this.texture = tr;
        return tr;
    }


}
