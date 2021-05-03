package com.example.omalaakekalenteri;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

/**
 * Class for making and customizing notifications
 * @author Mikko Räikkönen
 *
 */

public class NotificationHelper extends ContextWrapper {

    public static final String channelID = "OmaLääkekalenteri";
    public static final String channelName = "OmaLääkekalenteri";
    private NotificationManager mManager;

    /**
     * Constructor is making sure that users phone uses Android Oreo or a newer Android OS
     */
    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    /**
     * notification channel is made here and the channel is able for example to activate phones vibration and lights
     */
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        channel.enableLights(true);
        channel.setLightColor(R.color.design_default_color_primary);
        channel.enableVibration(true);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        getManager().createNotificationChannel(channel);
    }

    /**
     *This will build the notification channels
     */
    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    /**
     * Here notification gets its properties and what does the notification do
     */
    public NotificationCompat.Builder getChannelNotification() {

        Intent activityIntent = new Intent (this, DisplayMedicineList.class);
        PendingIntent moveIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("OmaLääkekalenteri")
                .setContentText("Muistakaa ottaa lääkkeet!")
                .setAutoCancel(true)

                .addAction(R.mipmap.ic_launcher, "En ottanut lääkkeitäni", moveIntent)
                .setSmallIcon(R.drawable.ic_omalaakekalenteri);
    }
}