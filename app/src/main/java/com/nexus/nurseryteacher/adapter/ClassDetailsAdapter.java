package com.nexus.nurseryteacher.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.activity.ChildProfileActivity;
import com.nexus.nurseryteacher.model.Child;

import java.util.ArrayList;

public class ClassDetailsAdapter extends RecyclerView.Adapter<ClassDetailsAdapter.ViewHolder>{

    private static ArrayList<Child> children;
    private Context context;
    private static int selectedChildIndex;

    public ClassDetailsAdapter(Context context, ArrayList<Child> children){
        this.context = context;
        this.children = children;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_list_item, viewGroup, false);
        ClassDetailsAdapter.ViewHolder holder = new ClassDetailsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int position) {
        viewHolder.childName.setText(children.get(position).getChildName()+ " "+children.get(position).getChildFatherName());
        viewHolder.childPicture.setImageResource(children.get(position).getChildPicture());
        viewHolder.childDetails_layout.setOnClickListener(new View.OnClickListener() {
            //public Context ctx;

            @Override
            public void onClick(View view) {
            //Context ctx = view.getContext();
            Intent intent = new Intent(context, ChildProfileActivity.class);
            selectedChildIndex = position;
            context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView childName;
        private ImageView childPicture;
        private LinearLayout childDetails_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            childName = itemView.findViewById(R.id.childName_textView);
            childPicture = itemView.findViewById(R.id.childPicture_imgView);
            childDetails_layout = itemView.findViewById(R.id.childDetails_layout);
        }
    }

    public static Child getSelectedChild(){
        return children.get(selectedChildIndex);
    }
}
