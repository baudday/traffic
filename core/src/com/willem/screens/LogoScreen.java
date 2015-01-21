package com.willem.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.willem.tHelpers.AssetLoader;
import com.willem.tHelpers.IActivityRequestHandler;
import com.willem.traffic.Traffic;

/**
 * Created by wellis on 1/20/2015.
 */
public class LogoScreen implements Screen {
    private Game game;
    private Stage stage;
    private OrthographicCamera cam;
    private SpriteBatch batcher;
    private IActivityRequestHandler myRequestHandler;
    private float gameHeight;
    private float runTime;

    public LogoScreen(Game g) {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        gameHeight = screenHeight / (screenWidth / gameWidth);

        game = g;
        stage = new Stage();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 136, gameHeight);
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        runTime = 0;
        myRequestHandler = Traffic.handler;
        myRequestHandler.showAds(false);
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        Gdx.gl.glClearColor(0xaa/255f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int frame = (int) Math.floor(runTime % 3);

        batcher.begin();
        batcher.draw(AssetLoader.logo[frame], 3, (gameHeight / 2) - 19.5f, 130, 39);
        batcher.end();
        if (runTime > 3) {
            game.setScreen(new StartScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
