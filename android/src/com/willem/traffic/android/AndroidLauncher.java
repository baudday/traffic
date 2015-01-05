package com.willem.traffic.android;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.willem.tHelpers.IActivityRequestHandler;
import com.willem.traffic.Traffic;

public class AndroidLauncher extends AndroidApplication implements IActivityRequestHandler{

	protected AdView adView;
	protected View gameView;

	private static final String AD_UNIT_ID = "ca-app-pub-1312638926628210/2413959024";
	private static final String GOOGLE_PLAY_URL = "";
	private final int SHOW_ADS = 1;
	private final int HIDE_ADS = 0;

	protected Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
				case SHOW_ADS:
				{
					adView.setVisibility(View.VISIBLE);
					break;
				}
				case HIDE_ADS:
				{
					adView.setVisibility(View.GONE);
					break;
				}
			}
		}
	};

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

		// Do the stuff that initialize() would do for you
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		RelativeLayout layout = new RelativeLayout(this);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		layout.setLayoutParams(params);

		AdView admobView = createAdView();
		layout.addView(admobView);
		View gameView = createGameView(cfg);
		layout.addView(gameView);

		setContentView(layout);
		startAdvertising(admobView);
	}

	@Override
	public void	showAds(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
	}

	private AdView createAdView() {
		adView = new AdView(this);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId(AD_UNIT_ID);
		adView.setId(R.id.ad_id); // this is an arbitrary id, allows for relative positioning in createGameView()
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		adView.setLayoutParams(params);
		adView.setBackgroundColor(Color.BLACK);
		return adView;
	}

	private void destroyAdView() {
		adView.destroy();
	}

	private View createGameView(AndroidApplicationConfiguration cfg) {
		gameView = initializeForView(new Traffic(this), cfg);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		gameView.setLayoutParams(params);
		return gameView;
	}

	private void startAdvertising(AdView adView) {
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
		adView.bringToFront();
		adView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (adView != null) adView.resume();
	}

	@Override
	public void onPause() {
		if (adView != null) adView.pause();
		super.onPause();
	}

	@Override
	public void onDestroy() {
		if (adView != null) adView.destroy();
		super.onDestroy();
	}
}
