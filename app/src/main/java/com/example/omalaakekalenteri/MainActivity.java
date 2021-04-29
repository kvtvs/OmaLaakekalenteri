package com.example.omalaakekalenteri;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

/**
 * @author Kata Sara-aho, Mikko Räikkönen, Mikael Alakari
 */
public class MainActivity extends AppCompatActivity /*implements Serializable*/ implements TimePickerDialog.OnTimeSetListener {
    private ArrayList<Medicine> medicines;
    ArrayAdapter adapter;
    private Button calendarButton, medicineListButton, clockButton;
    private NotificationManagerCompat notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /** Button for medicine list activity **/
        medicineListButton = (Button) findViewById(R.id.buttonLaakelista);
        medicineListButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMedicineList();
            }
        });

        /** Button for calendar activity **/
        calendarButton = (Button) findViewById(R.id.calanderButton);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityCalendar30();
            }
        });

        clockButton = (Button) findViewById(R.id.clockButton);
        clockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        notificationManager = NotificationManagerCompat.from(this);
    }


    /** Receives result info from AddMedicine.class that a new medicine is added **/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 1 && resultCode == RESULT_OK){
            /** Creates new medicine and adds it to ArratList Adapter **/
            Medicine med = new Medicine(intent.getStringExtra("laakeNimi"), intent.getStringExtra("vaikuttavaAine"), intent.getIntExtra("kertaaPaivassa", 0), intent.getIntExtra("maara", 0), intent.getIntExtra("annostus", 0),intent.getIntExtra("kappaleMaara", 0));
            adapter.add(med);
            adapter.notifyDataSetChanged();

        }
    }

    /** Receives result info from AddMedicine.class that a new medicine is added **/
    public void updateListView(){
        /** Fetches the info of the new medicine from Intent **/
        Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();
        if(bundle != null){
            /** Creates new medicine and adds it to ArratList Adapter **/
            Medicine med = new Medicine(intent.getStringExtra("laakeNimi"), intent.getStringExtra("vaikuttavaAine"), intent.getIntExtra("kertaaPaivassa", 0), intent.getIntExtra("maara", 0), intent.getIntExtra("annostus", 0),intent.getIntExtra("kappaleMaara", 0));
            adapter.add(med);
            adapter.notifyDataSetChanged();
        }
    }

    /** Method for opening the calendar activity **/
    public void openActivityCalendar30(){
        Intent intent = new Intent(this, calendar30.class);
        startActivity(intent);

    }

    /** Method for opening the medicine list activity **/
    public void openMedicineList() {
        Intent intent = new Intent(this, DisplayMedicineList.class);
        startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        TextView clockTextView = (TextView) findViewById(R.id.clockTextView);
        clockTextView.setText("Muistutus on asetettu " + hourOfDay + ":" + minute);
    }
}