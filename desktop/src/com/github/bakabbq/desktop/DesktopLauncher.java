package com.github.bakabbq.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.bakabbq.GdxGround;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Danmaku Test";
        config.width = 640;
        config.height = 480;
		new LwjglApplication(new GdxGround(), config);
	}
}
