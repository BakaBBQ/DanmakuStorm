package com.github.bakabbq;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.bakabbq.screens.TitleScreen;

/**
 * Created by LBQ on 7/4/14.
 */
public class DanmakuGame extends Game {
    public SpriteBatch batch;
    public AssetManager assetManager;
    public FitViewport viewport;
    public OrthographicCamera camera;
    public void create(){
        batch = new SpriteBatch();
        assetManager = new AssetManager();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);
        viewport = new FitViewport(640, 480, camera);

        //Main
        this.setScreen(new TitleScreen(this));
    }

    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        super.render();
    }

    public void dispose(){
        batch.dispose();
    }
}
