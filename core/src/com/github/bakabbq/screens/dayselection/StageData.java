package com.github.bakabbq.screens.dayselection;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.github.bakabbq.ScriptDescription;

import java.io.File;

/**
 * Created by LBQ on 8/30/14.
 *
 * Json Format Stage Datas
 */
public class StageData {
    public String name;
    public String location;
    public Array<ScriptDescription> scripts;
    public StageData(String path, int nothing){
        this(Gdx.files.getFileHandle(path, Files.FileType.Absolute).readString());
    }

    public StageData(String str) {
        scripts = new Array<ScriptDescription>();
        if (str == null)
            Gdx.app.log("json", "Null String");
        JsonReader reader = new JsonReader();
        JsonValue value = reader.parse(str);
        this.name = value.getString("name");
        this.location = value.getString("location");
        JsonValue scriptValues = value.get("scripts");
        Gdx.app.log("Json", scriptValues.toString());
        String[] scriptNames = scriptValues.asStringArray();
        for (String sStr : scriptNames) {
            Gdx.app.log("json", sStr);
        }
        for (String singleScriptName : scriptNames) {
            ScriptDescription sd = new ScriptDescription("scripts/" + singleScriptName + ".json");
            scripts.add(sd);
        }
    }

    public static Array<StageData> parseAllStages(File folder) {
        Array<StageData> results = new Array<StageData>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                parseAllStages(fileEntry);
            } else {
                results.add(new StageData(fileEntry.getAbsolutePath(), 0));
            }
        }
        return results;
    }
}
