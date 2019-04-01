package com.nexus.nurseryteacher.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.activity.UpcomingEventActivity;
import com.nexus.nurseryteacher.adapter.EventsAdapter;
import com.nexus.nurseryteacher.model.Event;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.getDrawable;

public class EventsFragment extends Fragment {

    private ArrayList<Event> currentEvents;
    private ArrayList<Event> lastMonthEvents;
    private int[] mainPhotosList_current, mainPhotosList_lastMonth;
    private ViewPager currentEvent_viewPager, lastMonthEvent_viewPager;
    private TextView currentEventTitle_txtV, lastMonthEventTitle_txtV;
    private LinearLayout sliderDotspanel_current, sliderDotspanel_lastMonth;


    private Dialog eventGalleryDialog;
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
        final int dotscount_current, dotscount_last;
        final ImageView[] dots_current, dots_last;

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

        final EventsAdapter adapter = new EventsAdapter(mainPhotosList_current, getContext());
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

        currentEvent_viewPager.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View selectedView) {
                eventGalleryDialog = new Dialog(getContext());
                eventGalleryDialog.setContentView(R.layout.event_details_dialog);

                LinearLayout eventsGallery_SliderDots = eventGalleryDialog.findViewById(R.id.eventsGallery_SliderDots);
                ViewPager eventsGallery_viewPager = eventGalleryDialog.findViewById(R.id.event_gallery_viewpager);
                Button ok_btn = eventGalleryDialog.findViewById(R.id.ok_btn);
                ArrayList<String> urls = new ArrayList<>();
                int dots_count =0;

                for(int i=0; i< currentEvents.size();i++) {
                    if (currentEventTitle_txtV.getText().toString().equals(currentEvents.get(i).getEventTitle())) {
                        urls = currentEvents.get(i).getEventGallery();
                    }
                }

                dots_count = urls.size();
                ImageView[] dots = new ImageView[dots_count];
                final EventsGalleryAdapter eventGalleryAdapter = new EventsGalleryAdapter(urls, getContext());
                eventsGallery_viewPager.setAdapter(eventGalleryAdapter);

                for(int i = 0; i < dots_count; i++){

                    dots[i] = new ImageView(getContext());
                    dots[i].setImageDrawable(getDrawable(getContext(), R.drawable.non_active_dot));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(8, 0, 8, 0);
                    eventsGallery_SliderDots.addView(dots[i], params);
                }
                dots[0].setImageDrawable(getDrawable(getContext(), R.drawable.active_dot));

                ok_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eventGalleryDialog.cancel();
                    }
                });
                eventGalleryDialog.show();
                return true;
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
        String imgPath="";
        ArrayList<String> eventGallery;
        //set dummy data
        event.setEventTitle("B & D");
        event.setEventDescription("Event One Description");
        event.setEventDate("22-01-2018");
        event.setEventMainPhoto(R.drawable.b_and_d);
        //Event Gallery
        eventGallery = new ArrayList<>();
        imgPath = "D:\\D:\\Github\\Nursery\\events\\b_and_d_1.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\b_and_d_2.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\b_and_d_3.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\b_and_d_4.jpg";
        eventGallery.add(imgPath);
        event.setEventGallery(eventGallery);
        currentEvents.add(event);

        event = new Event();
        event.setEventTitle("Belly Beez");
        event.setEventDescription("Event Two Description");
        event.setEventDate("22-02-2018");
        event.setEventMainPhoto(R.drawable.bellybeez);
        //Event Gallery
        eventGallery = new ArrayList<>();
        imgPath = "D:\\Github\\Nursery\\events\\bellybeez_1.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\bellybeez_2.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\events\\bellybeez_3.jpg";
        eventGallery.add(imgPath);
        event.setEventGallery(eventGallery);
        currentEvents.add(event);

        event = new Event();
        event.setEventTitle("Fantazy Circus");
        event.setEventDescription("Event Three Description");
        event.setEventDate("22-03-2018");
        event.setEventMainPhoto(R.drawable.fantazy_circus);
        //Event Gallery
        eventGallery = new ArrayList<>();
        imgPath = "D:\\Github\\Nursery\\events\\circus_1.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\circus_2.jpg";
        eventGallery.add(imgPath);
        event.setEventGallery(eventGallery);
        currentEvents.add(event);

        event = new Event();
        event.setEventTitle("Dream Park");
        event.setEventDescription("Event Four Description");
        event.setEventDate("22-04-2018");
        event.setEventMainPhoto(R.drawable.dream_park);
        //Event Gallery
        eventGallery = new ArrayList<>();
        imgPath = "D:\\Github\\Nursery\\events\\dreampark_1.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\dreampark_2.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\dreampark_3.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\dreampark_4.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\dreampark_5.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\dreampark_6.jpg";
        eventGallery.add(imgPath);
        event.setEventGallery(eventGallery);
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
        String imgPath="";
        ArrayList<String> eventGallery;
        //set dummy data
        event.setEventTitle("Crazy Water");
        event.setEventDescription("Last Month Event One Description");
        event.setEventDate("22-01-2018");
        event.setEventMainPhoto(R.drawable.crazy_water);
        //event.setEventGallery(new int[]{R.drawable.crazywater_1, R.drawable.crazywater_2,R.drawable.crazywater_3});
        //Event Gallery
        eventGallery = new ArrayList<>();
        imgPath = "D:\\Github\\Nursery\\events\\crazywater_1.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\crazywater_2.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\crazywater_3.jpg";
        eventGallery.add(imgPath);
        event.setEventGallery(eventGallery);
        lastMonthEvents.add(event);

        event = new Event();
        event.setEventTitle("Dream Park");
        event.setEventDescription("Last Month Event Two Description");
        event.setEventDate("22-02-2018");
        event.setEventMainPhoto(R.drawable.dream_park);
        //event.setEventGallery(new int[]{R.drawable.dreampark_5, R.drawable.dreampark_2,R.drawable.dreampark_4, R.drawable.dreampark_1});
        //Event Gallery
        eventGallery = new ArrayList<>();
        imgPath = "D:\\Github\\Nursery\\events\\dreampark_5.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\dreampark_2.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\dreampark_4.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\dreampark_1.jpg";
        eventGallery.add(imgPath);
        event.setEventGallery(eventGallery);
        lastMonthEvents.add(event);

        event = new Event();
        event.setEventTitle("Belly Beez");
        event.setEventDescription("Last Month Event Three Description");
        event.setEventDate("22-03-2018");
        event.setEventMainPhoto(R.drawable.bellybeez);
        //event.setEventGallery(new int[]{R.drawable.bellybeez_1, R.drawable.bellybeez_2,R.drawable.bellybeez_3, R.drawable.bellybeez_4});
        //Event Gallery
        eventGallery = new ArrayList<>();
        imgPath = "D:\\Github\\Nursery\\events\\bellybeez_1.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\bellybeez_2.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\bellybeez_3.jpg";
        eventGallery.add(imgPath);
        imgPath = "D:\\Github\\Nursery\\events\\bellybeez_4.jpg";
        eventGallery.add(imgPath);
        event.setEventGallery(eventGallery);
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

    private class EventsGalleryAdapter extends PagerAdapter {

        private ArrayList<String> gallery;
        private LayoutInflater inflater;
        private Context context;

        public EventsGalleryAdapter(ArrayList<String> gallery , Context context){

            this.gallery = gallery;
            this.context = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.sliding_events_gallery, null);

            //ImageView imageView = view.findViewById(R.id.image);
            ImageView imageView = new ImageView(context);
            //imageView.setImageResource(mainPhotosList[position]);
            Picasso.get().load(gallery.get(position)).fit().centerCrop().into(imageView);
            container.addView(imageView);

            return imageView;
        }

        public String getImagePathByPosition(int position){

            return gallery.get(position);
        }

        @Override
        public int getCount() {
            return gallery.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return false;
        }
    }
}
