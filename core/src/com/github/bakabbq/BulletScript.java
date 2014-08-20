package com.github.bakabbq;

import com.github.bakabbq.shooters.BulletShooter;

/**
 * Created by LBQ on 8/20/14.
 *
 * BulletScript - small scriptlets written in ruby
 */
public class BulletScript {
    public static BulletScript load(String filename){
        return new BulletScript(JRubyClassLoader.loadClass(filename));
    }

    Object scriptObject;

    public BulletScript(Object scriptObject){
        this.scriptObject = scriptObject;
    }

    public void setOwner(BulletShooter shooter){JRubyClassLoader.callMethod(scriptObject,"owner=", shooter);}

    public void update(){
        JRubyClassLoader.callMethod(scriptObject,"update");
    }


}
