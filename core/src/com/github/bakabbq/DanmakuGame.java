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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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
    public OrthographicCamera camera;

    public FontBank fontBank;

    private static final int VIRTUAL_WIDTH = 640;
    private static final int VIRTUAL_HEIGHT = 480;
    private static final float ASPECT_RATIO =
            (float) VIRTUAL_WIDTH / (float) VIRTUAL_HEIGHT;

    Rectangle viewport;

    public Screen currentScreen;
    public void create(){
        batch = new SpriteBatch();
        uiBatch = new SpriteBatch();
        assetManager = new AssetManager();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);

        fontBank = new FontBank();


        PracticeScreen screen;
        DanmakuScene scene = new DanmakuScene(TestSanae.class, TestSpellCard.class, ThBackground.class);

        boolean titleDebug = false;
        if(titleDebug)
            currentScreen = new TitleScreen(this);
        else
            currentScreen = new PracticeScreen(this, scene);

        //currentScreen = new TitleScreen(this);
        //Main
        this.setScreen( (currentScreen) );
    }

    public void render() {
        camera.update();
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                (int) viewport.width, (int) viewport.height);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        super.render();
    }

    @Override
    public void resize(int width, int height){
        float aspectRatio = (float) width / (float) height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);
        if (aspectRatio > ASPECT_RATIO) {
            scale = (float) height / (float) VIRTUAL_HEIGHT;
            crop.x = (width - VIRTUAL_WIDTH * scale) / 2f;
        } else if (aspectRatio < ASPECT_RATIO) {
            scale = (float) width / (float) VIRTUAL_WIDTH;
            crop.y = (height - VIRTUAL_HEIGHT * scale) / 2f;
        } else {
            scale = (float) width / (float) VIRTUAL_WIDTH;
        }

        float w = (float) VIRTUAL_WIDTH * scale;
        float h = (float) VIRTUAL_HEIGHT * scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
    }

    public void dispose(){
        batch.dispose();
    }
}
