package com.nexus.nurseryteacher.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.activity.AddPostActivity;
import com.nexus.nurseryteacher.activity.EditChildDataActivity;
import com.nexus.nurseryteacher.model.Child;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class EditChildDataFragmentTwo extends Fragment implements View.OnClickListener {

    private ImageView childPicture_imgV;
    private EditText childAge_editT, childBDate_editT, medicalIssueComment_editT, comment_editT;
    private Button back_btn/*, reset_btn*/, save_btn;
    private FloatingActionButton changeChildProfileImg_FB;
    private static final int SELECT_IMAGE =1;
    private Child child;
    private RadioGroup medicalIssues_RadioGroup;
    private RadioButton yes_rb, no_rb;
    private String medicalIssueComment;
    private boolean medicalIssueStatus;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    public EditChildDataFragmentTwo() {
        // Required empty public constructor
    }

    public static EditChildDataFragmentTwo newInstance(Child child) {
        EditChildDataFragmentTwo fragment = new EditChildDataFragmentTwo();
        fragment.child =child;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_child_data_fragment_two, container, false);

        childPicture_imgV = view.findViewById(R.id.childPicture_imageView);
        childAge_editT = view.findViewById(R.id.childAge_value);
        childBDate_editT = view.findViewById(R.id.childBDate_value);
        childBDate_editT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,dateSetListener, year, month, day );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        medicalIssues_RadioGroup = view.findViewById(R.id.medicalIssueOptions_radioGroup);
        yes_rb = medicalIssues_RadioGroup.findViewById(R.id.yes_rb);
        no_rb = medicalIssues_RadioGroup.findViewById(R.id.no_rb);
        medicalIssueComment_editT = view.findViewById(R.id.medicalIssueComment_value);
        comment_editT = view.findViewById(R.id.comment_value);

        back_btn = view.findViewById(R.id.back_btn);
        //reset_btn = view.findViewById(R.id.reset_btn_fragTwo);
        save_btn = view.findViewById(R.id.save_btn);

        back_btn.setOnClickListener(this);
        //reset_btn.setOnClickListener(this);
        save_btn.setOnClickListener(this);

        changeChildProfileImg_FB = view.findViewById(R.id.changeChildProfileImg_floatingBtn);
        changeChildProfileImg_FB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE );
            }
        });

        setData();

        return view;
    }

    public void setData(){

        childPicture_imgV.setImageResource(child.getChildPicture());
        childAge_editT.setText(child.getChildAge());
        childBDate_editT.setText(child.getChildBirthDate());
        if(child.getMedicalStatus())
            yes_rb.setChecked(true);
        else
            no_rb.setChecked(true);
        medicalIssueComment_editT.setText(child.getMedicalComment());
        comment_editT.setText(child.getComment());
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.back_btn){
            //((EditChildDataActivity)getActivity()).selectFragment(1);
            EditChildDataFragmentOne fragmentOne = EditChildDataFragmentOne.newInstance(child);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer_editChildData, fragmentOne).commit();
        }
        /*else if(v.getId() == R.id.reset_btn_fragTwo){
            childAge_editT.setText("");
            childBDate_editT.setText("");
            comment_editT.setText("");
        }*/
        else if(v.getId() == R.id.save_btn){
            EditChildDataActivity mainActivity = (EditChildDataActivity) getActivity();
            mainActivity.saveData("",childAge_editT.getText().toString(), childBDate_editT.getText().toString(), medicalIssueStatus, medicalIssueComment, comment_editT.getText().toString());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case SELECT_IMAGE:
                if(requestCode == SELECT_IMAGE){
                    Uri uri = data.getData();
                    Picasso.get().load(uri).fit().centerCrop().into(childPicture_imgV);
                }
                break;
        }
    }

    private void CheckClickedRB(View view){

        int checkedRadioButtonID = medicalIssues_RadioGroup.getCheckedRadioButtonId();
        if(checkedRadioButtonID == R.id.yes_rb)
            medicalIssueStatus = true;
        else
            medicalIssueStatus = false;

    }

    /*
    public void setSelectedChild(Child child){
        this.child = child;
    }*/
}
