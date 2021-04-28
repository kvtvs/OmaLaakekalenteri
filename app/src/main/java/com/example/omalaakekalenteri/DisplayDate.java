package com.example.omalaakekalenteri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DisplayDate extends AppCompatActivity {
    private int year, month, day;
    private TextView textViewDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_date);
        textViewDate = findViewById(R.id.textViewDate);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        String[] split = formattedDate.split("-");
        int defaultDay = Integer.valueOf(split[0]);
        int defaultMonth = Integer.valueOf(split[1]);
        int defaultYear = Integer.valueOf(split[2]);

        Log.d("MED_", formattedDate);
        Bundle bundle = getIntent().getExtras();
        year = bundle.getInt("year", 0);
        month = bundle.getInt("month", 0);
        day = bundle.getInt("day", 0);
        Log.d("MED_", "" + defaultYear);
        if (day != 0) {
            textViewDate.setText(day + "." + month + "." + year);
        } else {
            textViewDate.setText(defaultDay + "." + defaultMonth + "." + defaultYear);
        }

    }
}