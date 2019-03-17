package com.nexus.nurseryteacher.mRecycler;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexus.nurseryteacher.R;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by Yousef on 15/05/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private ArrayList<Dataprovider> item;

    public RecyclerAdapter(ArrayList<Dataprovider> itemsData) {
        this.item = itemsData;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view);
        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.date.setText(item.get(position).getDate());
        holder.desc.setText(item.get(position).getDescription());
        holder.teacher.setText(item.get(position).getTeachername());
        holder.event.setText(item.get(position).getEventname());
        holder.imge.setImageResource(item.get(position).getC_image());
        holder.pic.setImageResource(item.get(position).getP_image());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder

    {
        ImageView imge,pic;
        TextView desc,teacher,event,date;
        View Cardview;
        public RecyclerViewHolder(View itemview){
            super(itemview);
            imge=(ImageView)itemView.findViewById(R.id.admin_img);

            pic=(ImageView)itemView.findViewById(R.id.post_image);
            desc=(TextView)itemView.findViewById(R.id.event_details_txtV);
            teacher=(TextView)itemView.findViewById(R.id.teacherName_txtV);
            event=(TextView)itemView.findViewById(R.id.eventName_txtv);
            date=(TextView)itemView.findViewById(R.id.date_txtV);
            Cardview=itemView.findViewById(R.id.card);
        }

    }
}