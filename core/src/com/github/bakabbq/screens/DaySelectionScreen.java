package com.github.bakabbq.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.github.bakabbq.DanmakuGame;
import com.github.bakabbq.ScriptDescription;
import com.github.bakabbq.screens.dayselection.StageData;
import com.github.bakabbq.screens.title.BackgroundSprite;

import java.io.File;

/**
 * Created by LBQ on 8/30/14.
 */
public class DaySelectionScreen implements Screen{
    DanmakuGame game;
    BackgroundSprite bgSprite;
    AllblackSprite allblackSprite;
    Texture curtain;
    int timer;
    Array<StageData> stages;
    BufferedChoice bufferedChoice;

    int currentChoiceId;
    public DaySelectionScreen(){
        game = DanmakuGame.getInstance();
        bgSprite = new BackgroundSprite();
        allblackSprite = new AllblackSprite();
        curtain = new Texture("title/blackCurtain.png");
        timer = 0;
        stages = StageData.parseAllStages(new File("stage_infos"));
        Gdx.app.log("Json", ""+stages.size);
        currentChoiceId = 0;
        bufferedChoice = new BufferedChoice(stages.size, 0);
    }

    @Override
    public void render(float delta) {
        timer++;
        game.batch.begin();
        trueRendering();
        game.batch.end();
        updateKeys();
        bufferedChoice.update();
    }

    public void trueRendering(){
        bgSprite.update(game.batch);
        game.batch.draw(curtain,0,0);
        allblackSprite.draw(game.batch,Math.max(30 - timer,0)/30f);
        renderStageSelection();
    }

    public void renderStageSelection(){

        int i;

        i = 0;
        for(StageData singleData : stages){
            i++;
            Color c;
            c = game.batch.getColor();
            float o;
            Gdx.app.log("Choice", "" + bufferedChoice.choiceId);
            if (bufferedChoice.choiceId == i)
                o = 1f;
            else
                o = 0.6f;
            game.batch.setColor(c.r, c.g, c.b, o);
            game.fontBank.trajanPro.draw(game.batch, singleData.name, 50, 450 - 40 * i);
            int j = 0;
            for(ScriptDescription sd : singleData.scripts){
                j++;
                game.fontBank.arial.draw(game.batch, sd.bossName + " " + sd.spellName, 200, 400 - 30* j);
            }
            game.batch.setColor(c.r, c.g, c.b, 1);
        }
    }

    public void updateKeys(){
        if(Gdx.input.isKeyPressed(Input.Keys.X)){
            game.switchToTitle();
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

    @Override
    public void dispose() {

    }
}
