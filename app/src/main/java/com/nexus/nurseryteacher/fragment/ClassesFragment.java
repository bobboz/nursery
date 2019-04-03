package com.nexus.nurseryteacher.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.adapter.ClassesAdapter;
import com.nexus.nurseryteacher.model.Child;
import com.nexus.nurseryteacher.model.Class;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Yousef on 29/05/2017.
 */

public class ClassesFragment extends Fragment {

    private RecyclerView classes_recyclerView;
    private ArrayList<Class> classes = new ArrayList<Class>();
    private ArrayList<Child> classChildren;
    private Context context;
    private Dialog changeClassInfoDialog;
    private String selectedClassName;
    private static final int SELECT_IMAGE =1;
    private ImageView classIcon;

    public static ClassesFragment newInstance()
    {
        ClassesFragment classesFragment=new ClassesFragment();

        return classesFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View rootview=inflater.inflate(R.layout.activity_classes,null);
        //context= getActivity();
        context = getContext();
        classes = getClasses();
        getActivity().setTitle("Classes");
        classes_recyclerView = rootview.findViewById(R.id.classes_recyclerView);
        classes_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final ClassesAdapter mAdapter = new ClassesAdapter(rootview.getContext(), classes);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                classes.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(classes_recyclerView);

        classes_recyclerView.setAdapter(mAdapter);
        //registerForContextMenu(classes_recyclerView);

        return rootview;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId() == R.id.classes_recyclerView){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            selectedClassName = classes.get(info.position).getClassName();
            menu.setHeaderTitle(selectedClassName);
            String[] popupMenuItems = getResources().getStringArray(R.array.modify_popup_menu);
            for(int i=0; i < popupMenuItems.length; i++){
                menu.add(Menu.NONE, i , i , popupMenuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.modify_popup_menu);
        String menuItemName = menuItems[menuItemIndex];
        switch (menuItemName){
            case "Edit":
                changeClassInfoDialog = new Dialog(context);
                changeClassInfoDialog.setContentView(R.layout.modify_class_info_layout);

                EditText className = changeClassInfoDialog.findViewById(R.id.className_txtV);
                classIcon = changeClassInfoDialog.findViewById(R.id.class_icon);
                FloatingActionButton changeClassIcon_FB = changeClassInfoDialog.findViewById(R.id.changeClassIcon_floatingBtn);
                changeClassIcon_FB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE );
                    }
                });


                break;
            case "Delete":
                break;
        }

        return true;
    }

    public String toString(){
        return "ClassesFragment";
    }

    private ArrayList<Class> getClasses(){
        Class classObj;

        // Class 1
        classObj = new Class();
        classObj.setClassName("Class A Junior");
        classObj.setMainTeacher("Mrs.Reem");
        classObj.setCoTeacher("Mrs.Basma");
        classObj.setClassProfilePicture(R.drawable.class_one);
        classObj.setClassChildren(initClassChildren(classObj.getClassName()));
        classes.add(classObj);
        // Class 2
        classObj = new Class();
        classObj.setClassName("Class B Junior");
        classObj.setMainTeacher("Mrs.Heba");
        classObj.setCoTeacher("Mrs.Shaimaa");
        classObj.setClassProfilePicture(R.drawable.class_two);
        classObj.setClassChildren(initClassChildren(classObj.getClassName()));
        classes.add(classObj);
        // Class 3
        classObj = new Class();
        classObj.setClassName("Class C Junior");
        classObj.setMainTeacher("Mrs.Samar");
        classObj.setCoTeacher("Mrs.Nour");
        classObj.setClassProfilePicture(R.drawable.class_three);
        classObj.setClassChildren(initClassChildren(classObj.getClassName()));
        classes.add(classObj);
        // Class 4
        classObj = new Class();
        classObj.setClassName("Class A Senior");
        classObj.setMainTeacher("Mrs.Sara");
        classObj.setCoTeacher("Mrs.Eman");
        classObj.setClassProfilePicture(R.drawable.class_six);
        classObj.setClassChildren(initClassChildren(classObj.getClassName()));
        classes.add(classObj);
        // Class 5
        classObj = new Class();
        classObj.setClassName("Class B Senior");
        classObj.setMainTeacher("Mrs.Eman");
        classObj.setCoTeacher("Mrs.Heba");
        classObj.setClassProfilePicture(R.drawable.class_one);
        classObj.setClassChildren(initClassChildren(classObj.getClassName()));
        classes.add(classObj);
        // Class 6
        classObj = new Class();
        classObj.setClassName("Class C Senior");
        classObj.setMainTeacher("Mrs.Ghada");
        classObj.setCoTeacher("Mrs.Basma");
        classObj.setClassProfilePicture(R.drawable.class_seven);
        classObj.setClassChildren(initClassChildren(classObj.getClassName()));
        classes.add(classObj);
        // Class 7
        classObj = new Class();
        classObj.setClassName("Class D Senior");
        classObj.setMainTeacher("Mrs.Soaad");
        classObj.setCoTeacher("Mrs.Reem");
        classObj.setClassProfilePicture(R.drawable.class_eight);
        classObj.setClassChildren(initClassChildren(classObj.getClassName()));
        classes.add(classObj);
        // Class 8
        classObj = new Class();
        classObj.setClassName("Class A Junior");
        classObj.setMainTeacher("Mrs.Eman");
        classObj.setCoTeacher("Mrs.Basma");
        classObj.setClassProfilePicture(R.drawable.class_nine);
        classObj.setClassChildren(initClassChildren(classObj.getClassName()));
        classes.add(classObj);
        // Class 9
        classObj = new Class();
        classObj.setClassName("Class B Junior");
        classObj.setMainTeacher("Mrs.Basma");
        classObj.setCoTeacher("Mrs.Eman");
        classObj.setClassProfilePicture(R.drawable.class_two);
        classObj.setClassChildren(initClassChildren(classObj.getClassName()));
        classes.add(classObj);
        // Class 10
        classObj = new Class();
        classObj.setClassName("Class C Junior");
        classObj.setMainTeacher("Mrs.Hanan");
        classObj.setCoTeacher("Mrs.Basma");
        classObj.setClassProfilePicture(R.drawable.class_three);
        classObj.setClassChildren(initClassChildren(classObj.getClassName()));
        classes.add(classObj);

        return classes;
    }

    private ArrayList<Child> initClassChildren(String className){

        Child child;
        classChildren = new ArrayList<>();
        switch(className){
            case "Class A Junior":
                child = new Child();
                child.setChildName("Adam");
                child.setChildGender("boy");
                child.setChildID("001A");
                child.setChildClass("Class A Junior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                child.setMedicalStatus(false);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Nour");
                child.setChildGender("girl");
                child.setChildID("002A");
                child.setChildClass("Class A Junior");
                child.setChildFatherName("Mohammed");
                child.setChildMotherName("Aya");
                child.setChildFatherNumber("01087654321");
                child.setChildMotherNumber("01087654321");
                child.setChildBirthDate("20-02-2016");
                child.setChildAge("3 Years");
                child.setMedicalStatus(true);
                child.setMedicalComment("AAAAAAAAAAAAAAAaaaaa");
                child.setChildPicture(R.drawable.girl_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Ehab");
                child.setChildGender("boy");
                child.setMedicalStatus(true);
                child.setMedicalComment("CCCCCCCCCCCc");
                child.setChildID("003A");
                child.setChildClass("Class A Junior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Soaad");
                child.setChildFatherNumber("01011122233");
                child.setChildMotherNumber("01011122233");
                child.setChildBirthDate("10-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_2);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Aya");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setChildID("004A");
                child.setChildClass("Class A Junior");
                child.setChildFatherName("Sameh");
                child.setChildMotherName("Eman");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.girl_2);
                child.setMedicalComment("");
                classChildren.add(child);

                child = new Child();
                child.setChildName("Sara");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setChildID("005A");
                child.setChildClass("Class A Junior");
                child.setChildFatherName("Mohammed");
                child.setChildMotherName("Noha");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.girl_3);
                child.setMedicalComment("");
                classChildren.add(child);

                child = new Child();
                child.setChildName("Shaimaa");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setChildID("006A");
                child.setChildClass("Class A Junior");
                child.setChildFatherName("Hytham");
                child.setChildMotherName("Noura");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.girl_4);
                child.setMedicalComment("");
                classChildren.add(child);

                child = new Child();
                child.setChildName("Kareem");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setChildID("007A");
                child.setChildClass("Class A Junior");
                child.setChildFatherName("Hesham");
                child.setChildMotherName("Marwa");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                child.setMedicalComment("");
                classChildren.add(child);

                child = new Child();
                child.setChildName("Yousef");
                child.setChildGender("boy");
                child.setMedicalStatus(true);
                child.setMedicalComment("BBBBBBBBBBBBb");
                child.setChildID("008A");
                child.setChildClass("Class A Junior");
                child.setChildFatherName("Amir");
                child.setChildMotherName("Noha");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_5);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Fares");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setChildID("009A");
                child.setChildClass("Class A Junior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Heba");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_5);
                child.setMedicalComment("");
                classChildren.add(child);

                child = new Child();
                child.setChildName("Mohammed");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("010A");
                child.setChildClass("Class A Junior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Sara");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_6);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Ibrahim");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("011A");
                child.setChildClass("Class A Junior");
                child.setChildFatherName("Islam");
                child.setChildMotherName("Ghada");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_7);
                classChildren.add(child);
                break;
            /* ---------------------------------------------------------------------------------- */
            case "Class B Junior":
                child = new Child();
                child.setChildName("Adam");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("001B");
                child.setChildClass("Class B Junior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Nour");
                child.setChildGender("boy");
                child.setMedicalStatus(true);
                child.setMedicalComment("AAAAAAAAAAAAAAAa");
                child.setChildID("002B");
                child.setChildClass("Class B Junior");
                child.setChildFatherName("Mohammed");
                child.setChildMotherName("Aya");
                child.setChildFatherNumber("01087654321");
                child.setChildMotherNumber("01087654321");
                child.setChildBirthDate("20-02-2016");
                child.setChildAge("3 Years");
                child.setChildPicture(R.drawable.girl_2);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Ehab");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("003B");
                child.setChildClass("Class B Junior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Soaad");
                child.setChildFatherNumber("01011122233");
                child.setChildMotherNumber("01011122233");
                child.setChildBirthDate("10-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_2);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Aseel");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("004B");
                child.setChildClass("Class B Junior");
                child.setChildFatherName("Mohammed");
                child.setChildMotherName("Samia");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.girl_3);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Adam");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("005B");
                child.setChildClass("Class B Junior");
                child.setChildFatherName("Mohammed");
                child.setChildMotherName("Sara");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_3);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Sahar");
                child.setChildGender("girl");
                child.setMedicalStatus(true);
                child.setMedicalComment("BBBBBBBBBBBBBBBBBb");
                child.setChildID("006B");
                child.setChildClass("Class B Junior");
                child.setChildFatherName("Ehab");
                child.setChildMotherName("Marwa");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.girl_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Zeyad");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("007B");
                child.setChildClass("Class B Junior");
                child.setChildFatherName("Ibrahim");
                child.setChildMotherName("Esraa");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Jana");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("008B");
                child.setChildClass("Class B Junior");
                child.setChildFatherName("Amir");
                child.setChildMotherName("Noha");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.girl_5);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Jory");
                child.setChildGender("girl");
                child.setMedicalStatus(true);
                child.setMedicalComment("UUUUUUUU\nKKKKKKKK\nJJJJJJJJ");
                child.setChildID("009B");
                child.setChildClass("Class B Junior");
                child.setChildFatherName("Sherif");
                child.setChildMotherName("Heba");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                classChildren.add(child);
                child.setChildPicture(R.drawable.girl_6);
                break;
            /* ---------------------------------------------------------------------------------- */
            case "Class C Junior":
                child = new Child();
                child.setChildName("Adam");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("001C");
                child.setChildClass("Class C Junior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Nour");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("002C");
                child.setChildClass("Class C Junior");
                child.setChildFatherName("Mohammed");
                child.setChildMotherName("Aya");
                child.setChildFatherNumber("01087654321");
                child.setChildMotherNumber("01087654321");
                child.setChildBirthDate("20-02-2016");
                child.setChildAge("3 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Ehab");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("003C");
                child.setChildClass("Class C Junior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Soaad");
                child.setChildFatherNumber("01011122233");
                child.setChildMotherNumber("01011122233");
                child.setChildBirthDate("10-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Adam");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("004C");
                child.setChildClass("Class C Junior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Adam");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("005C");
                child.setChildClass("Class C Junior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Jody");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("006C");
                child.setChildClass("Class C Junior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Yara");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("007C");
                child.setChildClass("Class C Junior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Yousef");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("008C");
                child.setChildClass("Class C Junior");
                child.setChildFatherName("Amir");
                child.setChildMotherName("Noha");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Fares");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("009C");
                child.setChildClass("Class C Junior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Heba");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Fares");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("010C");
                child.setChildClass("Class C Junior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Heba");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);
                break;
            /* ---------------------------------------------------------------------------------- */
            case "Class A Senior":
                child = new Child();
                child.setChildName("Adam");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("101A");
                child.setChildClass("Class A Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Nour");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("102A");
                child.setChildClass("Class A Senior");
                child.setChildFatherName("Mohammed");
                child.setChildMotherName("Aya");
                child.setChildFatherNumber("01087654321");
                child.setChildMotherNumber("01087654321");
                child.setChildBirthDate("20-02-2016");
                child.setChildAge("3 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Ehab");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("103A");
                child.setChildClass("Class A Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Soaad");
                child.setChildFatherNumber("01011122233");
                child.setChildMotherNumber("01011122233");
                child.setChildBirthDate("10-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Ahmed");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("104A");
                child.setChildClass("Class A Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Omar");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("105A");
                child.setChildClass("Class A Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Mohammed");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("106A");
                child.setChildClass("Class A Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Souzy");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("107A");
                child.setChildClass("Class A Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Yousef");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("108C");
                child.setChildClass("Class A Senior");
                child.setChildFatherName("Amir");
                child.setChildMotherName("Noha");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Fares");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("109A");
                child.setChildClass("Class A Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Heba");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Feras");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("110A");
                child.setChildClass("Class A Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Heba");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Mariam");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("111A");
                child.setChildClass("Class A Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Heba");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Youmna");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("112A");
                child.setChildClass("Class A Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Heba");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_4);
                classChildren.add(child);
                break;
            /* ---------------------------------------------------------------------------------- */
            case "Class B Senior":
                child = new Child();
                child.setChildName("Sara");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("101B");
                child.setChildClass("Class B Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Hend");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("102B");
                child.setChildClass("Class B Senior");
                child.setChildFatherName("Mohammed");
                child.setChildMotherName("Aya");
                child.setChildFatherNumber("01087654321");
                child.setChildMotherNumber("01087654321");
                child.setChildBirthDate("20-02-2016");
                child.setChildAge("3 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Soaad");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("103B");
                child.setChildClass("Class B Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Soaad");
                child.setChildFatherNumber("01011122233");
                child.setChildMotherNumber("01011122233");
                child.setChildBirthDate("10-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Rehab");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("104B");
                child.setChildClass("Class B Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Eslam");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("105B");
                child.setChildClass("Class B Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Abdel Rahman");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("106B");
                child.setChildClass("Class B Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Adel");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("107B");
                child.setChildClass("Class B Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Jody");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("108B");
                child.setChildClass("Class B Senior");
                child.setChildFatherName("Amir");
                child.setChildMotherName("Noha");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Jory");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("109B");
                child.setChildClass("Class B Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Heba");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Lyla");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("110B");
                child.setChildClass("Class B Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Heba");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Yehya");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("111B");
                child.setChildClass("Class B Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Heba");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Fawzeya");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("112B");
                child.setChildClass("Class B Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Heba");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);
                break;
            /* ---------------------------------------------------------------------------------- */
            case "Class C Senior":
                child = new Child();
                child.setChildName("Khadeja");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("101C");
                child.setChildClass("Class C Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Nour");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("102C");
                child.setChildClass("Class C Senior");
                child.setChildFatherName("Mohammed");
                child.setChildMotherName("Aya");
                child.setChildFatherNumber("01087654321");
                child.setChildMotherNumber("01087654321");
                child.setChildBirthDate("20-02-2016");
                child.setChildAge("3 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Hosam");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("103C");
                child.setChildClass("Class C Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Soaad");
                child.setChildFatherNumber("01011122233");
                child.setChildMotherNumber("01011122233");
                child.setChildBirthDate("10-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Hatem");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("104C");
                child.setChildClass("Class C Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Karim");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("105C");
                child.setChildClass("Class C Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Amr");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("106C");
                child.setChildClass("Class C Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Adam");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("107C");
                child.setChildClass("Class C Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Yousef");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("108C");
                child.setChildClass("Class C Senior");
                child.setChildFatherName("Amir");
                child.setChildMotherName("Noha");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);
                break;
            /* ---------------------------------------------------------------------------------- */
            case "Class D Senior":
                child = new Child();
                child.setChildName("Adam");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("101D");
                child.setChildClass("Class D Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Nour");
                child.setChildGender("girl");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("102D");
                child.setChildClass("Class D Senior");
                child.setChildFatherName("Mohammed");
                child.setChildMotherName("Aya");
                child.setChildFatherNumber("01087654321");
                child.setChildMotherNumber("01087654321");
                child.setChildBirthDate("20-02-2016");
                child.setChildAge("3 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Ehab");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("103D");
                child.setChildClass("Class D Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Soaad");
                child.setChildFatherNumber("01011122233");
                child.setChildMotherNumber("01011122233");
                child.setChildBirthDate("10-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Adam");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("104D");
                child.setChildClass("Class D Senior");
                child.setChildFatherName("Ahmed");
                child.setChildMotherName("Basma");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Sief");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("105D");
                child.setChildClass("Class D Senior");
                child.setChildFatherName("Hosam");
                child.setChildMotherName("Mervat");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Yasin");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("106D");
                child.setChildClass("Class D Senior");
                child.setChildFatherName("Hosam");
                child.setChildMotherName("Mervat");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Omar");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("107D");
                child.setChildClass("Class D Senior");
                child.setChildFatherName("Karim");
                child.setChildMotherName("Reem");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Haytham");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("108D");
                child.setChildClass("Class D Senior");
                child.setChildFatherName("Fathy");
                child.setChildMotherName("Fatem");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Mohammed");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("109D");
                child.setChildClass("Class D Senior");
                child.setChildFatherName("Khaled");
                child.setChildMotherName("Noha");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Mohab");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("110D");
                child.setChildClass("Class D Senior");
                child.setChildFatherName("Yasser");
                child.setChildMotherName("Heba");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);

                child = new Child();
                child.setChildName("Yousef");
                child.setChildGender("boy");
                child.setMedicalStatus(false);
                child.setMedicalComment("");
                child.setChildID("111D");
                child.setChildClass("Class D Senior");
                child.setChildFatherName("Amir");
                child.setChildMotherName("Noha");
                child.setChildFatherNumber("01012345678");
                child.setChildMotherNumber("01012345678");
                child.setChildBirthDate("09-01-2015");
                child.setChildAge("4 Years");
                child.setChildPicture(R.drawable.boy_1);
                classChildren.add(child);
                break;
        }

        return classChildren;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case SELECT_IMAGE:
                if(requestCode == SELECT_IMAGE){
                    Uri uri = data.getData();
                    Picasso.get().load(uri).fit().centerCrop().into(classIcon);
                }
                break;
        }
    }

}