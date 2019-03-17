package com.nexus.nurseryteacher.HomeFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.mRecycler.Dataprovider;
import com.nexus.nurseryteacher.mRecycler.RecyclerAdapter;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Yousef on 15/05/2017.
 */

public class FirstFragment extends Fragment {
    private RecyclerView rv;
    private ArrayList<Dataprovider> items =new ArrayList<>();
    private RecyclerAdapter mAdapter;

    public static FirstFragment newInstance() {
        FirstFragment homeFrag = new FirstFragment();
        return homeFrag;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootview = inflater.inflate(R.layout.home_layout, null);

        rv = (RecyclerView) rootview.findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        getActivity().setTitle("Post");

        items.add(new Dataprovider(getActivity(),R.drawable.tech, R.drawable.happy, "Mother's Day Celebration",new Date(), "Mrs Reem Salah", "Motivation is the driving force behind life-enhancing change. It comes from knowing exactly what you want to do and having an insatiable, burning desire to do what’s necessary to get it. " +
                "It keeps your dream on track as it is the power of motivation that keeps you going when the going gets tough."));
        items.add(new Dataprovider(getActivity(),R.drawable.tech, R.drawable.sample_class, "Control Your Own Level Of Motivation", new Date(), "Mrs Reem Salah", "cur fiscina assimilant.Nixs experimentum in moscua!Manducare satis ducunt ad castus detrius.A falsis, resistentia secundus frondator."));

        mAdapter= new RecyclerAdapter(items);
        rv.setAdapter(mAdapter);
        return rootview;

    }

}
