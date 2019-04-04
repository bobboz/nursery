package com.nexus.nurseryteacher.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Post {

    private int id;
    private Integer userId;
    private String title;
    @SerializedName("body")
    private String text;

    public Post(int userId, String title, String body){
        this.userId= userId;
        this.title = title;
        this.text = body;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

}
