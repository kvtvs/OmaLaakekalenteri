package com.example.omalaakekalenteri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
/**
 * Activity class with CalendarView
 * @author Mikko Räikkönen, Mikael Alakari
 *
 */

public class calendar30 extends AppCompatActivity {

    private Button mainScreen, selectDay;
    private CalendarView calendar30;
    private final String TAG = "MED_";
    private int chosenYear, chosenMonth, chosenDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar30);


        mainScreen = (Button) findViewById(R.id.mainScreenButton);

        mainScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        calendar30 = (CalendarView) findViewById(R.id.calendar30);
        calendar30.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //etsii painetun päivän datan
                chosenYear = year;
                chosenMonth = month + 1;
                chosenDay = dayOfMonth;


            }
        });
        selectDay = findViewById(R.id.buttonSelectDay);
        selectDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayDate();
            }
        });
    }

    /**
     * Opens MainActivity activity
     */
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Opens DisplayDate activity and sends chosen date
     */
    public void openDisplayDate(){
        Intent intent = new Intent(this, DisplayDate.class);


        Bundle bundle = new Bundle();

        bundle.putInt("year", chosenYear);
        bundle.putInt("month", chosenMonth);
        bundle.putInt("day", chosenDay);
        //Log.d(TAG, "" + chosenDay + " " + chosenMonth + " " + chosenYear);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}