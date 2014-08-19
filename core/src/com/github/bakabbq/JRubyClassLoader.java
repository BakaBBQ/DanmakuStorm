package com.github.bakabbq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import org.jruby.embed.ScriptingContainer;

/**
 * Created by LBQ on 8/19/14.
 *
 * Integration layer of JRuby
 */
public class JRubyClassLoader {
    private ScriptingContainer container = new ScriptingContainer();
    public Object loadClass(String filename){
        String content = Gdx.files.internal(filename).readString();
        container.setClassLoader(JRubyClassLoader.class.getClassLoader());
        Object objectClazz = container.runScriptlet(content);
        System.out.println(objectClazz);
        Object actualObject = container.callMethod(objectClazz, "new", new Object[]{});
        return actualObject;
    }
}
