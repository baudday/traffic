package com.willem.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.willem.gameobjects.Car;

import java.util.ArrayList;

/**
 * Created by wellis on 12/5/2014.
 */
public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    public GameRenderer(GameWorld world) {
        myWorld = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, 204);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    public void render() {

        ArrayList<Car> cars = myWorld.getCars();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (int i = 0; i < cars.size(); i++) {
            drawCar(cars.get(i));
        }
    }

    public OrthographicCamera getCam() {
        return cam;
    }

    private void drawCar(Car car) {
        // Draw filled rectangle
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
        shapeRenderer.rect(car.getX(), car.getY(), car.getWidth(), car.getHeight());
        shapeRenderer.end();

        // Draw outline
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
        shapeRenderer.rect(car.getX(), car.getY(),
                car.getWidth(), car.getHeight());
        shapeRenderer.end();
    }
}
