package com.nexus.nurseryteacher.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.nexus.nurseryteacher.R;

import org.w3c.dom.Text;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


public class SettingsActivity extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private TextView nameTextView, mobTextView, classTextView;
    private EditText nameEditText, mobEditText, classEditText;
    private FloatingActionButton button;
    boolean ViewMode;
    public int SELECT_PHOTO = 1;
    CircleImageView profilephoto;
    ImageView back_photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Nursery Teacher");
        nameTextView = (TextView) findViewById(R.id.name_textview);
        nameEditText = (EditText) findViewById(R.id.name_editText);
        mobTextView = (TextView) findViewById(R.id.mob_textview);
        mobEditText = (EditText) findViewById(R.id.mob_editText);
        classTextView = (TextView) findViewById(R.id.class_textview);
        classEditText = (EditText) findViewById(R.id.class_editText);
        button = (FloatingActionButton) findViewById(R.id.fab);
        back_photo = (ImageView) findViewById(R.id.backgroundphoto);
        profilephoto = (CircleImageView) findViewById(R.id.profile_image);
        ViewMode = true;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ViewMode == true) {
                    nameEditText.setVisibility(View.VISIBLE);
                    nameTextView.setVisibility(View.INVISIBLE);
                    mobEditText.setVisibility(View.VISIBLE);
                    mobTextView.setVisibility(View.INVISIBLE);
                    classEditText.setVisibility(View.VISIBLE);
                    classTextView.setVisibility(View.INVISIBLE);
                    button.setImageResource(R.drawable.ic_class);
                    nameTextView.setText(nameEditText.getText().toString());
                    mobTextView.setText(mobEditText.getText().toString());
                    classTextView.setText(classEditText.getText().toString());
                    Log.i("Save", "Button was clicked ");
                    ViewMode = false;
                    profilephoto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (ViewMode == false) {
                                Intent i = new Intent(
                                        Intent.ACTION_PICK,
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                i.setType("image/*");
                                startActivityForResult(i, SELECT_PHOTO);
                                ViewMode = false;
                            }
                        }
                    });
                } else {
                    nameEditText.setVisibility(View.INVISIBLE);
                    nameTextView.setVisibility(View.VISIBLE);
                    mobEditText.setVisibility(View.INVISIBLE);
                    mobTextView.setVisibility(View.VISIBLE);
                    classEditText.setVisibility(View.INVISIBLE);
                    classTextView.setVisibility(View.VISIBLE);
                    button.setImageResource(R.mipmap.ic_edit);
                    Log.i("Edit", "Button was clicked1 ");
                    ViewMode = true;
                    //if(ViewMode ==true)
                    //ViewMode =true
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                profilephoto.setImageBitmap(bitmap);
                back_photo.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}






