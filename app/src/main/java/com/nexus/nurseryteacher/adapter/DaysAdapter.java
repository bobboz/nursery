package com.nexus.nurseryteacher.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nexus.nurseryteacher.R;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Sara on 7/10/2017.
 */

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.MyViewHolder> {

    private List<Date> horizontalList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtView;

        public MyViewHolder(View view) {
            super(view);
            txtView = (TextView) view.findViewById(R.id.day_name);

        }
    }


    public DaysAdapter() {
    }

    public void setDaysList(List<Date> horizontalList) {
        this.horizontalList = horizontalList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Calendar c = Calendar.getInstance();
        c.setTime(((Date) horizontalList.get(position)));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String weekday = new DateFormatSymbols().getShortWeekdays()[dayOfWeek];

        holder.txtView.setText(weekday);
//        holder.txtView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,holder.txtView.getText().toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }
}
