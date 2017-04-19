package com.example.lakshay.todo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Instrumentation;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TimeZone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class Addactivity extends AppCompatActivity {
    EditText edittitle;
    EditText editdescription;
    EditText datetext;
    EditText timetext;
    Button addtaskButton;
    String title;
    String description;
    String date;
    String time;
    long epoch;


    Calendar mcalendar= Calendar.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addactivity);

        edittitle = (EditText) findViewById(R.id.title);
        editdescription = (EditText) findViewById(R.id.description);


        datetext = (EditText) findViewById(R.id.datetext);
        timetext = (EditText) findViewById(R.id.timetext);


        addtaskButton = (Button) findViewById(R.id.addtaskbutton);
//

        datetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog();
            }
        });

        timetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimDialog();
            }
        });


        addtaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = edittitle.getText().toString();
                description = editdescription.getText().toString();
                date = datetext.getText().toString();
                time = timetext.getText().toString();
                epoch=mcalendar.getTimeInMillis();

                if (title.isEmpty()) {
                    edittitle.setError("Title can't be empty ");
                    return;
                }
                if (description.isEmpty()) {
                    editdescription.setError("Category can't be empty ");
                    return;
                }

                if (date.isEmpty()) {
                    datetext.setError("Price can't be empty ");
                    return;
                }
                if (time.isEmpty()) {
                    timetext.setError("Quantity can't be empty ");
                    return;
                }

                TasksHelper tasksHelper = new TasksHelper(Addactivity.this);
                SQLiteDatabase db = tasksHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(TasksHelper.TASKS_TABLE_COLUMN_TITLE, title);
                cv.put(TasksHelper.TASKS_TABLE_COLUMN_DESCRIPTION, description);
                cv.put(TasksHelper.TASKS_TABLE_COLUMN_DATE, date);
                cv.put(TasksHelper.TASKS_TABLE_COLUMN_TIME, time);
                db.insert(TasksHelper.TASKS_TABLE_NAME, null, cv);


                Intent i = getIntent();
                i.putExtra("title", title);
                i.putExtra("desc", description);
                i.putExtra("date", date);
                i.putExtra("time", time);
                //Log.i("timeadd",time);
                setalarms(date, time, title, description);
                setResult(Activity.RESULT_OK, i);


                finish();


            }
        });


    }

    public void DateDialog() {

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mcalendar.set(Calendar.YEAR,year);
                mcalendar.set(Calendar.MONTH,monthOfYear);
                mcalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);


                datetext.setText(dayOfMonth + "/" + monthOfYear + "/" + year);


            }
        };

        DatePickerDialog dpDialog = new DatePickerDialog(this, listener,mcalendar.get(Calendar.YEAR),mcalendar.get(Calendar.MONTH),mcalendar.get(Calendar.DAY_OF_MONTH));
        dpDialog.show();

    }

    TimePickerDialog.OnTimeSetListener listener;
    String amorpm;

    public void TimDialog() {
        listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                if (hourOfDay > 12) {
//                    amorpm = "pm";
//                    hourOfDay = hourOfDay - 12;
//                } else {
//                    amorpm = "am";
//                }
                mcalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                mcalendar.set(Calendar.MINUTE,minute);
                Log.i("minute",""+minute);

                timetext.setText(hourOfDay + ":" + minute + " " + amorpm);

            }
        };
        TimePickerDialog tmDialog = new TimePickerDialog(this, listener, mcalendar.get(Calendar.HOUR_OF_DAY), mcalendar.get(Calendar.MINUTE), true);

        tmDialog.show();


    }


    public void setalarms(String date2, String time2, String title2, String description2) {

        Intent intent = new Intent(this, MyBroadcastReciever.class);
        intent.putExtra("titleview", title2);
        intent.putExtra("descview", description2);
        intent.putExtra("dateview", date2);
        intent.putExtra("timeview", time2);

        PendingIntent alarmoperation = PendingIntent.getBroadcast(
                this.getApplicationContext(), 123, intent, 0);
//
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Log.i("hello",""+ epoch);
        alarmManager.set(AlarmManager.RTC, epoch
                , alarmoperation);// i am here setting the alarm to trigger
        Toast.makeText(this, " Alarm set on " + date2 + " at " + time2, Toast.LENGTH_LONG).show();



    }
}


//

