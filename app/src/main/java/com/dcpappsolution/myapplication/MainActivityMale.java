package com.dcpappsolution.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivityMale extends AppCompatActivity {

    private Button btnFurther;

    private String year;
    private String month;
    private String day;

    private AdView adView;

    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_male);

        year = getIntent().getStringExtra("key_year");
        month = getIntent().getStringExtra("key_month");
        day = getIntent().getStringExtra("key_day");


        TextView txt_Month = (TextView) findViewById(R.id.txtMonth);
        txt_Month.setText(month);

        TextView txt_Day = (TextView) findViewById(R.id.txtDay);
        txt_Day.setText(day);

        TextView txt_Year = (TextView) findViewById(R.id.txtYear);
        txt_Year.setText(year);


        btnFurther = (Button) findViewById(R.id.btnAgeCal);
        btnFurther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityFurtherAge();
            }
        });

        // AdMob Advertising
        MobileAds.initialize(MainActivityMale.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        adView = findViewById(R.id.adView);
        AdRequest b_adRequest = new AdRequest.Builder().build();
        adView.loadAd(b_adRequest);



        /////////// InterstitialAds
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,"ca-app-pub-6742529652813903/1133916489", adRequest,
            new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    // The mInterstitialAd reference will be null until
                    // an ad is loaded.
                    mInterstitialAd = interstitialAd;
                    Log.i("TAG", "onAdLoaded");
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    // Handle the error
                    Log.i("TAG", loadAdError.getMessage());
                    mInterstitialAd = null;
                }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivityMale.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
            }
        }, 2000);

    }
    public void openActivityFurtherAge(){
        Intent intent = new Intent(this, AgeCalculate.class);
        intent.putExtra("key_birth_year", year);
        intent.putExtra("key_birth_month", month);
        intent.putExtra("key_birth_day", day);
        startActivity(intent);
    }
}