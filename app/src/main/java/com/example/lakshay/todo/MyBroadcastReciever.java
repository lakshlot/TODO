package com.example.lakshay.todo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.widget.Toast;

import static android.content.Context.ALARM_SERVICE;

public class MyBroadcastReciever extends BroadcastReceiver {
    MediaPlayer mp;
    String title;
    String description;
    String date;
    String time;


    @Override
    public void onReceive(Context context, Intent intent) {
      // title= intent.getStringExtra("alarmname");
        mp=MediaPlayer.create(context,R.raw.shapeofyou );
        mp.start();
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();

        title= intent.getStringExtra("titleview");
        description= intent.getStringExtra("descview");
        date= intent.getStringExtra("dateview");
        time= intent.getStringExtra("timeview");
        newActivity(context);

//






    }
    public  void  newActivity(Context context){
      Intent  intent=new Intent();
        intent.setClass(context,ListitemDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("tview",title);
        intent.putExtra("deview",description);
        intent.putExtra("daview",date);
        intent.putExtra("tiview",time);



        context.startActivity(intent);

    }
}
