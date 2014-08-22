package com.github.bakabbq;

import com.badlogic.gdx.Gdx;
import com.github.bakabbq.shooters.BulletShooter;

/**
 * Created by LBQ on 8/20/14.
 *
 * BulletScript - small scriptlets written in ruby
 */
public class BulletScript {
    public boolean errored;
    public String oriFilename;
    Object scriptObject;
    public BulletScript(Object scriptObject){
        this.scriptObject = scriptObject;
    }

    public static BulletScript load(String filename){
            BulletScript newScript = new BulletScript(JRubyClassLoader.loadClass(filename));
            newScript.oriFilename = filename;
            return newScript;
    }

    public void setOwner(BulletShooter shooter){JRubyClassLoader.callMethod(scriptObject,"owner=", shooter);}

    public void update(){
        if(errored)
            return;
        try{
            JRubyClassLoader.callMethod(scriptObject,"update");
        }
        catch(Exception e){
            Gdx.app.log("JRuby", "Error when updating script", e);
            this.errored = true;
        }
    }
}
