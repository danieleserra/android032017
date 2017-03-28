package com.emmab.progettofinale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class editing_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_activity);

        Bundle bundle = getIntent().getExtras();
        long id =bundle.getLong("id");
        long date=bundle.getLong("date");
        String corpo=bundle.getString("body");
        String title=bundle.getString("title");

        SimpleDateFormat simple=new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ITALIAN);

        EditText et_body = (EditText) findViewById(R.id.show_memo_body);
        EditText et_title = (EditText) findViewById(R.id.show_memo_title);
        TextView et_date = (TextView) findViewById(R.id.show_memo_date);


        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(date);

        et_body.setText(corpo);
        et_title.setText(title);
        et_date.setText(simple.format(calendar.getTime()));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.editing_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        switch(item.getItemId()) {

            case R.id.save:

                EditText et_body = (EditText) findViewById(R.id.show_memo_body);
                EditText et_title = (EditText) findViewById(R.id.show_memo_title);
                MioDataBaseHelper mydb = new MioDataBaseHelper(getApplicationContext());

                mydb.updateNote(id,et_title.getText().toString(),et_body.getText().toString());
///aggiunmgere attributo lon id senza final e static

                Toast.makeText(this, "Nota salvata!", Toast.LENGTH_LONG).show();

                esci();
                return(true);
            case R.id.reset:

                Toast.makeText(this, "Nota cancellata!", Toast.LENGTH_LONG).show();
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
}
