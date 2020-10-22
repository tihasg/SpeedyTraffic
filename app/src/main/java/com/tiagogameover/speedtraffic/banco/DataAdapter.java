package com.tiagogameover.speedtraffic.banco;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiagogameover.speedtraffic.R;

import java.util.ArrayList;

public class DataAdapter extends BaseAdapter {
    private ArrayList<ListDataModel> listdata;
    private Activity activity;
    private LayoutInflater inflater;

    public DataAdapter(ArrayList<ListDataModel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(inflater==null)
            inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
            view=inflater.inflate(R.layout.row,null);
        TextView name= (TextView) view.findViewById(R.id.tvPing);
        TextView phone= (TextView)view.findViewById(R.id.tvdown);
        TextView upload= (TextView) view.findViewById(R.id.tvuplo);
        TextView data= (TextView) view.findViewById(R.id.tvdata);

        ListDataModel dataModel=listdata.get(position);
        name.setText(dataModel.getName());
        phone.setText(dataModel.getPhone());
        upload.setText(dataModel.getUpload());
        data.setText(dataModel.getData());

        return view;
    }
}
