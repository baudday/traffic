package com.willem.gameworld;

import com.willem.gameobjects.Car;
import com.willem.gameobjects.Freeway;

import java.util.ArrayList;

/**
 * Created by wellis on 12/5/2014.
 */
public class GameWorld {

    private Freeway freeway;

    private GameState currentState;

    public enum GameState {
        READY, RUNNING, GAMEOVER
    }

    public GameWorld(int gameHeight) {
        currentState = GameState.READY;
        freeway = new Freeway(gameHeight);
    }

    public void update(float delta) {
        switch (currentState) {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
            default:
                updateRunning(delta);
                break;
        }
    }

    private void updateReady(float delta) {
        currentState = GameState.RUNNING;
    }

    private void updateRunning(float delta) {
        if (freeway.collision()) {
            freeway.stop();
            currentState = GameState.GAMEOVER;
        }
        else
            freeway.update(delta);
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        freeway.onRestart();
        currentState = GameState.READY;
    }

    public ArrayList<Car> getCars() {
        return freeway.getCars();
    }
}
