package com.nexus.nurseryteacher.adapter;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.activity.EditScheduleActivity;
import com.nexus.nurseryteacher.fragment.TimePickerFragment;
import com.nexus.nurseryteacher.model.SessionModel;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class EditScheduleRecyclerViewAdapter extends  RecyclerView.Adapter<EditScheduleRecyclerViewAdapter.ViewHolder>{

    private ArrayList<SessionModel> sessionsArrayList;
    private Context context;

    public EditScheduleRecyclerViewAdapter(Context context, ArrayList<SessionModel> sessionsArrayList){
        this.context = context;
        this.sessionsArrayList = sessionsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_schedule_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.header.setText(sessionsArrayList.get(i).getSessionHeader()+(i+1));
    }

    @Override
    public int getItemCount() {
        return sessionsArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements TimePickerDialog.OnTimeSetListener {

        private EditText classTitle, classDescription;
        private TextView classTime, reset_btn, header;
        private ImageButton clock, remove_session;
        private LinearLayout parentLayout;

        public ViewHolder(final View itemView){

            super(itemView);
            header = itemView.findViewById(R.id.sessionHeader);
            classTitle = itemView.findViewById(R.id.classTitle_txtV);
            classDescription = itemView.findViewById(R.id.classDescription_txtV);
            classTime = itemView.findViewById(R.id.classTime_txtV);
            clock = itemView.findViewById(R.id.clock_img_btn);
            parentLayout = itemView.findViewById(R.id.editSchedule_listItem_parentLayout);
            clock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TimePickerDialog timeFragment = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            String am_pm="am";

                            if(hourOfDay > 12){
                                hourOfDay = hourOfDay -12;
                                am_pm = "pm";
                            }
                            classTime.setText(hourOfDay+":"+minute+" "+am_pm);
                        }
                    }, 0, 0, false);
                    timeFragment.show();
                }
            });

            remove_session = itemView.findViewById(R.id.removeSession_btn);
            remove_session.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    sessionsArrayList.remove(position);
                    notifyItemRemoved(position);
                }
            });

            reset_btn = itemView.findViewById(R.id.resetSession_btn);
            reset_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    classTitle.setText("");
                    classDescription.setText("");
                    classTime.setText("");
                }
            });

        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            TextView time_editTxt = null;
            String am_pm="am";

            if(hourOfDay > 12){
                hourOfDay = hourOfDay -12;
                am_pm = "pm";
            }
            classTime.setText(hourOfDay+":"+minute+" "+am_pm);

        }
    }
}
