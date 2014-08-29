package com.github.bakabbq.effects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by LBQ on 8/29/14.
 */
public class SlaveParticleEffect {
    //TODO replace all static textures with asset manager
    public static Texture particleTexture = new Texture("particles/p.png");
    public Sprite particle;

    public SlaveParticleEffect(float x, float y) {
        particle = new Sprite(particleTexture);
        particle.setOriginCenter();
        particle.setScale(0.6f);
        particle.setPosition(x * 5 + 54 + 15, y * 5 + 10);
    }

    public void update(){
        particle.setScale(particle.getScaleX() - 0.035f);
    }

    public boolean canDispose(){
        return particle.getScaleX() <= 0;
    }
}