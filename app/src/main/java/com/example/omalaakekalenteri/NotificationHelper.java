package com.example.omalaakekalenteri;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {

    public static final String channelID = "channelID";
    public static final String channelName = "Notification";
    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification() {

        Intent activityIntent = new Intent (this, MainActivity.class);
        PendingIntent moveIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("OmaLääkekalenteri")
                .setContentText("Muista ottaa lääkkeet!")
                .setAutoCancel(true)

                .addAction(R.mipmap.ic_launcher, "En ottanut lääkkeitäni", moveIntent)
                .setSmallIcon(R.drawable.ic_omalaakekalenteri);
    }
}