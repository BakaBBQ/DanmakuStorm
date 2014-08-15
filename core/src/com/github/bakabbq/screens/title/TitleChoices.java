package com.github.bakabbq.screens.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by LBQ on 8/14/14.
 */
public class TitleChoices {
    Sprite start;
    Sprite musicRoom;
    Sprite quit;

    public int choiceId;
    int justChangeTimer;
    public TitleChoices(){
        start = new Sprite(new Texture("title/startGame.png"));
        musicRoom = new Sprite(new Texture("title/musicRoom.png"));
        quit = new Sprite(new Texture("title/quit.png"));

        choiceId = 0;
        currentKey = 0;
        justChangeTimer = 0;
        o1 = 0.5f; o2=0.5f;o3=0.5f;
    }

    float o1, o2, o3;
    public void update(SpriteBatch batch){
        updateKeyboard();
        if(choiceId == 0) {
            o1 = Math.min(o1 + 0.02f, 1f);
            o2 = Math.max(o2 - 0.02f, 0.5f);
            o3 = Math.max(o3 - 0.02f, 0.5f);
        }
        else if(choiceId == 1) {
            o2 = Math.min(o2 + 0.02f, 1f);
            o1 = Math.max(o1 - 0.02f, 0.5f);
            o3 = Math.max(o3 - 0.02f, 0.5f);
        }
        else if(choiceId == 2){
            o3 = Math.min(o3 + 0.02f, 1f);
            o2 = Math.max(o2 - 0.02f, 0.5f);
            o1 = Math.max(o1 - 0.02f, 0.5f);
        }
        start.setPosition(-10 + 10 * o1,0);
        musicRoom.setPosition(-10 + 10 * o2,0);
        quit.setPosition(-10 + 10 * o3,0);
        start.draw(batch,o1);
        musicRoom.draw(batch, o2);
        quit.draw(batch, o3);
    }

    int currentKey;
    public void updateKeyboard(){
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && justChangeTimer == 0){
            if(choiceId == 0)
                choiceId = 2;
            else
                choiceId--;
            justChangeTimer = 10;

        } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && justChangeTimer == 0){
            if(choiceId == 2)
                choiceId = 0;
            else
                choiceId ++;
            justChangeTimer = 10;
        } else{
            currentKey = 0;
        }

        if(justChangeTimer >= 1)
            justChangeTimer -- ;
    }


}
