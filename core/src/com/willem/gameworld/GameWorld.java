package com.willem.gameworld;

import com.willem.gameobjects.Car;
import com.willem.gameobjects.Freeway;
import com.willem.tHelpers.AssetLoader;
import com.willem.traffic.Traffic;

import java.util.ArrayList;

/**
 * Created by wellis on 12/5/2014.
 */
public class GameWorld {

    private Freeway freeway;

    private GameState currentState;

    private long score;

    public enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld(int gameHeight) {
        score = 0;
        currentState = GameState.READY;
        freeway = new Freeway(gameHeight);
    }

    public void update(float delta) {
        switch (currentState) {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;

            default:
                break;

        }
    }

    private void updateReady(float delta) {
        currentState = GameState.RUNNING;
    }

    private void updateRunning(float delta) {
        if (freeway.collision()) {
            AssetLoader.crash.play();
            freeway.stop();
            currentState = GameState.GAMEOVER;
            if (Traffic.handler.isSignedIn()) {
                Traffic.handler.submitScore(score);
            }
            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
        else {
            freeway.update(delta);
            score += delta*1000;
        }
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        score = 0;
        currentState = GameState.READY;
        freeway.onRestart();
    }

    public ArrayList<Car> getCars() {
        return freeway.getCars();
    }

    public long getScore() {
        return score;
    }
}
