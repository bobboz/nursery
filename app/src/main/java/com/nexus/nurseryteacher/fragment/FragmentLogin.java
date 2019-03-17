package com.nexus.nurseryteacher.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nexus.nurseryteacher.R;


/**
 * Created by Yousef on 17/05/2017.
 */

public class FragmentLogin extends Fragment {
    public static FragmentLogin newInstance()
    {
        FragmentLogin login=new FragmentLogin();
        return login;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstance){
        View rootview=inflater.inflate(R.layout.activity_login_fragment,null);




        return rootview;
    }
    public String toString(){
        return "FragmentLogin";
    }
}

