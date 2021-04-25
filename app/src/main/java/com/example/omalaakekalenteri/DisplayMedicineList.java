package com.example.omalaakekalenteri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DisplayMedicineList extends AppCompatActivity {
    private final String TAG = "MED_";
    private Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_medicine_list);
        Log.d("MED_", "Test");

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

        Log.d("MED_", "Test2");


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


}
