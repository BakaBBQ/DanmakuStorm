package com.github.bakabbq.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.bakabbq.DanmakuGame;
import com.github.bakabbq.GdxGround;
import com.github.bakabbq.shooters.players.DesktopControlHelper;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Danmaku Test";
        config.width = 640;
        config.height = 480;

        GdxGround ground = new GdxGround();
        DanmakuGame game = new DanmakuGame();
        ground.controlHelper = new DesktopControlHelper();
		//new LwjglApplication(ground, config);
        new LwjglApplication(game,config);
	}
}
