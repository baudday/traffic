package com.willem.traffic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.willem.screens.GameScreen;
import com.willem.screens.StartScreen;
import com.willem.tHelpers.AssetLoader;

public class Traffic extends Game {
    @Override
    public void create() {
        Gdx.app.log("traffic", "created");
        AssetLoader.load();
        setScreen(new StartScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
