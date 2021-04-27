package com.example.omalaakekalenteri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class DisplayMedicine extends AppCompatActivity implements RemoveMedicineDialog.RemoveMedicineDialogListener{
    private final String TAG = "MED_";
    private int medicineNumber;
    public final static String SHARED_PREFS = "sharedPrefs";
    public final static String LIST = "list";
    private TextView warningText;
    private TextView textViewQuantity;
    private String quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_medicine);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String dosage = bundle.getString("dosage");
        String activeIngredient = bundle.getString("activeIngredient");
        String timesADay = bundle.getString("timesADay");
        quantity = bundle.getString("quantity");
        medicineNumber = bundle.getInt("medicineNumber");

        warningText = (TextView) findViewById(R.id.textViewWarning);
        warningText.setVisibility(View.INVISIBLE);

        TextView textViewName = findViewById(R.id.textViewMedicineName);
        TextView textViewDosage = findViewById(R.id.textViewMedicineDosage);
        TextView textViewActiveIngredient = findViewById(R.id.textViewMedicineActiveIngredient);
        TextView textViewTimesADay = findViewById(R.id.textViewMedicineTimesADay);
        Button buttonRemoveMedicine = findViewById(R.id.buttonRemoveMedicine);
        Button buttonReturnToList = findViewById(R.id.buttonReturnToList);

        textViewQuantity = findViewById(R.id.textViewMedicineQuantity);

        textViewName.setText(name);
        textViewDosage.setText(dosage + " mg");
        textViewActiveIngredient.setText(activeIngredient);
        textViewTimesADay.setText("Monta kertaa päivässä: " + timesADay);




        buttonReturnToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayMedicine.this, DisplayMedicineList.class);
                startActivity(intent);
            }
        });

        buttonRemoveMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

            }
        });
    }


    public void openDialog(){
        RemoveMedicineDialog dialog = new RemoveMedicineDialog();
        dialog.show(getSupportFragmentManager(), "remove medicine dialog");
    }

    @Override
    public void onYesClicked() {
        MedicineList.getInstance().getMedicines().remove(medicineNumber);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MedicineList.getInstance().getMedicines());
        editor.putString(LIST, json);
        editor.apply();

        Intent intent = new Intent(DisplayMedicine.this, DisplayMedicineList.class);
        startActivity(intent);
    }




}