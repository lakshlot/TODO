package com.example.lakshay.todo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lakshay on 2/15/2017.
 */

public class TasksAdapter extends ArrayAdapter<Tasks> {
    Context mContext;
    ArrayList<Tasks> mTasks;

    public TasksAdapter(Context context,  ArrayList<Tasks> objects) {
        super(context, 0, objects);
        mContext=context;
        mTasks=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= View.inflate(mContext,R.layout.list_item,null);
        }
        TextView titelTextView= (TextView)convertView.findViewById(R.id.title2);
        TextView descriptiontextView= (TextView)convertView.findViewById(R.id.description2);
        TextView dateTextView= (TextView)convertView.findViewById(R.id.dateview) ;
        TextView timeTextView=(TextView)convertView.findViewById(R.id.timeview);


        Tasks tasks= mTasks.get(position);

        dateTextView.setText(tasks.date);
        timeTextView.setText(tasks.time);
        titelTextView.setText(tasks.title);
        descriptiontextView.setText(tasks.description);
        return convertView;
    }


}
