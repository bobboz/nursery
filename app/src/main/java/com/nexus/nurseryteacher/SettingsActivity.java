package com.nexus.nurseryteacher;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import static android.R.id.message;

public class SettingsActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout = null;

    private TextView nameTextView, mobTextView, classTextView;
    private EditText nameEditText, mobEditText, classEditText;
    private FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Nursery Teacher");

        nameTextView = (TextView) findViewById(R.id.name_textview);
        nameEditText = (EditText) findViewById(R.id.name_editText);

        mobTextView = (TextView) findViewById(R.id.mob_textview);
        mobEditText = (EditText) findViewById(R.id.mob_editText);

        classTextView = (TextView) findViewById(R.id.class_textview);
        classEditText  = (EditText) findViewById(R.id.class_editText);

        button = (FloatingActionButton) findViewById(R.id.fab);

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.isClickable()) {
                    nameEditText.setVisibility(View.VISIBLE);
                    nameTextView.setVisibility(View.INVISIBLE);

                } else {
                    nameEditText.setVisibility(View.INVISIBLE);
                    nameTextView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
