package com.example.omalaakekalenteri;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{
    private ArrayList<Medicine> medicines;
    ArrayAdapter adapter;
    private Button calendarButton, medicineListButton, takenButton, notTakenButton, clockButton;
    private TextView clockTextView, otsikko;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        clockTextView = (TextView) findViewById(R.id.clockTextView) ;
        otsikko = (TextView) findViewById(R.id.textViewHeader);

        medicineListButton = (Button) findViewById(R.id.buttonLaakelista);
        medicineListButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMedicineList();
            }
        });


        calendarButton = (Button) findViewById(R.id.calanderButton);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityCalendar30();
            }
        });


        takenButton = (Button) findViewById(R.id.takenButton);
        takenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
                //poistaa hälyytyksen ja miinustaa lääke määrästä
            }
        });

        notTakenButton = (Button) findViewById(R.id.notTakenButton);
        notTakenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ei miinusta lääkemäärästä
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
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 1 && resultCode == RESULT_OK){
            Medicine med = new Medicine(intent.getStringExtra("laakeNimi"), intent.getStringExtra("vaikuttavaAine"), intent.getIntExtra("kertaaPaivassa", 0), intent.getIntExtra("maara", 0), intent.getIntExtra("annostus", 0));
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

    public void openMedicineList() {
        Intent intent = new Intent(this, DisplayMedicineList.class);
        startActivity(intent);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateClockText(c);
        startAlarm(c);
    }

    private void updateClockText(Calendar c){
        String clockText = "Muistutus asetettu: ";
        clockText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        clockTextView.setText(clockText);
    }

    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if(c.before(Calendar.getInstance())){
            c.add(Calendar.DATE,1); //tässä jos hälytys laitetaan mennneeseen kellonaikaan se siirtääsen seuraavalle päivälle
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
    }
}