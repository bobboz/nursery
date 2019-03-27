package com.nexus.nurseryteacher.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.activity.EditScheduleActivity;
import com.nexus.nurseryteacher.model.Post;
import com.nexus.nurseryteacher.adapter.PostsAdapter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Yousef on 15/05/2017.
 */

public class PostsFragment extends Fragment {
    private RecyclerView postts_recyclerView;
    private static ArrayList<Post> posts;
    private PostsAdapter mAdapter;

    public static PostsFragment newInstance(ArrayList<Post> _posts) {
        PostsFragment homeFrag = new PostsFragment();
        posts = _posts;
        return homeFrag;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootview = inflater.inflate(R.layout.home_layout, null);

        postts_recyclerView = rootview.findViewById(R.id.recycler_view);
        postts_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getActivity().setTitle("Post");
/*
        items.add(new Post(getActivity(),R.drawable.tech, R.drawable.happy, "Mother's Day Celebration",new Date(), "Mrs Reem Salah", "Motivation is the driving force behind life-enhancing change. It comes from knowing exactly what you want to do and having an insatiable, burning desire to do what’s necessary to get it. " +
                "It keeps your dream on track as it is the power of motivation that keeps you going when the going gets tough."));
        items.add(new Post(getActivity(),R.drawable.tech, R.drawable.sample_class, "Control Your Own Level Of Motivation", new Date(), "Mrs Reem Salah", "cur fiscina assimilant.Nixs experimentum in moscua!Manducare satis ducunt ad castus detrius.A falsis, resistentia secundus frondator."));
*/
        mAdapter= new PostsAdapter(posts);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                posts.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(postts_recyclerView);

        postts_recyclerView.setAdapter(mAdapter);
        return rootview;

    }

}
