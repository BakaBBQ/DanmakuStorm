package com.github.bakabbq.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.github.bakabbq.DanmakuGame;
import com.github.bakabbq.screens.title.BackgroundSprite;
import com.github.bakabbq.screens.title.LogoSprite;
import com.github.bakabbq.screens.title.TitleChoices;

/**
 * Created by LBQ on 8/14/14.
 */
public class TitleScreen implements Screen{
    DanmakuGame game;

    BackgroundSprite bgSprite;
    LogoSprite logoSprite;
    Texture curtain;
    TitleChoices titleChoices;
    AllblackSprite allblackSprite;
    int timer;
    public TitleScreen(DanmakuGame game){
        timer = 0;
        this.game = game;
        bgSprite = new BackgroundSprite();
        logoSprite = new LogoSprite();
        curtain = new Texture("title/blackCurtain.png");
        titleChoices = new TitleChoices();
        allblackSprite = new AllblackSprite();
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        timer++;
        game.batch.begin();
        bgSprite.update(game.batch);
        logoSprite.draw(game.batch);
        game.batch.draw(curtain,0,0);
        titleChoices.update(game.batch);
        allblackSprite.draw(game.batch,Math.max(30 - timer,0)/30f);
        game.batch.end();

        updateKeys();
    }

    public void updateKeys(){
        if(Gdx.input.isKeyPressed(Input.Keys.Z)){
            switch(titleChoices.choiceId){
                case 0:
                    game.switchToSelection();
                    break;
                case 1:
                    break;
                case 2:
                    System.exit(0);
                    break;
            }
        }
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
