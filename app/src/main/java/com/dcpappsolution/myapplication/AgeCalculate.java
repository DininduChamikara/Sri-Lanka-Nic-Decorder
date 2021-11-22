package com.dcpappsolution.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class AgeCalculate extends AppCompatActivity {

    private int temp;

    FloatingActionButton fabDate;
    TextView tvAge;
    TextView tvDate;
    TextView txtSelectDate;
    TextView txtBirthDate;

    // for calculated age output
    TextView txtYearsView;
    TextView txtMonthsView;
    TextView txtDaysView;

    private int yearCount;
    private int monthCount;
    private int dayCount;

    private int totaldays_birhtday;
    private String totaldays_difference;
    private String tdb;

    private int birth_year;
    private int birth_month;
    private int birth_day;

    private String birth_y;
    private String birth_m;
    private String birth_d;

    private String birthDate;

    private String selected_y;
    private String selected_m;
    private String selected_d;

    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculate);


        birth_y = getIntent().getStringExtra("key_birth_year");
        birth_m = getIntent().getStringExtra("key_birth_month");
        birth_d = getIntent().getStringExtra("key_birth_day");

        fabDate = findViewById(R.id.fabDatePicker);

        txtBirthDate = findViewById(R.id.txtBirthDate);
        txtSelectDate = findViewById(R.id.txtSelectDate);

        // for calculated age output
        txtYearsView = findViewById(R.id.txtYearsView);
        txtMonthsView = findViewById(R.id.txtMonthsView);
        txtDaysView = findViewById(R.id.txtDaysView);

        totaldays_birhtday = Integer.parseInt(birth_y)*12*30 + (Integer.parseInt(birth_m)-1)*30 + Integer.parseInt(birth_d);


        txtYearsView.setText(tdb);


        birthDate = " "+ birth_d + "/" + birth_m + "/" + birth_y+" ";
        txtBirthDate.setText(birthDate);

        fabDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int nYear = c.get(Calendar.YEAR);
                int nMonth = c.get(Calendar.MONTH);
                int nDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dateDialog = new DatePickerDialog(v.getContext(), datePickerListener, nYear, nMonth, nDay);
            //    dateDialog.getDatePicker().setMaxDate(new Date().getTime());
            //    dateDialog.getDatePicker().setMinDate(new Date().getTime());
                dateDialog.show();

            }
        });

    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);

            // testing is complete
            selected_y = String.valueOf(year);
            selected_m = String.valueOf(month+1);
            selected_d = String.valueOf(day);


            int defference = (year*30*12 + month*30 + day) - totaldays_birhtday;

            totaldays_difference = String.valueOf((year*12*30 + month*30 + day) - totaldays_birhtday );


            int yearCount = defference/360;
            int yearLeft = defference%360;

            int monthCount = yearLeft/30;
            int monthLeft = monthCount%30;

            int daysCount = 0;

            if(Integer.parseInt(selected_d)>=Integer.parseInt(birth_d)){
                daysCount = Integer.parseInt(selected_d)-Integer.parseInt(birth_d);
            }else{
                daysCount = 30-Integer.parseInt(birth_d)+Integer.parseInt(selected_d);
            }


            selectedDate = " "+ selected_d +"/"+ selected_m + "/" + selected_y +" ";
            txtSelectDate.setText(selectedDate);

            txtYearsView.setText(String.valueOf(yearCount));
            txtMonthsView.setText(String.valueOf(monthCount));
            txtDaysView.setText(String.valueOf(daysCount));
        }
    };


    int calculateAge(long date){
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR)-dob.get(Calendar.YEAR);

        if(today.get(Calendar.DAY_OF_MONTH)<dob.get(Calendar.DAY_OF_MONTH)){
            age--;
        }

        return age;
    }
}