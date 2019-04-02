package com.nexus.nurseryteacher.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.fragment.EventsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.getDrawable;

public class EventsAdapter extends PagerAdapter {

    private ArrayList<EventsFragment.EventDetailsObject> eventDetails;
    private LayoutInflater inflater;
    private Context context;
    private int eventMainPhoto;

    public EventsAdapter(ArrayList<EventsFragment.EventDetailsObject> eventDetails, Context context){

        this.eventDetails = eventDetails;
        this.context = context;
        //inflater = LayoutInflater.from(context);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.sliding_main_photo_events, null);
        eventMainPhoto = eventDetails.get(position).getEventMainPhoto();
        //ImageView imageView = view.findViewById(R.id.image);
        ImageView imageView = new ImageView(context);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog eventGalleryDialog;
                eventGalleryDialog = new Dialog(context);
                eventGalleryDialog.setContentView(R.layout.event_details_dialog);

                LinearLayout eventsGallery_SliderDots = eventGalleryDialog.findViewById(R.id.eventsGallery_SliderDots);
                ViewPager eventsGallery_viewPager = eventGalleryDialog.findViewById(R.id.event_gallery_viewpager);
                Button ok_btn = eventGalleryDialog.findViewById(R.id.ok_btn);
                ArrayList<String> urls = new ArrayList<>();
                final int dots_count;

                urls = eventDetails.get(position).getEventGallery();

                dots_count = urls.size();
                final ImageView[] dots = new ImageView[dots_count];
                final EventsFragment.EventsGalleryAdapter eventGalleryAdapter = new EventsFragment.EventsGalleryAdapter(urls, eventGalleryDialog.getContext());
                eventsGallery_viewPager.setAdapter(eventGalleryAdapter);

                for(int i = 0; i < dots_count; i++){

                    dots[i] = new ImageView(context);
                    dots[i].setImageDrawable(getDrawable(context, R.drawable.non_active_dot));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(8, 0, 8, 0);
                    eventsGallery_SliderDots.addView(dots[i], params);
                }
                dots[0].setImageDrawable(getDrawable(context, R.drawable.active_dot));
                eventsGallery_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        //currentEventTitle_txtV.setText(currentEvents.get(position).getEventTitle());

                    }
                    @Override
                    public void onPageSelected(int position) {

                        int i=0;
                        for(i = 0; i< dots_count; i++){
                            dots[i].setImageDrawable(getDrawable(context, R.drawable.non_active_dot));
                        }

                        dots[position].setImageDrawable(getDrawable(context, R.drawable.active_dot));
                    }
                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

                ok_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eventGalleryDialog.cancel();
                    }
                });
                eventGalleryDialog.show();
            }
        });
        //imageView.setImageResource(mainPhotosList[position]);
        Picasso.get().load(eventMainPhoto).fit().centerCrop().into(imageView);
        container.addView(imageView);

        return imageView;
    }

    @Override
    public int getCount() {
        return eventDetails.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;

    }
}
