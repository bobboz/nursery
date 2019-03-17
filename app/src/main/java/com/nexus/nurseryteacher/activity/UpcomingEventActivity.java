package com.nexus.nurseryteacher.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.adapter.UpcomingEventsRecyclerViewAdapter;
import com.nexus.nurseryteacher.fragment.ClassesFragment;
import com.nexus.nurseryteacher.fragment.EventsFragment;
import com.nexus.nurseryteacher.fragment.PostsFragment;
import com.nexus.nurseryteacher.fragment.ScheduleFragment;
import com.nexus.nurseryteacher.model.UpcomingEventItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UpcomingEventActivity extends AppCompatActivity{

    private RecyclerView upcomingEvent_recyclerView;

    private ArrayList<UpcomingEventItem> upcomingEvents;
    private UpcomingEventItem upcomingEventItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.upcomingEvent_toolbar);
        setSupportActionBar(toolbar);

        setTitle("Upcoming Events");

        upcomingEvents = new ArrayList<>();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        initImagesAndTitles();
    }

    private void initImagesAndTitles() {
        // Event One
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Kidzania");
        upcomingEventItem.setEventDescription("Event date: 20-03-2019");
        upcomingEventItem.setEventDate("15-09-2019");
        upcomingEventItem.setEventPhoto(R.drawable.kidzania);
        upcomingEventItem.setEventFees("100 L.E.");
        upcomingEventItem.setEventPlace("5th settlement");
        upcomingEventItem.setComment("A trip for both Classes A Junior and B Junior");
        upcomingEvents.add(upcomingEventItem);
        // Event Two
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Crazy Water");
        upcomingEventItem.setEventDescription("Event date: 25-04-2019");
        upcomingEventItem.setEventDate("15-09-2019");
        upcomingEventItem.setEventPhoto(R.drawable.crazy_water);
        upcomingEventItem.setEventFees("80 L.E.");
        upcomingEventItem.setEventPlace("Zayed City");
        upcomingEventItem.setComment("A trip for Classes A Senior, B Senior and C Senior");
        upcomingEvents.add(upcomingEventItem);
        // Event Three
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Fantazy Circus");
        upcomingEventItem.setEventDescription("Event date: 30-06-2019");
        upcomingEventItem.setEventDate("15-09-2019");
        upcomingEventItem.setEventPhoto(R.drawable.fantazy_circus);
        upcomingEventItem.setEventFees("50 L-E.");
        upcomingEventItem.setEventPlace("6th of October");
        upcomingEventItem.setComment("A trip for Class A Junior, B Junior and C Junior");
        upcomingEvents.add(upcomingEventItem);
        // Event Four
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Family Park");
        upcomingEventItem.setEventDescription("Event date: 23-07-2019");
        upcomingEventItem.setEventDate("15-09-2019");
        upcomingEventItem.setEventPhoto(R.drawable.family_park);
        upcomingEventItem.setEventFees("100 L.E.");
        upcomingEventItem.setEventPlace("Al-rehab 2nd Gate");
        upcomingEventItem.setComment("A trip for Classes A Senior, B Senior and C Senior");
        upcomingEvents.add(upcomingEventItem);
        // Event Fives
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("My Town");
        upcomingEventItem.setEventDescription("Event date: 21-08-2019");
        upcomingEventItem.setEventDate("15-09-2019");
        upcomingEventItem.setEventPhoto(R.drawable.my_town);
        upcomingEventItem.setEventFees("100 L.E.");
        upcomingEventItem.setEventPlace("Dandy Mall");
        upcomingEventItem.setComment("A trip for both Class A Junior and B Junior");
        upcomingEvents.add(upcomingEventItem);
        // Event Six
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Dream Park");
        upcomingEventItem.setEventDescription("Event date: 15-09-2019");
        upcomingEventItem.setEventPhoto(R.drawable.dream_park);
        upcomingEventItem.setEventDate("15-09-2019");
        upcomingEventItem.setEventFees("80 L.E.");
        upcomingEventItem.setEventPlace("6th of October");
        upcomingEventItem.setComment("A trip for both Class A Junior and C Junior");
        upcomingEvents.add(upcomingEventItem);
        // Event Seven
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Faby Land");
        upcomingEventItem.setEventDescription("Event date: 25-09-2019");
        upcomingEventItem.setEventDate("15-09-2019");
        upcomingEventItem.setEventPhoto(R.drawable.kidzania);
        upcomingEventItem.setEventFees("100 L.E.");
        upcomingEventItem.setEventPlace("Dandy Mall");
        upcomingEventItem.setComment("A trip for both Class C Senior and D Senior");
        upcomingEvents.add(upcomingEventItem);
        // Event Eight
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Belly Beez");
        upcomingEventItem.setEventDescription("Event date: 25-10-2019");
        upcomingEventItem.setEventDate("15-09-2019");
        upcomingEventItem.setEventPhoto(R.drawable.bellybeez);
        upcomingEventItem.setEventFees("100 L.E.");
        upcomingEventItem.setEventPlace("Arab Mall");
        upcomingEventItem.setComment("A trip for Class A Senior, B Senior and C Senior");
        upcomingEvents.add(upcomingEventItem);
        // Event Nine
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Dream Park");
        upcomingEventItem.setEventDescription("Event date: 25-11-2019");
        upcomingEventItem.setEventDate("15-09-2019");
        upcomingEventItem.setEventPhoto(R.drawable.dream_park);
        upcomingEventItem.setEventFees("80 L.E.");
        upcomingEventItem.setEventPlace("5th settlement");
        upcomingEventItem.setComment("A trip for both Class A Junior and B Junior");
        upcomingEvents.add(upcomingEventItem);
        // Event Ten
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("My Town");
        upcomingEventItem.setEventDescription("Event date: 25-12-2019");
        upcomingEventItem.setEventDate("15-09-2019");
        upcomingEventItem.setEventPhoto(R.drawable.my_town);
        upcomingEventItem.setEventFees("100 L.E.");
        upcomingEventItem.setEventPlace("5th settlement");
        upcomingEventItem.setComment("A trip for both Class A Junior and B Junior");
        upcomingEvents.add(upcomingEventItem);

        intiRecyclerView();
    }

    private void intiRecyclerView() {
        upcomingEvent_recyclerView = findViewById(R.id.upcomingEvent_recyclerView);
        final UpcomingEventsRecyclerViewAdapter adapter = new UpcomingEventsRecyclerViewAdapter(upcomingEvents, this);
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                upcomingEvents.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(upcomingEvent_recyclerView);
        upcomingEvent_recyclerView.setAdapter(adapter);
        upcomingEvent_recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<UpcomingEventItem> getUpcomingEvent() {

        ArrayList<UpcomingEventItem> upcomingEvents = new ArrayList<>();
        UpcomingEventItem upcomingEventItem;

        // Event One
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Event One");
        upcomingEventItem.setEventDescription("Event One Description");
        upcomingEventItem.setEventPhoto(R.drawable.boy_1);
        upcomingEvents.add(upcomingEventItem);
        // Event Two
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Event Two");
        upcomingEventItem.setEventDescription("Event Two Description");
        upcomingEventItem.setEventPhoto(R.drawable.boy_2);
        upcomingEvents.add(upcomingEventItem);
        // Event Three
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Event Three");
        upcomingEventItem.setEventDescription("Event Three Description");
        upcomingEventItem.setEventPhoto(R.drawable.boy_3);
        upcomingEvents.add(upcomingEventItem);
        // Event Four
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Event Four");
        upcomingEventItem.setEventDescription("Event Four Description");
        upcomingEventItem.setEventPhoto(R.drawable.boy_4);
        upcomingEvents.add(upcomingEventItem);
        // Event Fives
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Event Five");
        upcomingEventItem.setEventDescription("Event Five Description");
        upcomingEventItem.setEventPhoto(R.drawable.boy_5);
        upcomingEvents.add(upcomingEventItem);
        // Event Six
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Event Six");
        upcomingEventItem.setEventDescription("Event Six Description");
        upcomingEventItem.setEventPhoto(R.drawable.boy_6);
        upcomingEvents.add(upcomingEventItem);
        // Event Seven
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Event Seven");
        upcomingEventItem.setEventDescription("Event Seven Description");
        upcomingEventItem.setEventPhoto(R.drawable.boy_7);
        upcomingEvents.add(upcomingEventItem);
        // Event Eight
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Event Eight");
        upcomingEventItem.setEventDescription("Event Eight Description");
        upcomingEventItem.setEventPhoto(R.drawable.boy_8);
        upcomingEvents.add(upcomingEventItem);
        // Event Nine
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Event Nine");
        upcomingEventItem.setEventDescription("Event Nine Description");
        upcomingEventItem.setEventPhoto(R.drawable.boy_9);
        upcomingEvents.add(upcomingEventItem);
        // Event Ten
        upcomingEventItem = new UpcomingEventItem();
        upcomingEventItem.setEventTitle("Event Ten");
        upcomingEventItem.setEventDescription("Event Ten Description");
        upcomingEventItem.setEventPhoto(R.drawable.boy_10);
        upcomingEvents.add(upcomingEventItem);

        return upcomingEvents;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

