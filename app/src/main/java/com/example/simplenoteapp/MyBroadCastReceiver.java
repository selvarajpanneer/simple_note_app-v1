package com.example.simplenoteapp;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

public class MyBroadCastReceiver extends BroadcastReceiver {

    private SqlHelper dpHelper;
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1=intent;
        int serial_no=intent1.getIntExtra("notification_slno",0);
        String title=intent1.getStringExtra("notification_title");
        String content=intent1.getStringExtra("notification_content");
        NotificationService notificationHelper = new NotificationService(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(title,content);
        Intent intent2 = new Intent(context,InputSection.class);
        intent2.putExtra("edit_slno",serial_no);
        intent2.putExtra("edit_title",title);
        intent2.putExtra("edit_content",content);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        nb.setContentIntent(contentIntent);

        notificationHelper.getManager().notify(1, nb.build());
        //sql instantiation to handle db
        getdbHelper(context).deactivateAlarm(serial_no);

    }
    public SqlHelper getdbHelper(Context context){
        if(dpHelper==null){
            dpHelper= new SqlHelper(context);
        }
        return dpHelper;
    }
}
