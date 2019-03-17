package com.nexus.nurseryteacher.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.activity.EditScheduleActivity;
import com.nexus.nurseryteacher.adapter.DaysAdapter;
import com.nexus.nurseryteacher.adapter.TimeLineAdapter;
import com.nexus.nurseryteacher.model.TimeLineModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/*
import devs.mulham.horizontalcalendar.*;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
*/

public class ScheduleFragment extends Fragment{

    DaysAdapter daysAdapter = new DaysAdapter();
    TimeLineAdapter timeLineAdapter;
    List<Date> days;

    // for weekDaysView
    TextView sunday_date, monday_date,  tuesday_date, wednesday_date, thursday_date;
    private TextView date_value, day_value;

    List<TimeLineModel> timeLineModels = new ArrayList<>();
    private Button editSchedule_btn;
    RecyclerView recyclerView;

    public static ScheduleFragment newInstance() {
        ScheduleFragment scheduleFragment = new ScheduleFragment();
        return scheduleFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootview = inflater.inflate(R.layout.fragment_schedule, null);

        editSchedule_btn = rootview.findViewById(R.id.editSchedule_btn);
        editSchedule_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditScheduleActivity.class);
                startActivity(intent);
            }
        });

        date_value = rootview.findViewById(R.id.date_value);

        Calendar c = Calendar.getInstance();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        String day = formattedDate.substring(0, formattedDate.indexOf(","));

        date_value.setText(formattedDate);

        /*
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        View weekDaysView = inflater.inflate(R.layout.schedule_calendar_layout, null);
        sunday_date = weekDaysView.findViewById(R.id.sunday_date);
        monday_date = weekDaysView.findViewById(R.id.monday_date);
        tuesday_date = weekDaysView.findViewById(R.id.tuesday_date);
        wednesday_date = weekDaysView.findViewById(R.id.wednesday_date);
        thursday_date = weekDaysView.findViewById(R.id.thursday_date);

        highlightCurrentDay(inflater.inflate(R.layout.schedule_calendar_layout,null));
        */



        /*days = getWeek();


        recyclerView = (RecyclerView) rootview.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        timeLineModels.add(new TimeLineModel("Breakfast", "Eheu, tumultumque!"));
        timeLineModels.add(new TimeLineModel("English", "Hydras mori! Teres genetrix etiam vitares xiphias est."));
        timeLineModels.add(new TimeLineModel("Math", "Genetrixs manducare! Pol."));
        timeLineModels.add(new TimeLineModel("Garden", "Nunquam perdere nix. Sunt castores contactus alter, dexter fluctuses."));
//        daysAdapter.setDaysList(days);
//        daysScrollView.setAdapter(daysAdapter);

        timeLineAdapter = new TimeLineAdapter(timeLineModels);
        recyclerView.setAdapter(timeLineAdapter);*/
        getActivity().setTitle("Schedule");
        return rootview;
    }

    private List<Date> getWeek()
    {
        ArrayList<Date> week = new ArrayList<>();
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);

        c.add(Calendar.DAY_OF_MONTH,-2);

        for(int i=0; i<4 ; i++) {
            c.add(Calendar.DATE, +1);
            week.add(c.getTime());
        }

        return week;
    }
/*
    private void highlightCurrentDay(View calenderView){

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        String day = formattedDate.substring(0, formattedDate.indexOf("-"));
        Toast.makeText(getContext(), "Date:"+formattedDate+"\n Day:"+day, Toast.LENGTH_SHORT).show();

    }*/
}
