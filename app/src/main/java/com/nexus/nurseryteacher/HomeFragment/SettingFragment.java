package com.nexus.nurseryteacher.HomeFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nexus.nurseryteacher.R;


/**
 * Created by Yousef on 15/05/2017.
 */

public class SettingFragment extends Fragment {
    public static SettingFragment newInstance()
    {
        SettingFragment settingFragment=new SettingFragment();
        return settingFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstance){
        View rootview=inflater.inflate(R.layout.activity_splash,null);
        getActivity().setTitle("");



        return rootview;
    }
    public String toString(){
        return "SettingFragment";
    }
}