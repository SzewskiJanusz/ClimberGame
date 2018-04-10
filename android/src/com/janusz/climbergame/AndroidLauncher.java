package com.janusz.climbergame;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


public class AndroidLauncher extends AndroidApplication implements AdService{


    private InterstitialAd mInterstitialAd;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		super.onCreate(savedInstanceState);
		final AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;

        MobileAds.initialize(this, "ca-app-pub-9491149384478370~6013353900");

		RelativeLayout layout = new RelativeLayout(this);

		View gameView = initializeForView(new ClimberGame(this), config);
		AdView adView = new AdView(this);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setVisibility(View.VISIBLE);
		adView.setAdUnitId("ca-app-pub-9491149384478370/7797564085");
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		RelativeLayout.LayoutParams adParams =
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);

		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);


		layout.addView(gameView);

		layout.addView(adView, adParams);
		setContentView(layout);
        mInterstitialAd = new InterstitialAd(this);
		//ca-app-pub-9491149384478370/7828142227
        mInterstitialAd.setAdUnitId("ca-app-pub-9491149384478370/7828142227");
	}

    @Override
    public boolean isInterstitialLoaded()
    {
        return mInterstitialAd.isLoaded();
    }

    @Override
    public void showInterstitial()
    {
        runOnUiThread(new Runnable() {
            public void run() {
                if (mInterstitialAd.isLoaded())
                    mInterstitialAd.show();
            }
        });
    }

	@Override
    public void loadIntersitialAd(){
		runOnUiThread(new Runnable() {
			public void run() {
				if(!isInterstitialLoaded())
				{
					AdRequest interstitialRequest = new AdRequest.Builder().build();
					mInterstitialAd.loadAd(interstitialRequest);
				}
			}
		});
    }
}
