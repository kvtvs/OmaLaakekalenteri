package com.example.omalaakekalenteri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DisplayDate extends AppCompatActivity {
    private int year, month, day;
    private TextView textViewDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_date);
        textViewDate = findViewById(R.id.textViewDate);

        Bundle bundle = getIntent().getExtras();
        year = bundle.getInt("year", 0);
        month = bundle.getInt("month", 0);
        day = bundle.getInt("day", 0);

        textViewDate.setText(day + "." + month + "." + year);
    }
}