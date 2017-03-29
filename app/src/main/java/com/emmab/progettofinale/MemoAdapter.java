package com.emmab.progettofinale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class MemoAdapter extends BaseAdapter {

    private ArrayList<Note> preview=null;
    private Context context=null;
    private Activity myactivity = null;

    private SimpleDateFormat simple=new SimpleDateFormat("dd/MM/yy", Locale.ITALIAN);
    RelativeLayout layout;

    public MemoAdapter(Context context, ArrayList<Note> preview, Activity myactivity, Menu menu)
    {


        this.preview=preview;
        this.context=context;
        this.myactivity=myactivity;
    }






    @Override
    public Object getItem(int position) {
        return preview.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.memo_icon, null);
        }
        Note ai=(Note) getItem(position);
        layout=(RelativeLayout) convertView.findViewById(R.id.layout_memo);


       // txt=(TextView) convertView.findViewById(R.id.t_titolo);
      //  txt.setText(ai.getTitle());
        TextView txt_corpo=(TextView) convertView.findViewById(R.id.t_corpo);
        TextView txt_data=(TextView) convertView.findViewById(R.id.t_data);
        TextView txt_title=(TextView) convertView.findViewById(R.id.t_titolo);


        final String corpo=ai.getCorpo();
        final long id=ai.getId();
        final Calendar date=ai.getDate();
        final String title=ai.getTitle();

        txt_data.setText(simple.format(date.getTime()));

        if (corpo.length()>25)
            txt_corpo.setText (corpo.replace("\n"," ").substring(0,25)+"…");
        else
            txt_corpo.setText (corpo);

        if (title.length()>7)
            txt_title.setText (title.replace("\n"," ").substring(0,7)+"…");
        else
            txt_title.setText (title);




        layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,editing_activity.class);
                intent.putExtra("title",title);
                intent.putExtra("body",corpo);
                intent.putExtra("id",id);
                intent.putExtra("date",date.getTime().getTime());

                context.startActivity(intent);
                myactivity.overridePendingTransition(R.animator.activity_in, R.animator.activity_out);

            }
        });

        layout.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Animation rotate = AnimationUtils.loadAnimation(context, R.anim.vibrate);
                v.startAnimation(rotate);

                MainActivity.isChangeMenu = true;

                if (context instanceof MainActivity) {
                    ((MainActivity) context).onPrepareOptionsMenu(MainActivity.menu);
                }
                MainActivity.aggiungi_selezionato(id,v);

                //MenuItem item = menu.findItem(R.id.create);
                //item.setVisible(true);
                return true;

            }
        });




        return convertView;




    }

    @Override
    public int getCount() {
        return preview.size();
    }



}
