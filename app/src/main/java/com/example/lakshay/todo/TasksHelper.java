package com.example.lakshay.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lakshay on 2/26/2017.
 */

public class TasksHelper extends SQLiteOpenHelper {
    final static String TASKS_TABLE_NAME = "Tasks";
    final static String TASKS_TABLE_COLUMN_TITLE = "title";
    final static String TASKS_TABLE_COLUMN_DESCRIPTION = "description";
    final static String TASKS_TABLE_COLUMN_DATE= "date";
    final static String TASKS_TABLE_COLUMN_TIME = "time";
    final static String TASKS_TABLE_COLUMN_ID = "_id";

    public TasksHelper(Context context) {
        super(context, "Tododb", null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_Query = "CREATE TABLE " + TASKS_TABLE_NAME +
                "( "+TASKS_TABLE_COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TASKS_TABLE_COLUMN_TITLE + " TEXT, " +
                TASKS_TABLE_COLUMN_DESCRIPTION + " TEXT, " +
                TASKS_TABLE_COLUMN_DATE + " TEXT, " +
                TASKS_TABLE_COLUMN_TIME + " TEXT); ";
        db.execSQL(sql_Query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
