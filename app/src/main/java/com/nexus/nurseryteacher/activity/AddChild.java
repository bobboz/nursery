package com.nexus.nurseryteacher.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.fragment.AddChildFragmentOne;
import com.nexus.nurseryteacher.model.Child;
import com.squareup.picasso.Picasso;

public class AddChild extends AppCompatActivity {

    public static final int SELECT_IMAGE =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        Intent intent = getIntent();
        String className = intent.getStringExtra("className");

        Toolbar toolbar = findViewById(R.id.addChild_ToolBar);
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

        AddChildFragmentOne addChildFragmentOne =  AddChildFragmentOne.newInstance(className);
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer_addChild,addChildFragmentOne).commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case SELECT_IMAGE:
                if(requestCode == SELECT_IMAGE){
                    Uri uri = data.getData();
                    Picasso.get().load(uri).fit().centerCrop().into(AddChildFragmentOne.getSelectedImageView());
                }
                break;
        }
    }

    public static boolean addChild(Child child){
        //here we will send the child to the webservice to be added to the childList of the current class;

        return true;
    }
}
