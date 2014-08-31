package com.github.bakabbq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.github.bakabbq.tools.JIniFile;

/**
 * Created by LBQ on 8/30/14.
 *
 * ini files looks more lightweight to me...
 */
public class ScriptDescription {
    public String bossName;
    public String spellName;
    public String scriptAuthor;

    public String filename;

    public JIniFile ini;
    public ScriptDescription(){
    }

    public ScriptDescription(String filename){
        JsonReader reader = new JsonReader();
        String config = Gdx.files.internal(filename).readString();
        JsonValue value = reader.parse(config);
        bossName = value.getString("boss_name", "Kanako");
        spellName = value.getString("spell_name", "");
        scriptAuthor = value.getString("author", "NoName");
        this.filename = filename;
    }
}
