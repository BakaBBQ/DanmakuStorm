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
    }

    public void update(SpriteBatch batch){
        updateKeyboard();
        float o1 = 0.7f;
        float o2 = 0.7f;
        float o3 = 0.7f;
        if(choiceId == 0)
            o1 = 1f;
        else if(choiceId == 1)
            o2 = 1f;
        else if(choiceId == 2)
            o3 = 1f;
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
