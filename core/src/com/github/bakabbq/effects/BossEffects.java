package com.github.bakabbq.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.github.bakabbq.DanmakuGame;
import com.github.bakabbq.shooters.bosses.ThBoss;

/**
 * Created by LBQ on 8/5/14.
 *
 * Yeah ThEffect is a failure..
 */
public class BossEffects {
    Sprite rotatingHexagram;
    //Shape Renderer - for drawing health bar
    ShapeRenderer shapeRenderer;
    public SpellEffect spellEffect;

    Texture hpTexture;
    public int timer;

    private static BossEffects instance;
    public static BossEffects getInstance(){
        if(instance == null)
            instance = new BossEffects();
        return instance;
    }
    private BossEffects(){
        //rotatingHexagram = new Sprite(new TextureRegion(new Texture(Gdx.files.internal("bullets/system.png")), 128, 64 + 16, 128, 128));
        rotatingHexagram = new Sprite(new Texture("effects/hexagram.png"));
        rotatingHexagram.setOriginCenter();
        shapeRenderer = new ShapeRenderer();
        spellEffect = new SpellEffect(DanmakuGame.getInstance());
        timer = 0;

        hpTexture = new Texture("menus/hpBar.png");
    }

    public void update(ThBoss boss, SpriteBatch batch){
        if(boss == null)
            return;
        timer++;

        rotatingHexagram.setScale(1f + MathUtils.sinDeg(timer * 3) * 0.1f);
        rotatingHexagram.rotate(6f * rotatingHexagram.getScaleX());
        //Completely Magical
        rotatingHexagram.setPosition(boss.getX() * 5 - 10,boss.getY() * 5 - 72);
        float opacity = Math.min(1f / rotatingHexagram.getScaleX(), 1f) * 0.7f;
        rotatingHexagram.draw(batch,opacity);
    }

    public void drawHpBar(ThBoss boss, SpriteBatch batch){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(252/255f,158/255f,126/255f,1);
        shapeRenderer.rect(60,455,355 * boss.getScHpRatio(),2);
        shapeRenderer.end();
    }
}
