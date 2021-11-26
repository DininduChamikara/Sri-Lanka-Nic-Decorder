package com.dcpappsolution.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button btnTemp;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        button = (Button) findViewById(R.id.start_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        // AdMob Advertising
        MobileAds.initialize(MainActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

//        adView = (AdView) findViewById(R.id.adView);
////        AdSize adSize = new AdSize(300, 50);
//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        adView.loadAd(adRequest);
//
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
//        // TODO: Add adView to your view hierarchy.

    }
    public void openActivity2(){
        Intent intent = new Intent(this, MainActivity2_page2.class);
        startActivity(intent);
    }

}