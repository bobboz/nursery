package com.nexus.nurseryteacher.activity;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.fragment.EditChildDataFragmentOne;
import com.nexus.nurseryteacher.fragment.EditChildDataFragmentTwo;
import com.nexus.nurseryteacher.model.Child;

public class EditChildDataActivity extends AppCompatActivity {

    private FrameLayout fragContainer_frameLayout;
    //private EditChildDataFragmentPagerAdapter adapter;
    private EditChildDataFragmentOne editChildDataFragmentOne;
    private EditChildDataFragmentTwo editChildDataFragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_child_data);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setTitle("Edit Data");
        setTitleColor(Color.WHITE);
        /*adapter = new EditChildDataFragmentPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(editChildDataFragmentOne, "EditChildDataFormOne");
        adapter.addFragment(editChildDataFragmentTwo, "EditChildDataFormTwo");

        fragContainer_frameLayout.setAdapter(adapter);*/

        editChildDataFragmentOne =  EditChildDataFragmentOne.newInstance(ChildProfileActivity.getSelectedChild());
        //editChildDataFragmentOne.setSelectedChild(ChildProfileActivity.getSelectedChild());
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer_editChildData,editChildDataFragmentOne).commit();

    }

    /*
    public void selectFragment(int position){
        //fragContainer_viewPager.setCurrentItem(position, true);
        if(position ==0)
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void saveData(String profPicPath, String childAge, String childBD, String comment){

        String childName="", childFatherName="", childFatherNumber="", childMotherName="", childMotherNumber="", childID ="";
        childName = editChildDataFragmentOne.getChildName();
        childFatherName = editChildDataFragmentOne.getChildFatherName();
        childFatherNumber = editChildDataFragmentOne.getChildFatherNumber();
        childMotherName = editChildDataFragmentOne.getChildMotherName();
        childMotherNumber = editChildDataFragmentOne.getChildMotherNumber();
        childID = editChildDataFragmentOne.getChildID();

        Toast.makeText(this, "Name: " + childName + " , ID: "+childID + "\nAge: "+childAge + " , BD:" + childBD, Toast.LENGTH_LONG).show();
    }
}
