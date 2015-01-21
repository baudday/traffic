package com.willem.tHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 * Created by wellis on 12/17/2014.
 */
public class AssetLoader {

    public static Texture bg, bgEdge;
    public static Texture[] carTextures, logo;
    public static TextureRegion[] textures;
    public static BitmapFont font;
    public static Skin uiSkin;
    public static Sound crash, woosh, beep;
    public static Preferences prefs;
    public static TextButtonStyle btnStyle;
    public static LabelStyle labelStyle;
    public static String prefix = "data/";
    public static String suffix = ".png";

    public static float btnWidth, btnHeight;

    public static void load() {
        String[] carFiles = {
                "redcar", "yellowcar",
                "policecar", "greencar",
                "bluecar", "redtruck",
                "yellowtruck", "greentruck",
                "bluetruck"
        };
        carTextures = new Texture[carFiles.length];
        textures = new TextureRegion[carTextures.length];

        bg = new Texture(Gdx.files.internal(prefix + "road.png"));
        bg.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        bg.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        bgEdge = new Texture(Gdx.files.internal(prefix + "roadedge.png"));
        bgEdge.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        bgEdge.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        logo = new Texture[3];
        for (int i = 0; i < logo.length; i++) {
            logo[i] = new Texture(Gdx.files.internal(prefix + "ui/logo" + i + ".png"));
            logo[i].setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(prefix + "ui/font/slkscr.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = Math.round(38 * Gdx.graphics.getDensity());
        font = generator.generateFont(parameter);

        uiSkin = new Skin(Gdx.files.internal(prefix + "ui/uiskin.json"));

        btnStyle = new TextButtonStyle(uiSkin.getDrawable("btn"), uiSkin.getDrawable("btn"), uiSkin.getDrawable("btn"), font);
        btnWidth = Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 8);
        btnHeight = btnWidth / 4;
        labelStyle = new LabelStyle(font, uiSkin.getColor("white"));

        crash = Gdx.audio.newSound(Gdx.files.internal(prefix + "sound/crash.mp3"));
        woosh = Gdx.audio.newSound(Gdx.files.internal(prefix + "sound/woosh.mp3"));
//        beep = Gdx.audio.newSound(Gdx.files.internal(prefix + "sound/beep.mp3"));

        prefs = Gdx.app.getPreferences("traffic");
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

        for (int i = 0; i < carTextures.length; i++) {
            carTextures[i] = new Texture(Gdx.files.internal(prefix + carFiles[i] + suffix));
            carTextures[i].setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }

        for (int i = 0; i < textures.length; i++) {
            textures[i] = new TextureRegion(carTextures[i], 0, 0, 30, 48);
            textures[i].flip(false, true);
        }

    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        for (int i = 0; i < carTextures.length; i++) {
            carTextures[i].dispose();
        }

        font.dispose();
        crash.dispose();
    }

}
