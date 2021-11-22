package com.dcpappsolution.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityMale extends AppCompatActivity {

    private Button btnFurther;

    private String year;
    private String month;
    private String day;


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

    }
    public void openActivityFurtherAge(){
        Intent intent = new Intent(this, AgeCalculate.class);
        intent.putExtra("key_birth_year", year);
        intent.putExtra("key_birth_month", month);
        intent.putExtra("key_birth_day", day);
        startActivity(intent);
    }
}