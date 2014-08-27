package com.github.bakabbq.bullets;

/**
 * Created by LBQ on 2014/8/27.
 */
public class BulletSharpOval extends BulletOval {
    public BulletSharpOval(int colorId) {
        super(colorId);
    }

    @Override
    public void setTextureIndex() {
        textureX = colorId;
        textureY = 6;
    }
}
