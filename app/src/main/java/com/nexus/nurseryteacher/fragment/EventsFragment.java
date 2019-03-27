package com.nexus.nurseryteacher.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.activity.AddChild;
import com.nexus.nurseryteacher.activity.ClassDetailsActivity;
import com.nexus.nurseryteacher.activity.UpcomingEventActivity;
import com.nexus.nurseryteacher.adapter.ClassesAdapter;
import com.nexus.nurseryteacher.adapter.EventsAdapter;
import com.nexus.nurseryteacher.model.Class;
import com.nexus.nurseryteacher.model.Event;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static android.support.v4.content.ContextCompat.getDrawable;

public class EventsFragment extends Fragment {

    private ArrayList<Event> currentEvents;
    private ArrayList<Event> lastMonthEvents;
    private int[] mainPhotosList_current, mainPhotosList_lastMonth;
    private ViewPager currentEvent_viewPager, lastMonthEvent_viewPager;
    private TextView currentEventTitle_txtV, lastMonthEventTitle_txtV;
    private LinearLayout sliderDotspanel_current, sliderDotspanel_lastMonth;
    private int dotscount_current, dotscount_last;
    private ImageView[] dots_current, dots_last;

    private Button upcomingEvents_btn;

    public static EventsFragment newInstance()
    {
        EventsFragment eventsFragment=new EventsFragment();

        return eventsFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View rootview = inflater.inflate(R.layout.fragment_events_fragment,null);

        sliderDotspanel_current = rootview.findViewById(R.id.SliderDots);
        currentEventTitle_txtV = rootview.findViewById(R.id.current_event_title_label);
        currentEvent_viewPager = rootview.findViewById(R.id.current_events_viewpager);
        upcomingEvents_btn = rootview.findViewById(R.id.upcoming_events_btn);

        currentEvents = new ArrayList<>();
        lastMonthEvents = new ArrayList<>();

        currentEvents = getCurrentEvents();
        lastMonthEvents = getLastMonthEvents();

        //mainPhotosList_current = null;
        mainPhotosList_current = getCurrentEventsMainPhotos(currentEvents);

        //mainPhotosList_lastMonth = null;
        mainPhotosList_lastMonth = getLastMonthEventsMainPhotos(lastMonthEvents);

        EventsAdapter adapter = new EventsAdapter(mainPhotosList_current, getContext());
        currentEvent_viewPager.setAdapter(adapter);

        dotscount_current = adapter.getCount();
        dots_current = new ImageView[dotscount_current];

        for(int i = 0; i < dotscount_current; i++){

            dots_current[i] = new ImageView(getContext());
            dots_current[i].setImageDrawable(getDrawable(getContext(), R.drawable.non_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel_current.addView(dots_current[i], params);
        }

        currentEventTitle_txtV.setText(currentEvents.get(0).getEventTitle());
        dots_current[0].setImageDrawable(getDrawable(getContext(), R.drawable.active_dot));

        currentEvent_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentEventTitle_txtV.setText(currentEvents.get(position).getEventTitle());

            }
            @Override
            public void onPageSelected(int position) {

                int i=0;
                for(i = 0; i< dotscount_current; i++){
                    dots_current[i].setImageDrawable(getDrawable(getContext(), R.drawable.non_active_dot));
                }

                dots_current[position].setImageDrawable(getDrawable(getContext(), R.drawable.active_dot));
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        currentEvent_viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Open Event Details ID:"+currentEvent_viewPager.getCurrentItem(),Toast.LENGTH_LONG).show();
            }
        });

        /* ----------------------- */
        /*  For Last Month Events */

        sliderDotspanel_lastMonth = (LinearLayout) rootview.findViewById(R.id.lastMonthEventsSliderDots);
        lastMonthEventTitle_txtV = rootview.findViewById(R.id.lastMonth_event_title_label);
        lastMonthEvent_viewPager = rootview.findViewById(R.id.lastMonth_events_viewpager);

        EventsAdapter adapter_2 = new EventsAdapter(mainPhotosList_lastMonth, getContext());
        lastMonthEvent_viewPager.setAdapter(adapter_2);

        dotscount_last = adapter_2.getCount();
        dots_last = new ImageView[dotscount_last];

