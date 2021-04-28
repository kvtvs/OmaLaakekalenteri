package com.example.omalaakekalenteri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    private String pieces;
    private Button buttoniHaveEatenMedicine;
    private Counter laskuri;

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
        pieces = bundle.getString("pieces");

        laskuri = new Counter(Integer.parseInt(quantity), Integer.parseInt(pieces), 0);

        warningText = (TextView) findViewById(R.id.textViewWarning);
        warningText.setVisibility(View.INVISIBLE);

        TextView textViewName = findViewById(R.id.textViewMedicineName);
        TextView textViewDosage = findViewById(R.id.textViewMedicineDosage);
        TextView textViewActiveIngredient = findViewById(R.id.textViewMedicineActiveIngredient);
        TextView textViewTimesADay = findViewById(R.id.textViewMedicineTimesADay);
        TextView textViewPieces = findViewById(R.id.textViewPiecesAtOnce);
        Button buttonRemoveMedicine = findViewById(R.id.buttonRemoveMedicine);
        Button buttonReturnToList = findViewById(R.id.buttonReturnToList);
        buttoniHaveEatenMedicine = findViewById(R.id.buttoniHaveEatenMedicine);
        buttoniHaveEatenMedicine.setEnabled(true);
        textViewQuantity = findViewById(R.id.textViewMedicineQuantity);

        textViewName.setText(name);
        textViewDosage.setText(dosage + " mg");
        textViewActiveIngredient.setText(activeIngredient);
        textViewTimesADay.setText("Monta kertaa päivässä: " + timesADay);
        textViewQuantity.setText("Pillereitä jäljellä: " + quantity);
        textViewPieces.setText("Lääkkeiden määrä kerta-annoksessa: " + pieces);


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

    public void buttonPressed(View v) {
        laskuri.laskeUusi();

        MedicineList.getInstance().getMedicines().get(medicineNumber).setQuantity(MedicineList.getInstance().getMedicines().get(medicineNumber).getQuantity() - 1);
        int b = MedicineList.getInstance().getMedicines().get(medicineNumber).getQuantity();
        Log.d(TAG, ""+b);
        textViewQuantity.setText("Pillereitä jäljellä: " + laskuri.getUusi());
        if ( laskuri.getUusi() == 0){
            warningText.setVisibility(View.VISIBLE);
            buttoniHaveEatenMedicine.setEnabled(false);
        } else {
            warningText.setVisibility(View.INVISIBLE);
            buttoniHaveEatenMedicine.setEnabled(true);
        }
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