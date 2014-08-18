package com.github.bakabbq.datas;

import com.badlogic.gdx.utils.Array;

/**
 * Created by LBQ on 8/18/14.
 *
 * GameData, get all the high scores, unlocked stages and etc.
 */
public class GameData {
    public Array<ScoreBoard> scoreBoards;
    public Array<Short> unlockedStages;
    public GameData(){
        scoreBoards = new Array<ScoreBoard>(50);
        unlockedStages = new Array<Short>(50);
    }

    public ScoreBoard getScoreBoard(int day){
        return scoreBoards.get(day);
    }
}
