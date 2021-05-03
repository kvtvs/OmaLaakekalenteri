 package com.example.omalaakekalenteri;

 import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

import static android.app.AlarmManager.INTERVAL_DAY;

 /**
 * MainActivity
 * @author Kata Sara-aho, Mikko Räikkönen, Mikael Alakari
 */
public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private ArrayList<Medicine> medicines;
    ArrayAdapter adapter;
    private Button calendarButton, medicineListButton, clockButton, cancelButton;
    private boolean setText = false;
    private boolean cancelText = false;
    private TextView clockTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clockTextView = findViewById(R.id.clockTextView);
        Calendar c = Calendar.getInstance();

        /** Button for medicine list activity **/
        medicineListButton = (Button) findViewById(R.id.buttonLaakelista);
        medicineListButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMedicineList();
            }
        });

        /** Button for calendar activity */
        calendarButton = (Button) findViewById(R.id.calanderButton);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityCalendar30();
            }
        });

        /** Button for opening the timepicker */
        clockButton = (Button) findViewById(R.id.clockButton);
        clockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelText = false;
                setText = true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        /** Button for canceling the notification */
        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText = false;
                cancelText = true;
                cancelAlarm(c);
            }
        });
    }


    /**
     *  Receives result info from AddMedicine.class that a new medicine is added
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d("MED_", "onActivityResult() called");
        if(requestCode == 1 && resultCode == RESULT_OK){
            /** Creates new medicine and adds it to ArratList Adapter **/
            Medicine med = new Medicine(intent.getStringExtra("laakeNimi"), intent.getStringExtra("vaikuttavaAine"), intent.getIntExtra("kertaaPaivassa", 0), intent.getIntExtra("maara", 0), intent.getIntExtra("annostus", 0),intent.getIntExtra("kappaleMaara", 0));
            adapter.add(med);
            adapter.notifyDataSetChanged();

        }
    }

    /**
     *  Receives result info from AddMedicine.class that a new medicine is added
     */
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

    /**
     Method for opening the calendar activity
     */
    public void openActivityCalendar30(){
        Intent intent = new Intent(this, calendar30.class);
        startActivity(intent);

    }

    /**
     * Method for opening the medicine list activity
     */
    public void openMedicineList() {
        Intent intent = new Intent(this, DisplayMedicineList.class);
        startActivity(intent);
    }

    /**
     * Method for getting values from timepicker
     */

    @SuppressLint("SetTextI18n")
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateText(c);

        startAlarm(c);
    }

    /**
     * This method will create the notification
     */
    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if(c.before(Calendar.getInstance())){
                c.add(Calendar.DATE, 1);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), INTERVAL_DAY, alarmIntent);
        }
    }

    /**
     * this method will cancel the alarm and change the text to notification is canceled
     */
    @SuppressLint("SetTextI18n")
    private void cancelAlarm(Calendar c){
        AlarmManager cancelManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent cancelIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        updateText(c);

        cancelManager.cancel(cancelIntent);
    }

     /**
      * this method will update the text on main activity depending on user input
      */
    @SuppressLint("SetTextI18n")
    private void updateText(Calendar c){
        String clockText;
        if (setText){
            clockText = "Muistutus on asetettu joka päivälle kello: ";
            clockText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
            clockTextView.setText(clockText);
        }
        else if(cancelText){
            clockTextView.setText("Muistutus on hylätty");
        }
    }
}