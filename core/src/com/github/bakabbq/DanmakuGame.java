package com.github.bakabbq;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import com.github.bakabbq.background.ThBackground;
import com.github.bakabbq.datas.ScreenshotTaker;
import com.github.bakabbq.screens.PracticeScreen;
import com.github.bakabbq.screens.SigScreen;
import com.github.bakabbq.screens.TitleScreen;
import com.github.bakabbq.shooters.bosses.testsanae.TestSanae;
import com.github.bakabbq.shooters.bosses.testsanae.TestSpellCard;
import com.github.bakabbq.datas.FontBank;

/**
 * Created by LBQ on 7/4/14.
 */
public class DanmakuGame extends Game {
    private static final int VIRTUAL_WIDTH = 640;
    private static final int VIRTUAL_HEIGHT = 480;
    private static final float ASPECT_RATIO =
            (float) VIRTUAL_WIDTH / (float) VIRTUAL_HEIGHT;
    //I just realized that there is something called Singleton
    private static DanmakuGame instance;
    public SpriteBatch batch;
    public SpriteBatch uiBatch;
    public AssetManager assetManager;
    public OrthographicCamera camera;
    public FontBank fontBank;
    public Rectangle viewport;
    public Screen currentScreen;

    public boolean paused;

    public static DanmakuGame getInstance(){
        if(instance == null)
            instance = new DanmakuGame();
        return instance;
    }
    public void create(){
        batch = new SpriteBatch();
        uiBatch = new SpriteBatch();
        assetManager = new AssetManager();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);

        fontBank = new FontBank();


        //Object meow = JRubyClassLoader.loadClass("test.rb");



        PracticeScreen screen;
        DanmakuScene scene = new DanmakuScene(TestSanae.class, TestSpellCard.class, ThBackground.class);

        boolean titleDebug = false;
        if(titleDebug)
            currentScreen = new SigScreen(this);
        else
            currentScreen = new PracticeScreen(this, scene);

        //currentScreen = new TitleScreen(this);
        //Main
        this.setScreen( (currentScreen) );
    }

    public void render() {
        if(paused)
            return;
        camera.update();
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                (int) viewport.width, (int) viewport.height);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        super.render();
        updateScreenshot();
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

    public void switchToTitle(){
        currentScreen = new TitleScreen(this);
        this.setScreen(currentScreen);
    }
    public void updateScreenshot(){
        if(Gdx.input.isKeyPressed(Input.Keys.P))
            ScreenshotTaker.saveScreenshot();
    }

    public void dispose(){
        batch.dispose();
    }
}
