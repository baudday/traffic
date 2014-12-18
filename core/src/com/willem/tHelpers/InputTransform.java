package com.willem.tHelpers;

import com.badlogic.gdx.Gdx;

/**
 * Created by wellis on 12/8/2014.
 */
public class InputTransform {

    private static float screenWidth = Gdx.graphics.getWidth();
    private static float screenHeight = Gdx.graphics.getHeight();

    public static float getCursorToModelX(int screenX, float cursorX)
    {
        Gdx.app.log("ScreenX: " + screenX, "CursorX: " + cursorX);
        return (screenX / screenWidth) * cursorX;
    }

    public static float getCursorToModelY(int screenY, float cursorY)
    {
        Gdx.app.log("ScreenY: " + screenY, "CursorY: " + cursorY);
        return (screenY / screenHeight) * cursorY;
    }
}
