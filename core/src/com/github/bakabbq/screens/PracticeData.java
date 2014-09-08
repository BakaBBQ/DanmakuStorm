package com.github.bakabbq.screens;

import com.github.bakabbq.screens.dayselection.StageData;

import java.io.File;

/**
 * Created by LBQ on 9/6/14.
 */
public class PracticeData {
    private static PracticeData instance;
    public StageData stageData;

    private PracticeData() {
        stageData = StageData.parseAllStages(new File("stage_infos")).first();
    }

    public static PracticeData getInstance() {
        if (instance == null)
            instance = new PracticeData();
        return instance;
    }
}
