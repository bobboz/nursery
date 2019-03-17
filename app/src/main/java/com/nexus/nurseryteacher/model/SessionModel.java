package com.nexus.nurseryteacher.model;

public class SessionModel {

    private String header, sessionTitle, sessionDescription, sessionTime;

    public SessionModel(){
        sessionTitle ="";
        sessionDescription ="";
        sessionTime ="";
        header ="";
    }

    public void setSessionTitle(String _title){
        sessionTitle = _title;
    }

    public void setSessionDescription(String _desc){
        sessionDescription =_desc;
    }

    public void setSessionTime(String _time){
        sessionTime = _time;
    }

    public void setSessionHeader(String _header){
        header = _header;
    }

    public String getSessionTitle(){
        return sessionTitle;
    }

    public String getSessionDescription(){
        return sessionDescription;
    }

    public String getSessionTime(){
        return sessionTime;
    }

    public String getSessionHeader(){
        return header;
    }
}
