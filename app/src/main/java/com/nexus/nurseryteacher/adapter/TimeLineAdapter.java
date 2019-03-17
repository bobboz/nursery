package com.nexus.nurseryteacher.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.model.TimeLineModel;

import java.util.List;

/**
 * Created by Sara on 7/10/2017.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder> {

    private List<TimeLineModel> mFeedList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public TimeLineAdapter(List<TimeLineModel> feedList) {
        mFeedList = feedList;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view;

        view = mLayoutInflater.inflate(R.layout.item_timeline, parent, false);

        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {

        TimeLineModel timeLineModel = mFeedList.get(position);

        holder.mActivityName.setText(timeLineModel.getActivityName());
        holder.mActivityDescription.setText(timeLineModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return (mFeedList != null ? mFeedList.size() : 0);
    }

    public class TimeLineViewHolder extends RecyclerView.ViewHolder {

        TextView mActivityName;
        TextView mActivityDescription;

        public TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);

            mActivityName = (TextView) itemView.findViewById(R.id.text_timeline_activity_name);
            mActivityDescription = (TextView) itemView.findViewById(R.id.text_timeline_description);

        }
    }

}