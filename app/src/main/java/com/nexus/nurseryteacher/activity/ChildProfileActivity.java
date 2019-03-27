package com.nexus.nurseryteacher.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.adapter.ClassDetailsAdapter;
import com.nexus.nurseryteacher.fragment.ChildDataFragmentOne;
import com.nexus.nurseryteacher.model.Child;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChildProfileActivity extends AppCompatActivity {

    private DrawerLayout mainDrawer;
    private String childID_str, childName_str, childAge_str, childBDate_str, childFatherName_str, childMotherName_str, childTelephone_str;
    //private String childClassName;
    private TextView childName, childAge, childBDate, childTelephone, childFatherName, childMotherName;
    private Intent intent;
    private CircleImageView childPicture;

    // Fragment Data
    private String comment;
    private float itemOne_ratingBarValue, itemTwo_seekBarValue, itemFour_seekBarValue;
    private int selectedChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_data);

        Toolbar toolbar = findViewById(R.id.childData_toolBar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ChildDataFragmentOne childDataFragment = ChildDataFragmentOne.newInstance(getSelectedChild());
        //childDataFragment.setSelectedChild(getSelectedChild());
        this.getSupportFragmentManager().beginTransaction().replace(R.id.childData_fragment_container, childDataFragment).commit();

    }
/*
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu_item, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        if(item.getItemId() == R.id.action_home){
            intent = new Intent (this, MainActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.action_edit){
            intent = new Intent (this, EditChildDataActivity.class);
            startActivity(intent);
        }

        return true;
    }
*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public static Child getSelectedChild(){
        return ClassDetailsAdapter.getSelectedChild();
    }
}
