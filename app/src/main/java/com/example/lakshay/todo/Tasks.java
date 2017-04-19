package com.example.lakshay.todo;

/**
 * Created by Lakshay on 2/15/2017.
 */

public class Tasks {
     String title;
     String description;
     String date;
     String time;
    int id;

    public  Tasks(int id,String title,String description,String date,String time){
     this.title=title;
        this.description=description;
        this.time=time;
        this.date=date;
        this.id= id;

    }


}
