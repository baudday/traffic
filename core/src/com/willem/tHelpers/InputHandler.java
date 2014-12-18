package com.willem.tHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.willem.gameobjects.Car;

import java.util.ArrayList;

/**
 * Created by wellis on 12/8/2014.
 */
public class InputHandler implements InputProcessor {

    private ArrayList<Car> myCars;
    private Car activeCar;
    private Vector2 lastTouch = new Vector2();
    private int gameWidth;
    private int gameHeight;

    public InputHandler(ArrayList<Car> cars, int gameWidth, int gameHeight) {
        myCars = new ArrayList<Car>(6);
        myCars = cars;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float pointerX = InputTransform.getCursorToModelX(gameWidth, screenX);
        float pointerY = InputTransform.getCursorToModelY(gameHeight, screenY);
        for (int i = 0; i < myCars.size(); i++) {
            if (myCars.get(i).contains(pointerX, pointerY)) {
                activeCar = myCars.get(i);
                activeCar.setActive(true);
                break;
            }
        }
        lastTouch.set(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 newTouch = new Vector2(screenX, screenY);

        if (activeCar != null && activeCar.getActive()) {
            if(newTouch.x > lastTouch.x) {
                activeCar.changeLanes("right");
                Gdx.app.log("GameScreen", "right");
            }

            else if (newTouch.x < lastTouch.x) {
                activeCar.changeLanes("left");
                Gdx.app.log("GameScreen", "left");
            }

            activeCar.setActive(false);
            return true;
        }


        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
