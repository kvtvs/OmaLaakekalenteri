package com.example.omalaakekalenteri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    private Button calendarButton;
    private Button medicineListButton;
    private TextView otsikko;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        otsikko = (TextView) findViewById(R.id.textViewHeader);

        medicineListButton = (Button) findViewById(R.id.buttonLaakelista);
        medicineListButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMedicineList();
            }
        });


        calendarButton = (Button) findViewById(R.id.calanderButton);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityCalendar30();
            }
        });
    }



    public void openActivityCalendar30(){
        Intent intent = new Intent(this, calendar30.class);
        startActivity(intent);
    }

    public void openMedicineList() {
        Intent intent = new Intent(this, DisplayMedicineList.class);
        startActivity(intent);
    }

}