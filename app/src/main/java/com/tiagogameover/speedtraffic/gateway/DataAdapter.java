
package com.tiagogameover.speedtraffic.gateway;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiagogameover.speedtraffic.R;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    List<DataInfo> dataList;

    public DataAdapter(List<DataInfo> dataList) {
        this.dataList = dataList;


    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        protected TextView vDate;
        protected TextView vWifi;
        protected TextView vMobile;
        protected TextView vTotal;

        public DataViewHolder(View itemView) {
            super(itemView);

            vDate = (TextView) itemView.findViewById(R.id.id_date);
            vWifi = (TextView) itemView.findViewById(R.id.id_wifi);
            vMobile = (TextView) itemView.findViewById(R.id.mobile);
            vTotal = (TextView) itemView.findViewById(R.id.total);
        }
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        return new DataViewHolder(itemView);
    }


//    @UiThread
//    protected void dataSetChanged() {
//        notifyDataSetChanged();
//    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {

        DataInfo di = dataList.get(position);

        holder.vDate.setText(di.date);
        holder.vWifi.setText(di.wifi);
        holder.vMobile.setText(di.mobile);
        holder.vTotal.setText(di.total);

    }

    public void updateData(List<DataInfo> temp) {
        this.dataList = temp;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {

        return dataList.size();
    }


}
