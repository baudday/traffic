package com.willem.tHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * Created by wellis on 12/25/2014.
 */
public class DialogHelper extends Dialog {
    public DialogHelper(String title, Skin skin) {
        super(title, skin);
    }

    public DialogHelper(String title, Skin skin, String windowStyleName) {
        super(title, skin, windowStyleName);
    }

    public DialogHelper(String title, WindowStyle windowStyle) {
        super(title, windowStyle);
    }

    protected void result(Object object) {
        Gdx.app.log("Clicked", "clicked");
    }
}
