package com.github.bakabbq.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.bakabbq.DanmakuGame;

/**
 * Created by LBQ on 8/11/14.
 */
public class SigScreen implements Screen {
    DanmakuGame game;
    Texture sig;
    Sprite sigSprite;

    int timer;

    public SigScreen(DanmakuGame game){
        this.game = game;
        sig = new Texture(Gdx.files.local("title/sig.png"));
        sigSprite = new Sprite(sig);
        timer = 0;
    }


    public SpriteBatch getBatch(){
        return game.uiBatch;
    }
    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        timer++;
        getBatch().begin();

        float opacity = 0;
        if(timer >= 0 && timer <= 80){
            opacity = timer / 80f;
        }
        if(timer > 80 && timer < 180){
            opacity = 1f;
        }
        if (timer >= 180){
            opacity = (80 - (timer - 180)) / 80f;
        }
        if (timer >= 260){
            game.switchToTitle();
            opacity = 0f;
        }
        sigSprite.draw(getBatch(),opacity);
        getBatch().end();
    }


    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void show() {

    }


    @Override
    public void hide() {

    }


    @Override
    public void pause() {

    }


    @Override
    public void resume() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
