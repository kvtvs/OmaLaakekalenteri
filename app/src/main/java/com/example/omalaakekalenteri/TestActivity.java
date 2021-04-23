package com.example.omalaakekalenteri;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {
    private final String TAG = "MED_";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.d("MED_", "Test");


        Intent intent = getIntent();
        String name = intent.getStringExtra("laakeNimi");
        String activeIngredient = intent.getStringExtra("vaikuttavaAine");
        int timesADay = intent.getIntExtra("kertaaPäivässä", 0);
        int quantity = intent.getIntExtra("maara", 0);
        int dosage = intent.getIntExtra("annostus", 0);

        Medicine medicine = new Medicine(name, activeIngredient, timesADay, quantity, dosage);
        MedicineList.getInstance().addMedicine(medicine);
        Log.d("MED_", "Test2");


        ListView listViewMedicines = findViewById(R.id.listViewMedicineList);
        listViewMedicines.setAdapter(new ArrayAdapter<Medicine>(
                this, R.layout.medicine_item_layout, MedicineList.getInstance().getMedicines()
        ));

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

                Bundle medicineInfo = new Bundle();
                medicineInfo.putString("name", name);
                medicineInfo.putString("dosage", dosage);
                medicineInfo.putString("activeIngredient", activeIngredient);
                medicineInfo.putString("timesADay", timesADay);
                medicineInfo.putString("quantity", quantity);

                Intent addMedicineActivity = new Intent(TestActivity.this, DisplayMedicine.class);
                addMedicineActivity.putExtras(medicineInfo);
                startActivity(addMedicineActivity);
            }
        });
    }

    public void addMedicine(View view) {
        Intent intent = new Intent(this, AddMedicine.class);
        startActivity(intent);
    }
}
