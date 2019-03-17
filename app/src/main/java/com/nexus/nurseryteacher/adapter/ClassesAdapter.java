package com.nexus.nurseryteacher.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nexus.nurseryteacher.activity.ChildProfileActivity;
import com.nexus.nurseryteacher.activity.ClassDetailsActivity;
import com.nexus.nurseryteacher.activity.MainActivity;
import com.nexus.nurseryteacher.model.Class;
import com.nexus.nurseryteacher.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

/**
 * Created by Yousef on 02/05/2017.
 */


public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.RecyclerViewHolder> {

    private static ArrayList<Class> classes;
    private Context context;
    private static Class selectedClass;
    private Dialog changeClassInfoDialog;
    private static int selectedClassIndex;
    private ImageView classIcon;

    public ClassesAdapter(Context context, ArrayList<Class> classes) {
        this.classes = classes;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_list_item, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, context);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        holder.numberOfChildren.setText(String.valueOf(classes.get(position).getNumberOfChildren())+ " Children");
        holder.className.setText(classes.get(position).getClassName());
        holder.classPicture.setImageResource(classes.get(position).getClassProfilePicture());
        holder.classDetails_layout.setOnClickListener(new View.OnClickListener() {
            public Context ctx;

            @Override
            public void onClick(View view) {
                Context ctx = view.getContext();
                Intent intent = new Intent(ctx, ClassDetailsActivity.class);
                selectedClassIndex = position;
                ctx.startActivity(intent);
            }

        });


        holder.classDetails_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeClassInfoDialog = new Dialog(context);
                changeClassInfoDialog.setContentView(R.layout.modify_class_info_layout);

                final EditText className = changeClassInfoDialog.findViewById(R.id.className_txtV);
                classIcon = changeClassInfoDialog.findViewById(R.id.class_icon);
                classIcon.setImageResource(classes.get(position).getClassProfilePicture());
                Button change_btn = changeClassInfoDialog.findViewById(R.id.changeClassName_btn);
                change_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.className.setText(className.getText());
                        classes.get(position).setClassName(className.getText().toString());
                        changeClassInfoDialog.cancel();
                    }
                });
                Button cancel_btn = changeClassInfoDialog.findViewById(R.id.cancelModification_btn);
                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeClassInfoDialog.cancel();
                    }
                });

                FloatingActionButton changeClassIcon_FB = changeClassInfoDialog.findViewById(R.id.changeClassIcon_floatingBtn);
                changeClassIcon_FB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        //Intent.createChooser(intent, "Select Picture");
                        context.startActivity(intent);
                    }
                });
                changeClassInfoDialog.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView classPicture;
        private TextView className, numberOfChildren;
        //  Context ctx;
        private LinearLayout classDetails_layout;

        public RecyclerViewHolder(View itemView, Context context) {
            super(itemView);
            // this.ctx=ctx;
            classPicture = itemView.findViewById(R.id.classPicture_imgView);
            className = itemView.findViewById(R.id.className_textView);
            numberOfChildren = itemView.findViewById(R.id.classDescription_textView);
            //   itemView.setOnClickListener(this);
            classDetails_layout = itemView.findViewById(R.id.classDetails_layout);
        }
    }

    public static Class getSelectedClass(){
        return classes.get(selectedClassIndex);
    }

    public static boolean updateClassDetails(Class updatedClass){

        if(updatedClass == null)
            return false;

        classes.remove(selectedClassIndex);
        classes.add(selectedClassIndex, updatedClass);

        return true;
    }

}