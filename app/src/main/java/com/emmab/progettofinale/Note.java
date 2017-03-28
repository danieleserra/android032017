package com.emmab.progettofinale;

import android.util.Log;

import java.util.Calendar;

/**
 * Created by Epeople on 27/03/2017.
 */

public class Note {

        private String title;
        private Calendar date;
        private String corpo;
        private long id;
        final int maxbody=25;



    public Note(long id, Calendar d, String t, String c){
        title=t;
        date=d;
        corpo=c;
        this.id=id;

        Log.e(">>>costruttore",">>>>>>>>>>>>>>costrutt>>>>>>>>>>>>>"+d.getTime());
    }

    public Calendar getDate() {

        Log.e(">>>costruttore",">>>>>>>>>>>>>>>date>>>>>>>>>>>>"+date.getTime());
        return date;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getCorpo() {

            return corpo;
    }

    public void setTitle(String title) {
        this.title=title;

    }

    public long getId() {
        return id;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }
}
