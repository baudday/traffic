package com.willem.tHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by wellis on 12/22/2014.
 */
public class BackgroundHelper {

    public static void render(int scroll, SpriteBatch batcher) {
        int gameHeight = Gdx.graphics.getHeight() / (Gdx.graphics.getWidth() / 136);
        batcher.begin();
        batcher.enableBlending();
        batcher.draw(AssetLoader.bgEdge, 0, 0, 0, scroll, 34, gameHeight);
        batcher.draw(AssetLoader.bg, 34, 0, 0, scroll, 68, gameHeight);
        //                               x,   y, width, height,     srcX, srcY    srcWidth, srcHeight
        batcher.draw(AssetLoader.bgEdge, 102, 0, 34,    gameHeight, 0,    scroll, 34,       gameHeight, true, false);
        batcher.end();
    }
}
