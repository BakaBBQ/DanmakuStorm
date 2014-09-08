package com.github.bakabbq.datas;

import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

/**
 * Created by LBQ on 8/18/14.
 * <p/>
 * GameData, get all the high scores, unlocked stages and etc.
 */
public class GameData {
    private static GameData instance;
    public HashMap<String, ScoreBoard> scoreBoards;
    public Array<Short> unlockedStages;

    private GameData() {
        scoreBoards = new HashMap<String, ScoreBoard>();
        unlockedStages = new Array<Short>(50);
    }

    public static GameData getInstance() {
        if (instance == null)
            instance = new GameData();
        return instance;
    }

    public ScoreBoard getScoreBoard(String stageName) {
        if (!scoreBoards.containsKey(stageName))
            scoreBoards.put(stageName, new ScoreBoard());
        return scoreBoards.get(stageName);
    }
}
