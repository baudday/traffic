package com.willem.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.willem.gameobjects.Car;
import com.willem.tHelpers.*;

import java.util.ArrayList;

/**
 * Created by wellis on 12/5/2014.
 */
public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private Stage stage;
    private ReplayDialog dialog;

    private Label scoreLabel;

    int gameHeight, midPointY, scroll;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        myWorld = world;
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        stage = new Stage();

        scoreLabel = new Label("0", AssetLoader.labelStyle);

        Table table = new Table(AssetLoader.uiSkin);
        table.top();
        table.row();
        table.add(scoreLabel);
        table.setFillParent(true);

        stage.addActor(table);
        Window.WindowStyle windowStyle = new Window.WindowStyle(AssetLoader.font, AssetLoader.uiSkin.getColor("white"), null);
        dialog = new ReplayDialog("Game Over", windowStyle);
        dialog.setWorld(myWorld);

        GestureDetector gameInputProcessor = new GestureDetector(new MyGestureListener(myWorld, cam));
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(gameInputProcessor);

        Gdx.input.setInputProcessor(multiplexer);
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        BackgroundHelper.render(scroll, batcher);

        if (myWorld.isGameOver() || myWorld.isHighScore()) {
            if (myWorld.isHighScore())
                dialog.setHighScore("" + myWorld.getScore());
            dialog.show(stage);
        } else {
            scroll += 5;
        }


        ArrayList<Car> cars = myWorld.getCars();

        for (int i = 0; i < cars.size(); i++) {
            drawCar(cars.get(i));
        }

        scoreLabel.setText("" + myWorld.getScore());

        stage.act();
        stage.draw();
    }

    private void drawCar(Car car) {
        TextureRegion carTexture = AssetLoader.textures[car.getColor()];
        batcher.begin();
        batcher.enableBlending();
        batcher.draw(carTexture,
                car.getX(), car.getY(), car.getWidth(), car.getHeight());
        batcher.end();
    }
}
