package com.willem.traffic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.willem.screens.GameScreen;

public class Traffic extends Game {
    @Override
    public void create() {
        Gdx.app.log("traffic", "created");
        setScreen(new GameScreen());
    }
}
