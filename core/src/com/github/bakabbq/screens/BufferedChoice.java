package com.github.bakabbq.screens;

/**
 * Created by LBQ on 8/31/14.
 */
public class BufferedChoice {
    int choiceId;
    int maxChoices;

    int bufferTimer;
    public BufferedChoice(int maxChoices, int defaultChoice){
        this.maxChoices = maxChoices;
        choiceId = defaultChoice;
    }


    public int bufferTime(){
        return 30;
    }

    public void update(){

    }
}
