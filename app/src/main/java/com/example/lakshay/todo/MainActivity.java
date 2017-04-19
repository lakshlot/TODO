package com.example.lakshay.todo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Instrumentation;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    ArrayList<Tasks> taskses;
    TasksAdapter adapter;
    TasksHelper tasksHelper;

    final static int NEW_TASK=1;
    final static int EDIT_TASK=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tasksHelper= new TasksHelper(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.plus_clean);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Addactivity.class);
                startActivityForResult(intent,NEW_TASK);


            }
        });

        taskses = new ArrayList<>();



        adapter= new TasksAdapter(MainActivity.this,taskses);
         ListView listView=(ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
        setupView();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Tasks clicked = taskses.get(position);



                Intent i= new Intent();
                i.setClass(MainActivity.this,Addactivity.class);
                i.putExtra("title2",clicked.title);
                i.putExtra("desc2",clicked.description);
                i.putExtra("date2",clicked.date);
                i.putExtra("time2",clicked.time);
                 startActivityForResult(i,EDIT_TASK);


            }
       });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                PopupMenu popupMenu= new PopupMenu(MainActivity.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.popmenu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {



                        return true;

                    }
                });
                popupMenu.show();

                return true;
            }
        });






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==NEW_TASK){
          if (resultCode== Activity.RESULT_OK){


              setupView();

          }





//
//            Tasks temp= new Tasks(id,title,description,date,time);
//            taskses.add(temp);
//
//            adapter.notifyDataSetChanged();




        }


    }
    public void setupView(){
        SQLiteDatabase db= tasksHelper.getReadableDatabase();
        taskses.clear();
        Cursor c= db.query(TasksHelper.TASKS_TABLE_NAME,null,null, null,null,null,null);
        while (c.moveToNext()){
            int gid= c.getInt(c.getColumnIndex(TasksHelper.TASKS_TABLE_COLUMN_ID));
            String gtitle= c.getString(c.getColumnIndex(TasksHelper.TASKS_TABLE_COLUMN_TITLE));
            String gdescription= c.getString(c.getColumnIndex(TasksHelper.TASKS_TABLE_COLUMN_DESCRIPTION));
            String gdate= c.getString(c.getColumnIndex(TasksHelper.TASKS_TABLE_COLUMN_DATE));
            String gtime= c.getString(c.getColumnIndex(TasksHelper.TASKS_TABLE_COLUMN_TIME));

            Tasks tasks= new Tasks(gid,gtitle,gdescription,gdate,gtime);
            taskses.add(tasks);

        }
        adapter.notifyDataSetChanged();

    }












    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}


