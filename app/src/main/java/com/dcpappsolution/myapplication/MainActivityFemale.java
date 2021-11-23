package com.dcpappsolution.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivityFemale extends AppCompatActivity {

    private Button btnFurtherF;

    private String year;
    private String month;
    private String day;

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_female);

        year = getIntent().getStringExtra("key_year");
        month = getIntent().getStringExtra("key_month");
        day = getIntent().getStringExtra("key_day");

        TextView txt_Month = (TextView) findViewById(R.id.txtMonthF);
        txt_Month.setText(month);

        TextView txt_Day = (TextView) findViewById(R.id.txtDayF);
        txt_Day.setText(day);

        TextView txt_Year = (TextView) findViewById(R.id.txtYearF);
        txt_Year.setText(year);

        btnFurtherF = (Button) findViewById(R.id.btnAgeCalF);
        btnFurtherF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityFurtherAgeF();
            }
        });

        // AdMob Advertising
        MobileAds.initialize(MainActivityFemale.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }
    public void openActivityFurtherAgeF(){
        Intent intent = new Intent(this, AgeCalculate.class);
        intent.putExtra("key_birth_year", year);
        intent.putExtra("key_birth_month", month);
        intent.putExtra("key_birth_day", day);
        startActivity(intent);
    }
}