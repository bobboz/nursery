package com.nexus.nurseryteacher.model;

import android.net.Uri;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

public class Event {

    private String eventTitle, eventDescription, eventDate;
    private int eventMainPhoto;
    private ArrayList<String>  eventGallery;

    public Event(){

    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getEventMainPhoto() {
        return eventMainPhoto;
    }

    public void setEventMainPhoto(int eventMainPhoto) {
        this.eventMainPhoto = eventMainPhoto;
    }

    public ArrayList<String> getEventGallery() {
        return eventGallery;
    }

    public void setEventGallery(ArrayList<String>  eventGallery) {
        this.eventGallery = eventGallery;
    }

}
