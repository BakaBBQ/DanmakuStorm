package com.github.bakabbq;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.bakabbq.background.ThBackground;
import com.github.bakabbq.screens.PracticeScreen;
import com.github.bakabbq.screens.TitleScreen;
import com.github.bakabbq.shooters.bosses.testsanae.TestSanae;
import com.github.bakabbq.shooters.bosses.testsanae.TestSpellCard;

/**
 * Created by LBQ on 7/4/14.
 */
public class DanmakuGame extends Game {
    public SpriteBatch batch;
    public SpriteBatch uiBatch;
    public AssetManager assetManager;
    public FitViewport viewport;
    public OrthographicCamera camera;

    public Screen currentScreen;
    public void create(){
        batch = new SpriteBatch();
        uiBatch = new SpriteBatch();
        assetManager = new AssetManager();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);
        viewport = new FitViewport(640, 480, camera);


        PracticeScreen screen;
        DanmakuScene scene = new DanmakuScene(TestSanae.class, TestSpellCard.class, ThBackground.class);

        currentScreen = new PracticeScreen(this, scene);
        //Main
        this.setScreen( (currentScreen) );
    }

    public void render() {
        camera.update();
        Gdx.gl.glViewport((int) 0, (int) 0,
                (int) 640, (int) 480);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        super.render();
    }

    public void dispose(){
        batch.dispose();
    }
}
