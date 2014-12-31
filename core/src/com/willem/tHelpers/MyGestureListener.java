package com.willem.tHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.willem.gameobjects.Car;
import com.willem.gameworld.GameWorld;

import java.util.ArrayList;

/**
 * Created by wellis on 12/12/2014.
 */
public class MyGestureListener implements GestureDetector.GestureListener {

    private ArrayList<Car> myCars;
    private Car activeCar;
    private OrthographicCamera cam;
    private GameWorld myWorld;

    public MyGestureListener(GameWorld world, OrthographicCamera cam) {
        myWorld = world;
        myCars = myWorld.getCars();
        this.cam = cam;
    }

    @Override
    public boolean touchDown(float screenX, float screenY, int pointer, int button) {
        Vector3 touch = new Vector3();
        cam.unproject(touch.set(screenX, screenY, 0));

        for (int i = 0; i < myCars.size(); i++) {
            if (myCars.get(i).contains(touch.x, touch.y)) {
                activeCar = myCars.get(i);
                activeCar.setActive(true);
                break;
            }
        }

        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        if (myWorld.isGameOver())
            myWorld.restart();
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        if (activeCar != null && activeCar.getActive()) {
            if (velocityX > 0 &&
                Math.abs(velocityX) > Math.abs(velocityY) &&
                activeCar.getX() != 111) {
                activeCar.changeLanes("right");
            }

            else if (velocityX < 0 &&
                     Math.abs(velocityX) > Math.abs(velocityY) &&
                     activeCar.getX() != 9) {
                activeCar.changeLanes("left");
            }

            else if (velocityY > 0 &&
                     Math.abs(velocityY) > Math.abs(velocityX) &&
                     activeCar.getVelocityY() < -40) {
                activeCar.setVelocityY(activeCar.getVelocityY() + 40);
            }

            activeCar.setActive(false);
            return true;
        }


        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
