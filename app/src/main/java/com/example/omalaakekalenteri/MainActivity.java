package com.example.omalaakekalenteri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Medicine> medicines;
    ArrayAdapter adapter;
    private Button calendarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicines = new ArrayList<>();
        medicines.add(new Medicine("Burana", "ibuprofeiini", 400, 3, 21));
        medicines.add(new Medicine("Panadol", "parasetamoli", 1000, 2, 16));
        updateListView();

        Button addBtn = findViewById(R.id.buttonNewMedicine);
        addBtn.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick (View view){
                startActivityForResult(new Intent(MainActivity.this, AddMedicine.class), 1);
            }
        }));

        ListView listViewMedicines = findViewById(R.id.listViewMedicines);
        listViewMedicines.setAdapter(new ArrayAdapter<Medicine>(
                this, android.R.layout.simple_list_item_1, medicines
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 1 && resultCode == RESULT_OK){
            Medicine med = new Medicine(intent.getStringExtra("laakeNimi"), intent.getStringExtra("vaikuttavaAine"), intent.getIntExtra("kertaaPaivassa", 0), intent.getIntExtra("maara", 0), intent.getIntExtra("annostus", 0));
            medicines.add(med);
            adapter.add(med);
            adapter.notifyDataSetChanged();

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


}