package com.nexus.nurseryteacher.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.activity.AddChild;
import com.nexus.nurseryteacher.model.Child;

public class AddChildFragmentOne extends Fragment {

    private EditText childName_editT, childID_editT, childBDate_editT, class_editT;
    private EditText fatherName_editT, fatherNumber_editT, fatherLastName_editT;
    private EditText motherName_editT, motherNumber_editT, motherLastName_editT;
    private EditText comment_editT;
    private ImageView child_imgV, fatherID_imgV, mother_imgV;
    private static String className;
    private static ImageView selectedImageView;

    public static AddChildFragmentOne newInstance(String _className) {
        AddChildFragmentOne fragment = new AddChildFragmentOne();
        className = _className;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_child_fragment_one, container, false);

        Button reset_btn, next_btn;

        childName_editT = view.findViewById(R.id.childName_editT);
        childID_editT = view.findViewById(R.id.childID_editT);
        childBDate_editT = view.findViewById(R.id.childBDate_editT);
        class_editT = view.findViewById(R.id.class_editT);
        class_editT.setText(className);
        
        child_imgV = view.findViewById(R.id.childPicture_imgView);
        child_imgV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageView = child_imgV;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), AddChild.SELECT_IMAGE );
            }
        });

        fatherName_editT = view.findViewById(R.id.fatherName_editT);
        fatherLastName_editT = view.findViewById(R.id.fatherLastName_editT);
        fatherNumber_editT = view.findViewById(R.id.fatherNumber_editT);
        fatherID_imgV = view.findViewById(R.id.childFatherPicture_imgView);
        fatherID_imgV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageView = fatherID_imgV;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), AddChild.SELECT_IMAGE );
            }
        });

        motherName_editT = view.findViewById(R.id.motherName_editT);
        motherLastName_editT = view.findViewById(R.id.motherLastName_editT);
        motherNumber_editT = view.findViewById(R.id.motherNumber_editT);
        mother_imgV = view.findViewById(R.id.childMotherPicture_imgView);
        mother_imgV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageView = mother_imgV;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), AddChild.SELECT_IMAGE );
            }
        });

        comment_editT = view.findViewById(R.id.comment_editT);

        reset_btn = view.findViewById(R.id.reset_btn);
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childName_editT.setText("");
                childID_editT.setText("");
                childBDate_editT.setText("");
                fatherName_editT.setText("");
                fatherLastName_editT.setText("");
                fatherNumber_editT.setText("");
                motherName_editT.setText("");
                motherLastName_editT.setText("");
                motherNumber_editT.setText("");
            }
        });

        next_btn = view.findViewById(R.id.save_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Child child = new Child();
                child.setChildName(childName_editT.getText().toString());
                //child.setChildPicture(child_imgV.getDrawable().);
                child.setChildFatherName(fatherName_editT.getText().toString());
                child.setChildFatherNumber(fatherNumber_editT.getText().toString());
                child.setChildMotherName(motherName_editT.getText().toString());
                child.setChildMotherNumber(motherNumber_editT.getText().toString());
                child.setChildID(childID_editT.getText().toString());
                child.setComment(comment_editT.getText().toString());
                child.setChildClass(class_editT.getText().toString());

            }
        });
        return view;
    }

   public static ImageView getSelectedImageView(){
        return selectedImageView;
   }

}
