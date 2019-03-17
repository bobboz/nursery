package com.nexus.nurseryteacher.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nexus.nurseryteacher.R;

import java.util.ArrayList;

public class EventsActivity extends AppCompatActivity {

    private ArrayList<String> eventTitles;
    private ArrayList<String> eventsDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        setEventsDetails();
        initCurrentEventsView();
    }

    private void setEventsDetails(){

        eventTitles = new ArrayList<>();
        eventsDescription = new ArrayList<>();

        eventTitles.add("Event One Title");
        eventsDescription.add("Event One Description");

        eventTitles.add("Event Two Title");
        eventsDescription.add("Event Two Description");

        eventTitles.add("Event Three Title");
        eventsDescription.add("Event Three Description");

        eventTitles.add("Event Three Title");
        eventsDescription.add("Event Three Description");

        eventTitles.add("Event Four Title");
        eventsDescription.add("Event Four Description");

        eventTitles.add("Event Five Title");
        eventsDescription.add("Event Five Description");

        eventTitles.add("Event Six Title");
        eventsDescription.add("Event Six Description");

    }

    private void initCurrentEventsView(){

        /*RecyclerView currentEventRecyclerView = findViewById(R.id.current_events_recyclerView);
        CurrentEventsAdapter currentEventsAdapter = new CurrentEventsAdapter(eventTitles, eventsDescription, this);
        currentEventRecyclerView.setAdapter(currentEventsAdapter);
        currentEventRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView lastMonthEventRecyclerView = findViewById(R.id.lastMonth_events_recyclerView);
        lastMonthEventRecyclerView.setAdapter(currentEventsAdapter);
        lastMonthEventRecyclerView.setLayoutManager(new LinearLayoutManager(this));*/
    }
}
