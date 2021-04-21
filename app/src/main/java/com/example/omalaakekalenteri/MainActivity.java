package com.example.omalaakekalenteri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Medicine> medicines;
    private Button calendarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicines = new ArrayList<>();
        medicines.add(new Medicine("Burana", 400, "ibuprofeiini", 3, 21));
        medicines.add(new Medicine("Panadol", 1000, "parasetamoli", 2, 16));

        ListView listViewMedicines = findViewById(R.id.listViewMedicines);
        listViewMedicines.setAdapter(new ArrayAdapter<Medicine>(
                this, R.layout.medicine_item_layout, medicines
        ));

        listViewMedicines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

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

    public void openActivityCalendar30(){
        Intent intent = new Intent(this, calendar30.class);
        startActivity(intent);
    }

    public void addMedicine(View view) {
        Intent intent = new Intent(this, AddMedicine.class);
        startActivity(intent);
    }
}