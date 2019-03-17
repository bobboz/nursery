package com.nexus.nurseryteacher.model;

/**
 * Created by Yousef on 15/05/2017.
 */

public class Post {

    private int postOwnerImg;
    private int postHeaderImg;
    private String postName;
    private String date;
    private String postOwner;

    public Post( int ownerImg, int headerImg, String eventname, String date, String teachername, String description) {
        this.setPostOwner_image(ownerImg);
        this.setPostHeader_image(headerImg);
        this.setPostName(eventname);
//            this.setDate(""+DateUtils.getRelativeTimeSpanString(date.getTime(), System.currentTimeMillis(),DateUtils.SECOND_IN_MILLIS,DateUtils.FORMAT_ABBREV_RELATIVE));
        this.setDate(date);
        this.setPostOwner(teachername);
        this.setDescription(description);
    }

    public int getPostHeaderImg() {
        return postHeaderImg;
    }

    public void setPostHeader_image(int p_image) {
        this.postHeaderImg = p_image;
    }

    public int getPostOwnerImg() {
        return postOwnerImg;
    }

    public void setPostOwner_image(int c_image) {
        this.postOwnerImg = c_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(String postOwner) {
        this.postOwner = postOwner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    private String description;
}

