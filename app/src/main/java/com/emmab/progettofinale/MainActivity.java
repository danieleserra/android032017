package com.emmab.progettofinale;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
       /* MioDataBaseHelper mydb = new MioDataBaseHelper(getApplicationContext());
        Calendar data = Calendar.getInstance();
        Calendar data1 = Calendar.getInstance();
        Calendar data2 = Calendar.getInstance();

        data.set(Calendar.YEAR, 2017);
        data.set(Calendar.MONTH, 3-1);
        data.set(Calendar.DAY_OF_MONTH, 20);
        mydb.popola(data, "Viaggi da fare, Londra, Parigi e Tokyo", "aereo alle 15.00");

        data1.set(Calendar.YEAR, 2017);
        data1.set(Calendar.MONTH, 1-1);
        data1.set(Calendar.DAY_OF_MONTH, 31);

        mydb.popola(data1, "Ciao mondo", "Nonostante tutto la spesa da fare è la seguente: cibo cibo cibo");

        data2.set(Calendar.YEAR, 2016);
        data2.set(Calendar.MONTH, 3-1);
        data2.set(Calendar.DAY_OF_MONTH, 22);

        mydb.popola(data2, "Futurismo: l'apertura della mente", "fooo");*/


    }


    @Override
    public void onResume() {
        super.onResume();
        Log.e("Resume",">>>>>>>>>>>>>>>>>>>>>> resume");
        MioDataBaseHelper mydb = new MioDataBaseHelper(getApplicationContext());


        MemoAdapter adapter = new MemoAdapter(this, mydb.getAllNotes(), (Activity) this);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        MioDataBaseHelper mydb = new MioDataBaseHelper(getApplicationContext());

        mydb.elimina();


    }









}
