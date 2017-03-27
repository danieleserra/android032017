package com.emmab.progettofinale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


public class MemoAdapter extends BaseAdapter {

    private List<PreviewMemo> preview=null;
    private Context context=null;
    private SimpleDateFormat simple=new SimpleDateFormat("dd/MM", Locale.ITALIAN);

    public MemoAdapter(Context context,List<PreviewMemo> preview)
    {
        this.preview=preview;
        this.context=context;
    }






    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.memo_icon, null);
        }
        PreviewMemo ai=(PreviewMemo) getItem(position);
        TextView txt=(TextView) convertView.findViewById(R.id.t_data);
        txt.setText(simple.format(ai.getDate()));
        txt=(TextView) convertView.findViewById(R.id.t_titolo);
        txt.setText(ai.getTitle());
        txt=(TextView) convertView.findViewById(R.id.t_corpo);
        txt.setText(ai.getCorpo());
        return convertView;




    }

    @Override
    public int getCount() {
        return 0;
    }
}
