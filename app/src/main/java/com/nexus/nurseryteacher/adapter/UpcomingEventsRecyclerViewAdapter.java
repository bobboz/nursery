package com.nexus.nurseryteacher.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.model.UpcomingEventItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpcomingEventsRecyclerViewAdapter extends RecyclerView.Adapter<UpcomingEventsRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private Dialog eventDetailsDialog;
    private Button ok_btn;
    private ImageView eventPhoto;
    private ArrayList<UpcomingEventItem> upcomingEvents;

    public UpcomingEventsRecyclerViewAdapter(ArrayList<UpcomingEventItem> upcomingEvents, Context context) {
        this.upcomingEvents = upcomingEvents;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parentView, int i) {

        View view = LayoutInflater.from(parentView.getContext()).inflate(R.layout.upcoming_events_list_item, parentView, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        Picasso.get().load(upcomingEvents.get(i).getEventImage()).fit().centerCrop().into(viewHolder.eventImage);

        viewHolder.eventTitle.setText(upcomingEvents.get(i).getEventTitle());
        viewHolder.eventDescription.setText(upcomingEvents.get(i).getEventDescription());

        final int selectedIndex = i;
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventDetailsDialog = new Dialog(context);
                eventDetailsDialog.setContentView(R.layout.upcoming_events_details_dialog);

                TextView eventTitle = eventDetailsDialog.findViewById(R.id.event_title_value);
                TextView eventPlace = eventDetailsDialog.findViewById(R.id.event_place_value);
                TextView eventDate = eventDetailsDialog.findViewById(R.id.event_date_value);
                TextView eventFee = eventDetailsDialog.findViewById(R.id.event_fees_value);
                TextView comment = eventDetailsDialog.findViewById(R.id.event_comment_value);

                eventTitle.setText(upcomingEvents.get(selectedIndex).getEventTitle());
                eventPlace.setText(upcomingEvents.get(selectedIndex).getEventPlace());
                eventDate.setText(upcomingEvents.get(selectedIndex).getEventDate());
                eventFee.setText(upcomingEvents.get(selectedIndex).getEventFees());
                comment.setText(upcomingEvents.get(selectedIndex).getComment());

                ok_btn = eventDetailsDialog.findViewById(R.id.upcomingEventDialog_ok_btn);
                ok_btn.setEnabled(true);
                ok_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eventDetailsDialog.cancel();
                    }
                });
                eventPhoto = eventDetailsDialog.findViewById(R.id.event_photo);
                eventPhoto.setImageDrawable(viewHolder.eventImage.getDrawable());

                eventDetailsDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return upcomingEvents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView eventTitle, eventDescription;
        private CircleImageView eventImage;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView){
            super(itemView);
            eventTitle = itemView.findViewById(R.id.upcomingEvent_list_item_title_txtV);
            eventDescription = itemView.findViewById(R.id.upcomingEvent_list_item_description_txtV);
            eventImage = itemView.findViewById(R.id.upcomingEventMainPhoto_imgV);
            parentLayout = itemView.findViewById(R.id.upcomingEvent_listItem_relativeLayout);
        }

    }
}
