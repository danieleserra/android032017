package com.emmab.progettofinale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Menu menu;
    public static boolean isChangeMenu = false;
    public static ArrayList<Long> elementi_selezionati = new ArrayList<Long>();
    public static ArrayList<View> view_selezionate = new ArrayList<View>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu=menu;

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (isChangeMenu){
            menu.findItem(R.id.delete_prev).setVisible(true);
            Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(100);
            isChangeMenu = false;
        }
            return super.onPrepareOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        MioDataBaseHelper mydb;

        switch(item.getItemId()) {

            case R.id.create:

                creaNota();

                return(true);

            case R.id.delete_prev:
                mydb = new MioDataBaseHelper(getApplicationContext());
                mydb.elimina_selezionati(elementi_selezionati);

                for (int i = 0; i < view_selezionate.size(); i++) {
                    view_selezionate.get(i).setVisibility(View.INVISIBLE);

                }
                startActivity(getIntent());
                finish();
                load();
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResume() {
        super.onResume();
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CA9B5D")));
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.parseColor("#9A6725"));


        load();


    }

    private void load() {
        MioDataBaseHelper mydb = new MioDataBaseHelper(getApplicationContext());



        MemoAdapter adapter = new MemoAdapter(this, mydb.getAllNotes(), (Activity) this, menu );
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        MioDataBaseHelper mydb = new MioDataBaseHelper(getApplicationContext());

        mydb.elimina();


    }

    private void creaNota(){
        Intent intent=new Intent(this,editing_activity.class);

        startActivity(intent);
        overridePendingTransition(R.animator.activity_in, R.animator.activity_out);
    }



    public static void aggiungi_selezionato(Long id, View v){
        elementi_selezionati.add(id);
        view_selezionate.add(v);

    }



}
