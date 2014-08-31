package com.github.bakabbq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.github.bakabbq.shooters.bosses.ThBoss;
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
        Gdx.app.log("filename", filename);
        this.filename = filename;
        ini = new JIniFile(filename);
        bossName = ini.ReadString("ScriptProperties", "boss_name","");
        spellName = ini.ReadString("ScriptProperties", "spell_name","");
        scriptAuthor = ini.ReadString("ScriptProperties", "author","");
    }
}
