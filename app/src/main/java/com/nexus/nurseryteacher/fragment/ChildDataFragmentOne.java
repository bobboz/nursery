package com.nexus.nurseryteacher.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.model.Child;
import com.rtugeek.android.colorseekbar.ColorSeekBar;

public class ChildDataFragmentOne extends Fragment {

    private ImageView profileImage;
    private TextView childName_editTxt, childFatherName_editTxt, childMotherName_editTxt, childFatherNumber_editTxt, childMotherNumber_editTxt, childID_editTxt, childBDate_editTxt, childAge_editTxt, childClassName_editTxt, comment_txtView;
    private Child child;

    public ChildDataFragmentOne() {
    }

    public static ChildDataFragmentOne newInstance(Child child) {
        ChildDataFragmentOne fragment = new ChildDataFragmentOne();
        fragment.child = child;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_profile_data_fragment_one, container, false);

        profileImage = view.findViewById(R.id.childPicture_imgView);
        childName_editTxt = view.findViewById(R.id.childName_textView);
        childID_editTxt = view.findViewById(R.id.childID_textView);
        childFatherName_editTxt = view.findViewById(R.id.childFatherName_value);
        childFatherNumber_editTxt = view.findViewById(R.id.childFatherNumber_value);
        childMotherName_editTxt = view.findViewById(R.id.childMotherName_value);
        childMotherNumber_editTxt = view.findViewById(R.id.childMotherNumber_value);
        childBDate_editTxt = view.findViewById(R.id.childBDate_value);
        childAge_editTxt = view.findViewById(R.id.childAge_value);
        childClassName_editTxt = view.findViewById(R.id.childClassName_value);
        comment_txtView = view.findViewById(R.id.childComment_value);

        setData();

        return view;
    }

    public void setData(){
        profileImage.setImageResource(child.getChildPicture());
        childName_editTxt.setText(child.getChildName());
        childFatherName_editTxt.setText(child.getChildFatherName());
        childMotherName_editTxt.setText(child.getChildMotherName());
        childMotherNumber_editTxt.setText(child.getChildMotherNumber());
        childFatherNumber_editTxt.setText(child.getChildFatherNumber());
        childID_editTxt.setText(child.getChildID());
        childAge_editTxt.setText(child.getChildAge());
        childBDate_editTxt.setText(child.getChildBirthDate());
        childClassName_editTxt.setText(child.getChildClass());
        comment_txtView.setText(child.getComment());
    }

    /*
    public void setSelectedChild(Child child){
        this.child = child;
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
