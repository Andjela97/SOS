package com.example.sos;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

public class ReminderBroadcast extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context, PregledPodsetnika.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        String kobasica = "Popijte lekove.";
        try {
            Bundle b = intent.getExtras();
            kobasica = (String) b.get("imena");
            System.out.println(kobasica);
            System.out.println("USAO U TRYYYYYYYYYYYYYYYYYYYYYYYYYY");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("EXCEPTION u reminder bradcast klasi");
        }
        System.out.println(kobasica);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "kanalID").
                setContentIntent(pendingIntent).
                setContentText(kobasica).
                setContentTitle("MedAssistant").
                setSmallIcon(R.drawable.pill).
                setAutoCancel(true);
        notificationManager.notify(100, builder.build());
    }
}
