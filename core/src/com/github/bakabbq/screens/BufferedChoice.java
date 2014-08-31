package com.github.bakabbq.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by LBQ on 8/31/14.
 */
public class BufferedChoice {
    public int choiceId;
    public int maxChoices;


    int bufferTimer;
    public BufferedChoice(int maxChoices, int defaultChoice){
        this.maxChoices = maxChoices;
        choiceId = defaultChoice;
        bufferTimer = 0;
    }


    public int bufferTime(){
        return 15;
    }

    public void update(){
        bufferTimer--;
        if (bufferTimer < 0)
            bufferTimer = 0;
        if (bufferTimer > 0)
            return;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (choiceId == 0)
                choiceId = maxChoices - 1;
            else
                choiceId--;
            bufferTimer = bufferTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (choiceId == maxChoices - 1)
                choiceId = 0;
            else
                choiceId--;
            bufferTimer = bufferTime();
        }
    }
}
