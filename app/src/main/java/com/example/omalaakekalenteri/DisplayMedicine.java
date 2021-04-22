package com.example.omalaakekalenteri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DisplayMedicine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_medicine);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String dosage = bundle.getString("dosage");
        String activeIngredient = bundle.getString("activeIngredient");
        String timesADay = bundle.getString("timesADay");
        String quantity = bundle.getString("quantity");

        TextView textViewName = findViewById(R.id.textViewMedicineName);
        TextView textViewDosage = findViewById(R.id.textViewMedicineDosage);
        TextView textViewActiveIngredient = findViewById(R.id.textViewMedicineActiveIngredient);
        TextView textViewTimesADay = findViewById(R.id.textViewMedicineTimesADay);
        TextView textViewQauntity = findViewById(R.id.textViewMedicineQuantity);

        textViewName.setText(name);
        textViewDosage.setText(dosage + " mg");
        textViewActiveIngredient.setText(activeIngredient);
        textViewTimesADay.setText("Monta kertaa päivässä: " + timesADay);
        textViewQauntity.setText("Pillereitä jäljellä: " + quantity);
    }
}