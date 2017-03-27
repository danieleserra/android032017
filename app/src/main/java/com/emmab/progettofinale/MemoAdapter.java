package com.emmab.progettofinale;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MemoAdapter extends BaseAdapter {

    private ArrayList<PreviewMemo> preview=null;
    private Context context=null;
    private SimpleDateFormat simple=new SimpleDateFormat("dd/MM", Locale.ITALIAN);

    public MemoAdapter(Context context,ArrayList<PreviewMemo> preview)
    {
        this.preview=preview;
        this.context=context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.memo_icon, null);
        }
        PreviewMemo ai=(PreviewMemo) getItem(position);
        RelativeLayout layout=(RelativeLayout) convertView.findViewById(R.id.layout_memo);
        TextView txt=(TextView) convertView.findViewById(R.id.t_data);
        txt.setText(simple.format(ai.getDate().getTime()));
       // txt=(TextView) convertView.findViewById(R.id.t_titolo);
      //  txt.setText(ai.getTitle());
        txt=(TextView) convertView.findViewById(R.id.t_corpo);
        txt.setText(ai.getCorpo());



        layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,editing_activity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("value",position);
                context.startActivity(intent);
            }
        });



        return convertView;




    }

    @Override
    public int getCount() {
        return preview.size();
    }
}
