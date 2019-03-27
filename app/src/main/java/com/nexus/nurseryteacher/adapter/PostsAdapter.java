package com.nexus.nurseryteacher.adapter;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.model.Post;

import java.util.ArrayList;


/**
 * Created by Yousef on 15/05/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.RecyclerViewHolder> {
    private ArrayList<Post> posts;

    public PostsAdapter(ArrayList<Post> _posts) {
        this.posts = _posts;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view);
        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.postDate.setText(posts.get(position).getDate());
        holder.postDetails.setText(posts.get(position).getDescription());
        holder.postOwner.setText(posts.get(position).getPostOwner());
        holder.postTitle.setText(posts.get(position).getPostName());
        holder.post_image.setImageResource(posts.get(position).getPostHeaderImg());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView post_image;
        private TextView postTitle,postOwner,postDetails,postDate;

        public RecyclerViewHolder(View itemView){
            super(itemView);
            post_image= itemView.findViewById(R.id.postImage_imgView);
            postDetails= itemView.findViewById(R.id.postDetails_editText);
            postOwner= itemView.findViewById(R.id.postOwner_editText);
            postTitle= itemView.findViewById(R.id.postTitle_editText);
            postDate= itemView.findViewById(R.id.postDate_editText);
        }

    }
}