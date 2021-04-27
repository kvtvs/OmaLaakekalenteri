package com.example.omalaakekalenteri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DisplayMedicineList extends AppCompatActivity {
    private final String TAG = "MED_";
    private Button backBtn;
    public final static String SHARED_PREFS = "sharedPrefs";
    public final static String LIST = "list";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_medicine_list);

        saveData();
        loadData();


        backBtn = (Button) findViewById(R.id.buttonBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        ListView listViewMedicines = findViewById(R.id.listViewMedicineList);
        listViewMedicines.setAdapter(new ArrayAdapter<Medicine>(
                this, R.layout.medicine_item_layout, MedicineList.getInstance().getMedicines()
        ));

        Intent intent = getIntent();

        String name = intent.getStringExtra("laakeNimi");
        String activeIngredient = intent.getStringExtra("vaikuttavaAine");
        int timesADay = intent.getIntExtra("kertaaPaivassa", 0);
        int quantity = intent.getIntExtra("maara", 0);
        int dosage = intent.getIntExtra("annostus", 0);
        if (name != null){
            Medicine medicine = new Medicine(name, activeIngredient, timesADay, quantity, dosage);
            MedicineList.getInstance().addMedicine(medicine);
        }




        listViewMedicines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                Log.d(TAG, "onItemClick(" + i + ")");
                Medicine medicine = MedicineList.getInstance().getMedicine(i);
                Log.d(TAG, medicine.toString());

                String name = medicine.getName();
                String dosage = Integer.toString(medicine.getDosageMg());
                String activeIngredient = medicine.getActiveIngredient();
                String timesADay = Integer.toString(medicine.getTimesADay());
                String quantity = Integer.toString(medicine.getQuantity());
                int medicineNumber = i;
                Log.d(TAG, "" + i);

                Bundle medicineInfo = new Bundle();
                medicineInfo.putString("name", name);
                medicineInfo.putString("dosage", dosage);
                medicineInfo.putString("activeIngredient", activeIngredient);
                medicineInfo.putString("timesADay", timesADay);
                medicineInfo.putString("quantity", quantity);
                medicineInfo.putInt("medicineNumber", medicineNumber);

                Intent addMedicineActivity = new Intent(DisplayMedicineList.this, DisplayMedicine.class);
                addMedicineActivity.putExtras(medicineInfo);
                startActivity(addMedicineActivity);
            }
        });
    }

    public void addMedicine(View view) {
        Intent intent = new Intent(this, AddMedicine.class);
        startActivity(intent);
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MedicineList.getInstance().getMedicines());
        editor.putString(LIST, json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(LIST, null);
        Type type = new TypeToken<ArrayList<Medicine>>() {}.getType();
        MedicineList.getInstance().setMedicines(gson.fromJson(json, type));
        if (MedicineList.getInstance().getMedicines() == null) {
            Toast.makeText(this, "Lääkelista on tyhjä", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "empty");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveData();
    }
}
