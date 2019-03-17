package com.nexus.nurseryteacher.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.model.Post;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.Calendar;

public class AddPostActivity extends AppCompatActivity {

    private EditText postTitle_editText, postDetails_editText, postDate_editText, postOwner_editText;
    private ImageView postImage_imgView;
    private FloatingActionButton changePostImg_FB;
    private Button addPost_btn;
    private static final int SELECT_IMAGE =1;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        Toolbar toolbar =  findViewById(R.id.addPost_toolBar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setTitle("Add Post");

        postTitle_editText = findViewById(R.id.postTitle_editText);
        postDetails_editText = findViewById(R.id.postDetails_editText);
        postOwner_editText = findViewById(R.id.postOwner_editText);
        postDate_editText = findViewById(R.id.postDate_editText);
        postDate_editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddPostActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,dateSetListener, year, month, day );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                postDate_editText.setText(dayOfMonth +"-"+(month+1)+"-"+year);
            }
        };

        addPost_btn = findViewById(R.id.addPost_btn);
        addPost_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post post = new Post(R.drawable.tech, R.drawable.happy, postTitle_editText.getText().toString(), postDate_editText.getText().toString(), postOwner_editText.getText().toString(), postDetails_editText.getText().toString());
                // here we should save post in the DB
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                AddPostActivity.this.startActivity(intent);
            }
        });

        changePostImg_FB = findViewById(R.id.changePostImg_floatingBtn);
        changePostImg_FB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE );
            }
        });

        postImage_imgView = findViewById(R.id.addPostImage_imgView);
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
                    /*String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String imgPath = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap selectedImg = BitmapFactory.decodeFile(imgPath);
                    Drawable drawable = new BitmapDrawable(selectedImg);

                    postImage_imgView.setBackground(drawable);*/
                    Picasso.get().load(uri).fit().centerCrop().into(postImage_imgView);
                }
                break;
        }
    }
}
