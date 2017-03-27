package com.emmab.progettofinale;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<PreviewMemo> a_pm = new ArrayList<PreviewMemo>();


    Calendar data = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        Calendar d=Calendar.getInstance();
        data.set(2017,03,18);
        PreviewMemo pw=new PreviewMemo(d,"Buongiornissimo","Un giorno lui si trasformò era");
        a_pm.add(pw);
        data.set(2017,03,20);
        pw=new PreviewMemo(d,"Inglese futuristico","CIao a tutti mi chiamo");
        a_pm.add(pw);
        data.set(2017,03,23);
        pw=new PreviewMemo(d,"CIao mondo","Londra, venezia, tokyo");
        a_pm.add(pw);







        MemoAdapter adapter=new MemoAdapter(this, a_pm);
        GridView gridView=(GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);

        final View thumb1View = findViewById(R.id.imageView);
        thumb1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumb1View, R.drawable.image1);
            }
        });



    }

    public void onShouldStartActivity() {
        // creation of intent to start activity2


        // Following the documentation, right after starting the activity
        // we override the transition
        overridePendingTransition(R.animator.activity_in, R.animator.activity_out);
    }









}
