package com.github.bakabbq.screens;

import com.badlogic.gdx.Gdx;
import com.github.bakabbq.screens.dayselection.StageData;

/**
 * Created by LBQ on 9/6/14.
 */
public class PracticeData {
    private static PracticeData instance;
    public StageData stageData;

    private PracticeData() {
        Gdx.app.log("Folder?", "" + Gdx.files.internal("stage_infos/").isDirectory());
        stageData = StageData.parseAllStages(Gdx.files.internal("stage_infos/").file().getAbsoluteFile()).first();
    }

    public static PracticeData getInstance() {
        if (instance == null)
            instance = new PracticeData();
        return instance;
    }
}
