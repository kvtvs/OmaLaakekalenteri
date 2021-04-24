package com.example.omalaakekalenteri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Instant;

public class DisplayMedicine extends AppCompatActivity {
    private final String TAG = "MED_";
    private int medicineNumber;

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
        medicineNumber = bundle.getInt("medicineNumber");


        TextView textViewName = findViewById(R.id.textViewMedicineName);
        TextView textViewDosage = findViewById(R.id.textViewMedicineDosage);
        TextView textViewActiveIngredient = findViewById(R.id.textViewMedicineActiveIngredient);
        TextView textViewTimesADay = findViewById(R.id.textViewMedicineTimesADay);
        TextView textViewQuantity = findViewById(R.id.textViewMedicineQuantity);
        Button buttonRemoveMedicine = findViewById(R.id.buttonRemoveMedicine);

        textViewName.setText(name);
        textViewDosage.setText(dosage + " mg");
        textViewActiveIngredient.setText(activeIngredient);
        textViewTimesADay.setText("Monta kertaa päivässä: " + timesADay);
        textViewQuantity.setText("Pillereitä jäljellä: " + quantity);

        buttonRemoveMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedicineList.getInstance().getMedicines().remove(medicineNumber);
                Intent intent = new Intent(DisplayMedicine.this, DisplayMedicineList.class);
                startActivity(intent);
            }
        });

    }

}