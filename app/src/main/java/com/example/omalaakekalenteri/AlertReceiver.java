package com.example.omalaakekalenteri;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

/**
 * @author Mikko Räikkönen
 * Class will receive the notification
 */

public class AlertReceiver extends BroadcastReceiver {
    @Override

    /**
     * this method will be called when the notification starts
     */
    public void onReceive(Context context, Intent intent) {

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());
    }
}
