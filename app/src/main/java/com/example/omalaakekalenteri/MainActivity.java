package com.example.omalaakekalenteri;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {
    private ArrayList<Medicine> medicines;
    ArrayAdapter adapter;
    private Button calendarButton, medicineListButton, notificationTestButton, takenButton, notTakenButton;
    private NotificationManagerCompat notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //otsikko = (TextView) findViewById(R.id.textViewHeader);

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

            }
        });

        notTakenButton = (Button) findViewById(R.id.notTakenButton);
        notTakenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        notificationManager = NotificationManagerCompat.from(this);

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
        /*if (!MedicineList.getInstance().getMedicines().isEmpty()){
            intent.putExtra("list", (Serializable) MedicineList.getInstance().getMedicines());
        }
         */

        startActivity(intent);

    }

    public void openMedicineList() {
        Intent intent = new Intent(this, DisplayMedicineList.class);
        startActivity(intent);
    }

    public void notifications(View v) {

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity (this, 0, activityIntent, 0);

        String message = "Oletteko ottaneet lääkkeenne?";

        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("laakeMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, Notifications.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_omalaakekalenteri)
                .setContentTitle("OmaLääkekalenteri")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .addAction(R.mipmap.ic_launcher, "Otettu", actionIntent)
                .addAction(R.mipmap.ic_launcher, "Ei ole otettu", actionIntent)
                .build();

        notificationManager.notify(1, notification);
    }
}