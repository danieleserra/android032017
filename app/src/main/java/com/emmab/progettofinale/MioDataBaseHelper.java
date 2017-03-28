package com.emmab.progettofinale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MioDataBaseHelper extends SQLiteOpenHelper {

    public MioDataBaseHelper(Context context){

        super(context, "note_db", null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE note(" +
                "_id INTEGER PRIMARY KEY, " +
                "date INTEGER NOT NULL, " +
                "title TEXT NOT NULL, " +
                "body TEXT" +
                ");";
        db.execSQL(sql);


    }


    public void elimina() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from note;";
        db.execSQL(sql);


    }

    public void updateNote() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from note;";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void popola(Calendar date, String title, String body){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date",date.getTime().getTime());
        values.put("title",title);
        values.put("body",body);
        long id = db.insert("note",null,values);

    }

    public ArrayList<Note> getAllNotes(){
        ArrayList<Note> ar_note = new ArrayList<Note>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursore = db.query("note",null,null,null,null,null,null);

        while(cursore.moveToNext()){
            Calendar c= Calendar.getInstance();
            c.setTimeInMillis(cursore.getLong(1));
            Note note=new Note(cursore.getLong(0),c,cursore.getString(2),cursore.getString(3));

            ar_note.add(note);

        }

        return ar_note;
    }

}
