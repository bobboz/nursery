package com.nexus.nurseryteacher.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.adapter.ClassDetailsAdapter;
import com.nexus.nurseryteacher.adapter.ClassesAdapter;
import com.nexus.nurseryteacher.model.Child;
import com.nexus.nurseryteacher.model.Class;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ClassDetailsActivity extends AppCompatActivity {

    private ArrayList<Child> children;
    private ClassDetailsAdapter classDetailsAdapter;
    private String className, mainTeacher, coTeacher;
    private int classPicture;
    private String numberOfChildren;
    private static Class selectedClass;
    private ImageView classIcon;
    private TextView className_txtView, mainTeacher_txtView, coTeacher_txtView;
    private Dialog updateClassNameDialog;
    private static final int SELECT_IMAGE =1;

    private EditText dialog_className, dialog_mainTeacher, dialog_coTeacher;
    private ImageView dialog_classIcon;

    public ClassDetailsActivity() {
        selectedClass = ClassesAdapter.getSelectedClass();
        className = selectedClass.getClassName();
        classPicture = selectedClass.getClassProfilePicture();
        mainTeacher = selectedClass.getMainTeacher();
        coTeacher = selectedClass.getCoTeacher();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);

        Toolbar toolbar = findViewById(R.id.classDetails_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        className_txtView = findViewById(R.id.className_txtV);
        className_txtView.setText(className);

        mainTeacher_txtView = findViewById(R.id.classTeacher_txtV);
        mainTeacher_txtView.setText("Teacher: "+mainTeacher);

        coTeacher_txtView = findViewById(R.id.classCo_Teacher_txtV);
        coTeacher_txtView.setText("Co-Teacher: "+coTeacher);

        classIcon = findViewById(R.id.classIcon_classDetailsView);
        classIcon.setImageResource(classPicture);

        initRecyclerView();
    }

    private void initRecyclerView(){

        RecyclerView recyclerView = findViewById(R.id.classChildren_recyclerView);
        children = ClassesAdapter.getSelectedClass().getClassChildren();
        classDetailsAdapter = new ClassDetailsAdapter(this, children);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                children.remove(viewHolder.getAdapterPosition());
                classDetailsAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(classDetailsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.class_details_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        if(item.getItemId() == R.id.edit_class_menuItem){
            updateClassNameDialog = new Dialog(this);
            updateClassNameDialog.setContentView(R.layout.change_class_data_dialog);

            dialog_className = updateClassNameDialog.findViewById(R.id.className_editT);
            dialog_mainTeacher = updateClassNameDialog.findViewById(R.id.mainTeacher_editT);
            dialog_coTeacher = updateClassNameDialog.findViewById(R.id.coTeacher_editT);

            dialog_classIcon = updateClassNameDialog.findViewById(R.id.class_icon);
            dialog_classIcon.setImageResource(classPicture);
            FloatingActionButton changeClassIcon_FB = updateClassNameDialog.findViewById(R.id.changeClassIcon_floatingBtn);
            changeClassIcon_FB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE );
                }
            });

            Button update_btn = updateClassNameDialog.findViewById(R.id.updateClassData_btn);
            update_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String className = dialog_className.getText().toString();
                    String teacher = dialog_mainTeacher.getText().toString();
                    String coTeacher = dialog_coTeacher.getText().toString();

                    if(className.equals("") || teacher.equals("") || coTeacher.equals("")){
                        Toast.makeText(ClassDetailsActivity.this, "All fields are important", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Class newClass = new Class();
                        newClass.setClassName(className);
                        newClass.setMainTeacher(teacher);
                        newClass.setCoTeacher(coTeacher);
                        if(ClassesAdapter.updateClassDetails(newClass)){
                            className_txtView.setText(dialog_className.getText());
                            mainTeacher_txtView.setText("Teacher: "+dialog_mainTeacher.getText());
                            coTeacher_txtView.setText("Co-Teacher: "+dialog_coTeacher.getText());
                            classIcon.setImageDrawable(dialog_classIcon.getDrawable());

                        }else{
                            Toast.makeText(ClassDetailsActivity.this, "An error occurred while updating data\nplease try again later", Toast.LENGTH_LONG).show();
                        }
                        updateClassNameDialog.cancel();
                    }
                }
            });

            Button cancel_btn = updateClassNameDialog.findViewById(R.id.cancel_btn);
            cancel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateClassNameDialog.cancel();
                }
            });

            updateClassNameDialog.show();
        }
        else if(item.getItemId() == R.id.addChild_menuItem){
            intent = new Intent (this, AddChild.class);
            intent.putExtra("className",className);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.moveChild_menuItem){

        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case SELECT_IMAGE:
                if(requestCode == SELECT_IMAGE){
                    Uri uri = data.getData();
                    Picasso.get().load(uri).fit().centerCrop().into(dialog_classIcon);
                }
                break;
        }
    }

}
