package com.dcpappsolution.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity2_page2 extends AppCompatActivity {

    private Button btnfind;

    private String str_year;
    private String str_month;
    private String str_day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_page2);

        btnfind = (Button) findViewById(R.id.btn_find);
        btnfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity3();
            }
        });
    }
    public void openActivity3(){
        EditText nicNum =  (EditText) findViewById(R.id.nicNumInput);
        String nic_Num = nicNum.toString();


        boolean newId = false;
        boolean validId = false;

        if(nicNum.length()==10 || nicNum.length()==12){
            validId = true;
        }else{
            validId = false;
        }

        if(nicNum.length()==12 && validId == true){
            newId = true;
        }
        else if(nicNum.length()==10 && validId==true){
            newId = false;
        }

        int month[] = {31,29,31,30,31,30,31,31,30,31,30,31};

        String sex;
        int year = 0;

        int y;  // variable to calculate the year
        int md = 0; // variable for calculate month and data
        int tempMd; // temporary do changes in variable md
        int mon=0;  // variable for month
        int day;    // variable for date
        String temp_y = "";


        if(newId==false && validId == true){

        // these codes not working well
            y = Integer.parseInt(nicNum.getText().toString().substring(0, 2));
            md = Integer.parseInt(nicNum.getText().toString().substring(2, 5));

            year = 1900 + y;
        }
        else if(newId==true && validId == true){
            y = Integer.parseInt(nicNum.getText().toString().substring(0, 4));
            md = Integer.parseInt(nicNum.getText().toString().substring(4, 7));

            year = y;
        }

// correct code
        if(md>500){
            md = md-500;
            sex = "female";
        }else{
            sex = "male";
        }
        //end correct

        tempMd = md;
        // month
        for(int i=0; i<12; i++){
            if(tempMd <=month[i]){
                mon = i+1;
                break;
            }
            else{
                tempMd = tempMd - month[i];
            }
        }

        // date
        for(int i=0; i<mon-1; i++){
            md = md-month[i];
        }
        day = md;

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int age = currentYear - year;

        // finally should give correct values for them
        str_year =  String.valueOf(year);
        str_month = String.valueOf(mon);
        str_day = String.valueOf(day);



// correct down codes
        if(validId==true && sex.equalsIgnoreCase("male")){
            Intent intent = new Intent(this, MainActivityMale.class);
            intent.putExtra("key_year", str_year);
            intent.putExtra("key_month", str_month);
            intent.putExtra("key_day", str_day);
            startActivity(intent);

        }else if(validId==true && sex.equalsIgnoreCase("female")){
            Intent intent = new Intent(this, MainActivityFemale.class);
            intent.putExtra("key_year", str_year);
            intent.putExtra("key_month", str_month);
            intent.putExtra("key_day", str_day);
            startActivity(intent);
        }
        else{
            nicNum.setHint("Invalid NIC");
            nicNum.setText("");
            nicNum.setHintTextColor(Color.parseColor("red"));


//            nicNum.setText("Invalid NIC");
//            nicNum.setTextColor(Color.parseColor("red"));
        }

    }
}