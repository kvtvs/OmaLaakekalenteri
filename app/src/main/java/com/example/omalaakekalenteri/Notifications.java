package com.example.omalaakekalenteri;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notifications extends Application{
    public static final String CHANNEL_1_ID = "laakkeet";
    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel laakkeet = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Lääkkeet",
                    NotificationManager.IMPORTANCE_HIGH
            );
            laakkeet.setDescription("Oletteko ottaneet lääkkeenne?");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(laakkeet);

        }
    }
}
