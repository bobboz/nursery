package com.nexus.nurseryteacher.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.adapter.EditScheduleRecyclerViewAdapter;
import com.nexus.nurseryteacher.fragment.TimePickerFragment;
import com.nexus.nurseryteacher.model.SessionModel;

import java.util.ArrayList;


public class EditScheduleActivity extends AppCompatActivity {

    private ArrayList<SessionModel> sessions;
    private FloatingActionButton addSessionFloatingPoint;
    private EditScheduleRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule);

        // ToolBar
        Toolbar toolbar = findViewById(R.id.editSchedule_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        setTitle("Edit Schedule");

        initSessions();
        initRecyclerView();

        addSessionFloatingPoint = findViewById(R.id.addSession_floatingPoint);
        addSessionFloatingPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionModel sessionModel = new SessionModel();
                sessionModel.setSessionHeader("Class ");
                sessions.add(sessionModel);
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void initSessions(){

        sessions = new ArrayList<>();

        SessionModel sessionModel = new SessionModel();
        sessionModel.setSessionHeader("Class ");
        sessions.add(sessionModel);
        sessionModel = new SessionModel();
        sessionModel.setSessionHeader("Class ");
        sessions.add(sessionModel);
        sessionModel = new SessionModel();
        sessionModel.setSessionHeader("Class ");
        sessions.add(sessionModel);

    }

    private void initRecyclerView(){

        RecyclerView recyclerView = findViewById(R.id.sessions_recyclerView);
        adapter = new EditScheduleRecyclerViewAdapter(this, sessions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.clear_and_save_manu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.reset:
                break;

            case R.id.save:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
