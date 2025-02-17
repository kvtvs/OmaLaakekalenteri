package com.example.omalaakekalenteri;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Fecthes the data that will be showing in DisplayDateMedicine-activity
 * @author Mikael Alakari
 */

public class DisplayDateMedicineInfo extends AppCompatActivity {
    private TextView textViewDateMedName, textViewDateMedTimes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_date_medicine_info);
        textViewDateMedName = findViewById(R.id.textViewDateMedName);
        textViewDateMedTimes = findViewById(R.id.textViewDateMedTimes);
        Bundle bundle = getIntent().getExtras();

        String name = bundle.getString("name");
        String dosage = bundle.getString("dosage");
        String timesADay = bundle.getString("timesADay");

        textViewDateMedName.setText(name + " " + dosage + "mg");
        textViewDateMedTimes.setText("Päivän kokonaislääkemäärä: " + timesADay);
    }
}