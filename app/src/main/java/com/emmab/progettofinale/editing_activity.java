package com.emmab.progettofinale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class editing_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_activity);
        Bundle bundle = getIntent().getExtras();
        String value= bundle.getString("value");


    }
}
