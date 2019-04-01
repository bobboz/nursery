package com.nexus.nurseryteacher.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nexus.nurseryteacher.R;
import com.squareup.picasso.Picasso;

public class EventsAdapter extends PagerAdapter {

    private int[] mainPhotosList;
    private LayoutInflater inflater;
    private Context context;

    public EventsAdapter(int[] mainPhotosList , Context context){

        this.mainPhotosList = mainPhotosList;
        this.context = context;
        //inflater = LayoutInflater.from(context);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.sliding_main_photo_events, null);

        //ImageView imageView = view.findViewById(R.id.image);
        ImageView imageView = new ImageView(context);
        //imageView.setImageResource(mainPhotosList[position]);
        Picasso.get().load(mainPhotosList[position]).fit().centerCrop().into(imageView);
        container.addView(imageView);

        return imageView;
    }

    public int getImageByPosition(int position){

        return mainPhotosList[position];
    }

    @Override
    public int getCount() {
        return mainPhotosList.length;
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
