package com.nexus.nurseryteacher.model;

public class UpcomingEventItem {

    private int eventMainPhoto;
    private String eventTitle;
    private String eventDescription;
    private String eventFees;
    private String comment;
    private String eventPlace;
    private String eventDate;

    public UpcomingEventItem(){

    }

    public int getEventImage(){
        return eventMainPhoto;
    }

    public String getEventTitle(){
        return eventTitle;
    }

    public String getEventDescription(){
        return eventDescription;
    }

    public void setEventPhoto(int _eventPhoto){
        eventMainPhoto = _eventPhoto;
    }

    public void setEventTitle(String _eventTitle){
        eventTitle = _eventTitle;
    }

    public void setEventDescription(String _eventDescription){
        eventDescription = _eventDescription;
    }

    public String getEventFees() {
        return eventFees;
    }

    public void setEventFees(String eventFees) {
        this.eventFees = eventFees;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
