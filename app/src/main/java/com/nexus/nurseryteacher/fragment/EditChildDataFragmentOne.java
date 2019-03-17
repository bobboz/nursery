package com.nexus.nurseryteacher.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.activity.EditChildDataActivity;
import com.nexus.nurseryteacher.model.Child;

public class EditChildDataFragmentOne extends Fragment implements View.OnClickListener {

    private EditText childName_editT, childFatherName_editT, childFatherNumber_editT, childMotherName_editT, childMotherNumber_editT, childID_editT;
    private String childName, childFatherName, childFatherNumber, childMotherName, childMotherNumber, childID;
    private Button reset_btn, next_btn;
    private Intent intent;
    private Child child;

    public EditChildDataFragmentOne() {
        // Required empty public constructor
    }

    public static EditChildDataFragmentOne newInstance(Child child) {
        EditChildDataFragmentOne fragment = new EditChildDataFragmentOne();
        fragment.child = child;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_child_data_fragment_one, container, false);

        childName_editT = view.findViewById(R.id.childName_value);
        childFatherName_editT = view.findViewById(R.id.childFatherName_value);
        childFatherNumber_editT = view.findViewById(R.id.childFatherNumber_value);
        childMotherName_editT = view.findViewById(R.id.childMotherName_value);
        childMotherNumber_editT = view.findViewById(R.id.childMotherNumber_value);
        childID_editT = view.findViewById(R.id.childID_value);

        reset_btn = view.findViewById(R.id.reset_btn_fragOne);
        next_btn = view.findViewById(R.id.next_btn);

        reset_btn.setOnClickListener(this);
        next_btn.setOnClickListener(this);

        setData();

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.reset_btn_fragOne){
            childName_editT.setText("");
            childFatherName_editT.setText("");
            childFatherNumber_editT.setText("");
            childMotherName_editT.setText("");
            childMotherNumber_editT.setText("");
            childID_editT.setText("");
        }
        else if(v.getId() == R.id.next_btn){

            //((EditChildDataActivity)getActivity()).selectFragment(2);
            EditChildDataFragmentTwo fragmentTwo = EditChildDataFragmentTwo.newInstance(child);
            //fragmentTwo.setSelectedChild(child);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer_editChildData, fragmentTwo).commit();
        }
    }

    public void setData(){

        childName_editT.setText(child.getChildName());
        childFatherName_editT.setText(child.getChildFatherName());
        childFatherNumber_editT.setText(child.getChildFatherNumber());
        childMotherName_editT.setText(child.getChildMotherName());
        childMotherNumber_editT.setText(child.getChildMotherNumber());
        childID_editT.setText(child.getChildID());
    }

    public String getChildName(){
        return childName_editT.getText().toString();
    }

    public String getChildFatherName(){
        return childFatherName_editT.getText().toString();
    }

    public String getChildMotherName(){
        return childMotherName_editT.getText().toString();
    }

    public String getChildFatherNumber(){
        return childFatherNumber_editT.getText().toString();
    }

    public String getChildMotherNumber(){
        return childMotherNumber_editT.getText().toString();
    }

    public String getChildID(){
        return childID_editT.getText().toString();
    }
/*
    public void setSelectedChild(Child child){
        this.child = child;
    }*/
}
