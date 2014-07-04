package com.github.bakabbq.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Created by LBQ on 5/30/14.
 */
public class BulletReimuHoming extends BulletDef {
    public BulletReimuHoming(int colorId) {
        super(colorId);
    }

    public void setTextureIndex() {
        bulletSheet = new Texture(Gdx.files.internal("players/reimu.png"));
        textureX = 0;
        textureY = 10;
        alpha = 180;
        angleFix = 90;
    }

    @Override
    public Shape getShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5 / 5, 1.4f);
        return shape;
    }

    @Override
    public void modifySprite(Sprite spt) {
        spt.setAlpha(180f);
    }

    @Override
    public void modifyBullet(Bullet bullet) {
    }
}
