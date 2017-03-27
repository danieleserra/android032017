package com.emmab.progettofinale;

import java.util.Calendar;

/**
 * Created by Epeople on 27/03/2017.
 */

public class PreviewMemo {

        private String title;
        private Calendar date;
        private String corpo;



    public PreviewMemo(Calendar d, String t, String c){
        title=t;
        date=d;
        corpo=c;
    }

    public Calendar getDate() {
        return date;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getCorpo() {
        return (corpo.substring(0,25)+"...");
    }

    public void setTitle(String title) {
        this.title=title;

    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getTitle() {
        return (title.substring(0,10)+"...");
    }
}
