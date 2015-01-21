package com.willem.traffic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.willem.screens.GameScreen;
import com.willem.screens.LogoScreen;
import com.willem.screens.StartScreen;
import com.willem.tHelpers.AssetLoader;
import com.willem.tHelpers.IActivityRequestHandler;

public class Traffic extends Game {
    public static IActivityRequestHandler handler;

    public Traffic(IActivityRequestHandler handler) {
        super();
        Traffic.handler = handler;
    }

    @Override
    public void create() {
        Gdx.app.log("traffic", "created");
        AssetLoader.load();
        setScreen(new LogoScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
