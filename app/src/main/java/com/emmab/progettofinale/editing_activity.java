package com.emmab.progettofinale;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class editing_activity extends AppCompatActivity {

   long id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_activity);
        getActionBar().setDisplayShowTitleEnabled(false);

        Dialog overlayInfo = new Dialog(editing_activity.this);
        // Making sure there's no title.
        overlayInfo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Making dialog content transparent.
        overlayInfo.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        // Removing window dim normally visible when dialog are shown.
        overlayInfo.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        // Setting position of content, relative to window.
        WindowManager.LayoutParams params = overlayInfo.getWindow().getAttributes();
        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 100;
        params.y = 20;
        // If user taps anywhere on the screen, dialog will be cancelled.
        overlayInfo.setCancelable(true);
        // Setting the content using prepared XML layout file.
        overlayInfo.setContentView(R.layout.dialog_over_editing);
        overlayInfo.show();


        load();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.editing_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        MioDataBaseHelper mydb = new MioDataBaseHelper(getApplicationContext());
        switch(item.getItemId()) {

                case R.id.save:

                EditText et_body = (EditText) findViewById(R.id.show_memo_body);
                EditText et_title = (EditText) findViewById(R.id.show_memo_title);

                load();
                mydb.updateNote(id,et_title.getText().toString(),et_body.getText().toString());

                Toast.makeText(this, "Nota salvata!", Toast.LENGTH_LONG).show();

                esci();
                return(true);
            case R.id.reset:
                load();

                Toast.makeText(this, "Modifiche cancellate!", Toast.LENGTH_LONG).show();
                return(true);
            case R.id.delete:
                mydb.deleteNote(id);

                Toast.makeText(this, "Nota cancellata!", Toast.LENGTH_LONG).show();
                esci();
                return(true);


        /*case R.id.about:
            Toast.makeText(this, R.string.about_toast, Toast.LENGTH_LONG).show();
            return(true);
        case R.id.exit:
            finish();
            return(true);*/

    }
        return(super.onOptionsItemSelected(item));
    }


    public void esci(){


        Intent intent=new Intent(this,MainActivity.class);


       startActivity(intent);
        overridePendingTransition(R.animator.editing_in, R.animator.editing_out);
    }

    public void load(){


        Bundle bundle = getIntent().getExtras();
        if (!(bundle==null)){
        id =bundle.getLong("id");
        long date=bundle.getLong("date");
        String corpo=bundle.getString("body");
        String title=bundle.getString("title");

        SimpleDateFormat simple=new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ITALIAN);

        EditText et_body = (EditText) findViewById(R.id.show_memo_body);
        EditText et_title = (EditText) findViewById(R.id.show_memo_title);
        TextView et_date = (TextView) findViewById(R.id.show_memo_date);
        TextView et_info = (TextView) findViewById(R.id.info_time);


        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(date);

        et_body.setText(corpo);
        et_title.setText(title);
        et_date.setText(simple.format(calendar.getTime()));
        int time=(int)(calendar.getTime().getTime()/1000);
        int current_time=(int)(Calendar.getInstance().getTime().getTime()/1000);
        int d_time=current_time-time;

        Log.e(">>>>>>>>>>>>>>>>>>>>","current "+Calendar.getInstance().getTime());
        Calendar c=Calendar.getInstance();
        c.setTimeInMillis(d_time*1000);
        Log.e(">>>>>>>>>>>>>>>>>>>>","differenza "+c.getTime());


        String value;
        if (d_time<(60)) {
            value = "secondi";
        }
        else{
            value= "minuto";
            time=1;
            if (d_time>(60*2)) {
                value = "minuti";
                time=d_time/60;
            }
            if (d_time>(60*60)) {
                value = "ora";
                time=1;
            }
            if (d_time>(60*60*2)) {
                value = "ore";
                time=d_time/(60*60);
            }
            if (d_time>(60*60*24)) {
                value = "giorno";
                time=1;
            }
            if (d_time>(60*60*24*2)) {
                value = "giorni";
                time=d_time/(60*60*24);
            }
            if (d_time>(60*60*24*30)) {
                value = "mese";
                time=1;
            }
            if (d_time>(60*60*24*30*2)) {
                value = "mesi";
                time=d_time/(60*60*24*30);
            }
            if (d_time>(60*60*24*365)) {
                value = "anno";
                time=1;
            }
            if (d_time>(60*60*24*365*2)) {
                time=d_time/(60*60*24*365);
                value = "anni";
            }

        }

        et_info.setText("ultima modifica: "+time+" "+value+" fa");
        et_info.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        et_info.setSingleLine(true);
        et_info.setMarqueeRepeatLimit(-1);
        et_info.setSelected(true);

        }else{




        }
    }

}
