package com.willem.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.willem.tHelpers.AssetLoader;
import com.willem.tHelpers.BackgroundHelper;
import com.willem.tHelpers.IActivityRequestHandler;
import com.willem.traffic.Traffic;

/**
 * Created by wellis on 12/22/2014.
 */
public class StartScreen implements Screen {

    private Game game;
    private Stage stage;
    private OrthographicCamera cam;
    private SpriteBatch batcher;
    private int scroll;
    private IActivityRequestHandler myRequestHandler;

    public StartScreen(Game g) {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        game = g;
        myRequestHandler = Traffic.handler;
        stage = new Stage();
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        scroll = 0;
        Gdx.input.setInputProcessor(stage);

        Skin skin = AssetLoader.uiSkin;
        float btnWidth = screenWidth - (screenWidth / 4);
        float btnHeight = btnWidth / 4;
        TextButton btnStart = new TextButton("Play", AssetLoader.btnStyle);
        btnStart.setPosition((screenWidth / 2) - (btnWidth / 2), (screenHeight / 2) - (btnHeight / 2));
        btnStart.setSize(btnWidth, btnHeight);
        btnStart.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int pointer, int button) {
                btnStartClicked();
            }
        });

        TextButton btnLeaderboard = new TextButton("Leaderboard", AssetLoader.btnStyle);
        btnLeaderboard.setPosition((screenWidth / 2) - (btnWidth / 2), (screenHeight / 2) - (btnHeight * 2));
        btnLeaderboard.setSize(btnWidth, btnHeight);
        btnLeaderboard.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int pointer, int button) {
                btnLeaderboardClicked();
                super.touchUp(e, x, y, pointer, button);
            }
        });

        stage.addActor(btnStart);
        stage.addActor(btnLeaderboard);

        myRequestHandler.showAds(true);
    }

    public void btnStartClicked() {
        myRequestHandler.showAds(false);
        game.setScreen(new GameScreen());
    }

    public void btnLeaderboardClicked() {
        myRequestHandler.showScores();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        scroll += 5;
        BackgroundHelper.render(scroll, batcher);

        stage.act(delta);
        stage.draw();
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