        for(int i = 0; i < dotscount_last; i++){

            dots_last[i] = new ImageView(getContext());
            dots_last[i].setImageDrawable(getDrawable(getContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel_lastMonth.addView(dots_last[i], params);

        }

        lastMonthEventTitle_txtV.setText(lastMonthEvents.get(0).getEventTitle());
        dots_last[0].setImageDrawable(getDrawable(getContext(), R.drawable.active_dot));

        lastMonthEvent_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                lastMonthEventTitle_txtV.setText(lastMonthEvents.get(position).getEventTitle());

            }

            @Override
            public void onPageSelected(int position) {

                int i=0;
                for(i = 0; i< dotscount_last; i++){
                    dots_last[i].setImageDrawable(getDrawable(getContext(), R.drawable.non_active_dot));
                }

                dots_last[position].setImageDrawable(getDrawable(getContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        lastMonthEvent_viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Open Event Details ID:"+lastMonthEvent_viewPager.getCurrentItem(),Toast.LENGTH_LONG).show();
            }
        });

        /* ------------------------------- */

        upcomingEvents_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UpcomingEventActivity.class);
                startActivity(intent);
            }
        });

        getActivity().setTitle("Events");

        return rootview;
    }

    private ArrayList<Event> getCurrentEvents(){

        Event event = new Event();
        //set dummy data
        event.setEventTitle("B & D");
        event.setEventDescription("Event One Description");
        event.setEventDate("22-01-2018");
        event.setEventMainPhoto(R.drawable.b_and_d);
        event.setEventGallery(new int[]{R.drawable.b_and_d_1, R.drawable.b_and_d_2,R.drawable.b_and_d_3, R.drawable.b_and_d_4});

        currentEvents.add(event);

        event = new Event();
        event.setEventTitle("Belly Beez");
        event.setEventDescription("Event Two Description");
        event.setEventDate("22-02-2018");
        event.setEventMainPhoto(R.drawable.bellybeez);
        event.setEventGallery(new int[]{R.drawable.bellybeez_1, R.drawable.bellybeez_2,R.drawable.bellybeez_3});

        currentEvents.add(event);

        event = new Event();
        event.setEventTitle("Fantazy Circus");
        event.setEventDescription("Event Three Description");
        event.setEventDate("22-03-2018");
        event.setEventMainPhoto(R.drawable.fantazy_circus);
        event.setEventGallery(new int[]{R.drawable.circus_1, R.drawable.circus_2});

        currentEvents.add(event);

        event = new Event();
        event.setEventTitle("Dream Park");
        event.setEventDescription("Event Four Description");
        event.setEventDate("22-04-2018");
        event.setEventMainPhoto(R.drawable.dream_park);
        event.setEventGallery(new int[]{R.drawable.dreampark_1, R.drawable.dreampark_2,R.drawable.dreampark_3, R.drawable.dreampark_4, R.drawable.dreampark_5, R.drawable.dreampark_6});

        currentEvents.add(event);

        return currentEvents;
    }

    private int[] getCurrentEventsMainPhotos(ArrayList<Event> currentEvents){

        int[] mainPhotosList = new int[currentEvents.size()];

        for(int i=0; i < currentEvents.size(); i++){
            mainPhotosList[i] = currentEvents.get(i).getEventMainPhoto();
        }

        return mainPhotosList;

    }

    private ArrayList<Event> getLastMonthEvents(){

        Event event = new Event();
        //set dummy data
        event.setEventTitle("Crazy Water");
        event.setEventDescription("Last Month Event One Description");
        event.setEventDate("22-01-2018");
        event.setEventMainPhoto(R.drawable.crazy_water);
        event.setEventGallery(new int[]{R.drawable.crazywater_1, R.drawable.crazywater_2,R.drawable.crazywater_3});

        lastMonthEvents.add(event);

        event = new Event();
        event.setEventTitle("Dream Park");
        event.setEventDescription("Last Month Event Two Description");
        event.setEventDate("22-02-2018");
        event.setEventMainPhoto(R.drawable.dream_park);
        event.setEventGallery(new int[]{R.drawable.dreampark_5, R.drawable.dreampark_2,R.drawable.dreampark_4, R.drawable.dreampark_1});

        lastMonthEvents.add(event);

        event = new Event();
        event.setEventTitle("Belly Beez");
        event.setEventDescription("Last Month Event Three Description");
        event.setEventDate("22-03-2018");
        event.setEventMainPhoto(R.drawable.bellybeez);
        event.setEventGallery(new int[]{R.drawable.bellybeez_1, R.drawable.bellybeez_2,R.drawable.bellybeez_3, R.drawable.bellybeez_4});

        lastMonthEvents.add(event);

        return lastMonthEvents;
    }

    private int[] getLastMonthEventsMainPhotos(ArrayList<Event> lastMonthEvents){

        int[] mainPhotosList = new int[lastMonthEvents.size()];

        for(int i=0; i < lastMonthEvents.size(); i++){
            mainPhotosList[i] = lastMonthEvents.get(i).getEventMainPhoto();
        }

        return mainPhotosList;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        if(item.getItemId() == R.id.action_editCurrentEvents){

        }
        else if(item.getItemId() == R.id.action_editLastMonthEvents){

        }
        return true;
    }
}
