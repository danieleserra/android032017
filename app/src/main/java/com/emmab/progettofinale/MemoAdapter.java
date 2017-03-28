package com.emmab.progettofinale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private SimpleDateFormat simple=new SimpleDateFormat("dd/MM", Locale.ITALIAN);

    public MemoAdapter(Context context, ArrayList<Note> preview, Activity myactivity)
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
        RelativeLayout layout=(RelativeLayout) convertView.findViewById(R.id.layout_memo);


       // txt=(TextView) convertView.findViewById(R.id.t_titolo);
      //  txt.setText(ai.getTitle());
        TextView txt_corpo=(TextView) convertView.findViewById(R.id.t_corpo);
        TextView txt_data=(TextView) convertView.findViewById(R.id.t_data);
        //EditText txt_title=(EditText) convertView.findViewById(R.id.show_memo_title);


        final String corpo=ai.getCorpo();
        final long id=ai.getId();
        final Calendar date=ai.getDate();
        final String title=ai.getTitle();

        txt_data.setText(simple.format(date.getTime()));
        Log.e(">>>>>>>>>>>>>>>>>>>>",""+date.getTime());

        if (corpo.length()>25)
            txt_corpo.setText (corpo.substring(0,25)+"...");
        else
            txt_corpo.setText (corpo);


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




        return convertView;




    }

    @Override
    public int getCount() {
        return preview.size();
    }



}
