package com.willem.traffic.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.willem.tHelpers.AssetLoader;
import com.willem.tHelpers.IActivityRequestHandler;
import com.willem.tools.MyPacker;
import com.willem.traffic.Traffic;

public class DesktopLauncher implements IActivityRequestHandler {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Traffic";
        config.width = 272;
        config.height = 408;
		AssetLoader.prefix = "android/assets/data/";
		TexturePacker.process(AssetLoader.prefix + "ui", AssetLoader.prefix + "ui", "uiskin");
		new LwjglApplication(new Traffic(new DesktopLauncher()), config);
	}

	@Override
	public void showAds(boolean show) {

	}

	@Override
	public void signIn() {

	}

	@Override
	public void signOut() {

	}

	@Override
	public void rateGame() {

	}

	@Override
	public void submitScore(long score) {

	}

	@Override
	public void showScores() {

	}

	@Override
	public boolean isSignedIn() {
		return false;
	}
}
