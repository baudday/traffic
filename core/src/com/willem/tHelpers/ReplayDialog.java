package com.willem.tHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.willem.gameworld.GameWorld;
import com.willem.traffic.Traffic;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by wellis on 12/25/2014.
 */
public class ReplayDialog extends Dialog {

    private GameWorld world;
    private IActivityRequestHandler myRequestHandler;

    private Label highScore;

    public ReplayDialog(String title, Skin skin) {
        super(title, skin);
    }

    public ReplayDialog(String title, Skin skin, String windowStyleName) {
        super(title, skin, windowStyleName);
    }

    public ReplayDialog(String title, WindowStyle windowStyle) {
        super(title, windowStyle);
    }

    {
        myRequestHandler = Traffic.handler;
        highScore = new Label("Best: " + AssetLoader.formatTime(AssetLoader.getHighScore()), AssetLoader.labelStyle);
        text(highScore).padTop(50);
        getButtonTable().defaults().size(AssetLoader.btnWidth, AssetLoader.btnHeight);
        button("Play Again", "again", AssetLoader.btnStyle);
        getButtonTable().row();
        button("Leaderboard", "leaderboard", AssetLoader.btnStyle);
    }

    public void setWorld(GameWorld world) {
        this.world = world;
    }

    public void setHighScore(String val) {
        highScore.setText("Best: " + val);
    }

    @Override
    public Dialog show (Stage stage) {
        myRequestHandler.showAds(true);
        show(stage, sequence(Actions.alpha(1), Actions.fadeIn(0.4f, Interpolation.fade)));
        setPosition(Math.round((stage.getWidth() - getWidth()) / 2), Math.round((stage.getHeight() - getHeight()) / 2));
        return this;
    }

    @Override
    public void hide () {
        hide(sequence(Actions.fadeOut(0f, Interpolation.fade), Actions.removeListener(ignoreTouchDown, true), Actions.removeActor()));
    }

    @Override
    protected void result(Object object) {
        if (object == "again") {
            myRequestHandler.showAds(false);
            world.restart();
        }

        else if (object == "leaderboard") {
            myRequestHandler.showScores();
        }

        super.result(object);
    }
}
