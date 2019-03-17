package com.nexus.nurseryteacher.model;

import java.util.ArrayList;

/**
 * Created by Yousef on 02/05/2017.
 */

public class Class {

    private int classProfilePicture;
    private String className;
    private String mainTeacher, coTeacher;
    private ArrayList<Child> classChildren;

    public Class(){
        classProfilePicture =0;
        className ="";
    }

    public int getClassProfilePicture() {
        return classProfilePicture;
    }

    public void setClassProfilePicture(int img) {
        this.classProfilePicture = img;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String f_name) {
        this.className = f_name;
    }

    public ArrayList<Child> getClassChildren(){
        return classChildren;
    }

    public void setClassChildren(ArrayList<Child> children){

        classChildren = children;
    }

    public int getNumberOfChildren(){
        return classChildren.size();
    }

    public String getMainTeacher(){
        return mainTeacher;
    }

    public void setMainTeacher(String mainTeacher){
        this.mainTeacher = mainTeacher;
    }

    public String getCoTeacher(){
        return coTeacher;
    }

    public void setCoTeacher(String coTeacher){
        this.coTeacher = coTeacher;
    }
}
