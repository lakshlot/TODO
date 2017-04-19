package com.example.lakshay.todo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ListitemDetailActivity extends AppCompatActivity {
    TextView titletex;
    TextView descriptiontext;
    TextView datetext;
    TextView timetext;
    String title;
    String date;
    String time;
    String description;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listitem_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent= getIntent();
                PendingIntent almoperation= PendingIntent.getBroadcast(ListitemDetailActivity.this,123,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                almoperation.cancel();
                alarmManager.cancel(almoperation);
                mp.stop();





            }
        });
        titletex= (TextView)findViewById(R.id.titlview);
        descriptiontext=(TextView)findViewById(R.id.descview);
        datetext= (TextView)findViewById(R.id.datview);
        timetext=(TextView)findViewById(R.id.timview);
        Intent i= getIntent();
        titletex.setText(i.getStringExtra("tview"));
        descriptiontext.setText(i.getStringExtra("deview"));
        datetext.setText(i.getStringExtra("daview"));
        timetext.setText(i.getStringExtra("tiview"));







    }

}
