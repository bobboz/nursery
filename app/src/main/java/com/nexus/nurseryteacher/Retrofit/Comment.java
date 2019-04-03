package com.nexus.nurseryteacher.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Comment {

    public int getId() {
        return id;
    }

    public int getPostId() {
        return postId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }

    private int id;
    private int postId;
    private String name;
    private String email;
    @SerializedName("body")
    private String text;
}
