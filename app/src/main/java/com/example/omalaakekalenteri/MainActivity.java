package com.example.omalaakekalenteri;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewTest;
    ArrayAdapter adapter;
    private Button calendarButton;
    private final String TAG = "MED_";
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewTest = findViewById(R.id.textViewTest);

        updateListView();

        ListView listViewMedicines = findViewById(R.id.listViewMedicines);
        listViewMedicines.setAdapter(new ArrayAdapter<Medicine>(
                this, android.R.layout.simple_list_item_1, MedicineList.getInstance().getMedicines()
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

                Intent addMedicineActivity = new Intent(MainActivity.this, DisplayMedicine.class);
                addMedicineActivity.putExtras(medicineInfo);
                startActivity(addMedicineActivity);
            }
        });

        Button addBtn = findViewById(R.id.buttonNewMedicine);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                startActivity(new Intent(MainActivity.this, AddMedicine.class));
            }
        });

        calendarButton = (Button) findViewById(R.id.calanderButton);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityCalendar30();
            }
        });
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d(TAG, "onAcitivityResult() called");
        if(requestCode == 1 && resultCode == RESULT_OK){
            String name = intent.getStringExtra("laakeNimi");
            Log.d(TAG, name);
            Medicine med = new Medicine(intent.getStringExtra("laakeNimi"), intent.getStringExtra("vaikuttavaAine"), intent.getIntExtra("kertaaPaivassa", 0), intent.getIntExtra("maara", 0), intent.getIntExtra("annostus", 0));

            adapter.add(med);
            adapter.notifyDataSetChanged();

        }
    }
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult() called");

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String test = data.getStringExtra("laakeNimi");
                mTextViewTest.setText(test);
            }
            if (resultCode == RESULT_CANCELED) {
                mTextViewTest.setText("Nothing selected");
            }
        }
    }

    public void updateListView(){
        Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();
        if(bundle != null){
            Medicine med = new Medicine(intent.getStringExtra("laakeNimi"), intent.getStringExtra("vaikuttavaAine"), intent.getIntExtra("kertaaPaivassa", 0), intent.getIntExtra("maara", 0), intent.getIntExtra("annostus", 0));
            adapter.add(med);
            adapter.notifyDataSetChanged();
        }
    }

    public void openActivityCalendar30(){
        Intent intent = new Intent(this, calendar30.class);
        startActivity(intent);
    }

    public void addMedicine(View view) {
        Intent intent = new Intent(this, AddMedicine.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onCreate() called");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called");
    }
}