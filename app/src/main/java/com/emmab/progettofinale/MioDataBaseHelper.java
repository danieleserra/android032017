package com.emmab.progettofinale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.Calendar;


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
        db.close();


    }


    public void elimina() {
       /* SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from note;";
        db.execSQL(sql);
        db.close();
*/

    }

    public void updateNote(long id, String title, String body) {
        SQLiteDatabase db = this.getWritableDatabase();
        title=cleanText(title);
        body=cleanText(body);
        String sql = "UPDATE note SET title='"+title+"', body='"+body+"', date=1000*(SELECT strftime('%s','now')) " +
                "WHERE _id="+id+";";


        db.execSQL(sql);
        db.close();

    }

    public void deleteNote(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "DELETE FROM note WHERE _id="+id+";";


        db.execSQL(sql);
        db.close();

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
        Cursor cursore = db.query("note",null,null,null,null,null,"date DESC");

        while(cursore.moveToNext()){
            Calendar c= Calendar.getInstance();
            c.setTimeInMillis(cursore.getLong(1));
            Note note=new Note(cursore.getLong(0),c,fixText(cursore.getString(2)),fixText(cursore.getString(3)));

            ar_note.add(note);

        }

        return ar_note;
    }


    private String cleanText(String arg){

        return arg.replace("'", "#apice");
    }

    private String fixText(String arg){


        return arg.replace("#apice", "'");
    }
}
