package com.nexus.nurseryteacher.model;

public class Child {

    private String childName;
    private String childID;
    private String childClass;
    private String childFatherName;
    private String childMotherName;
    private String childFatherNumber;
    private String childMotherNumber;
    private String childAge;
    private String childBirthDate;
    private String comment;
    private int childPicture;

    public Child(){
        childName ="";
        childFatherName ="";
        childMotherName="";
        childFatherNumber="";
        childMotherNumber="";
        childAge="";
        childBirthDate="";
        comment="";

    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public int getChildPicture() {
        return childPicture;
    }

    public void setChildPicture(int childPicture) {
        this.childPicture = childPicture;
    }

    public String getChildID() {
        return childID;
    }

    public void setChildID(String childID) {
        this.childID = childID;
    }

    public String getChildClass() {
        return childClass;
    }

    public void setChildClass(String childClass) {
        this.childClass = childClass;
    }

    public String getChildFatherName() {
        return childFatherName;
    }

    public void setChildFatherName(String childFatherName) {
        this.childFatherName = childFatherName;
    }

    public String getChildMotherName() {
        return childMotherName;
    }

    public void setChildMotherName(String childMotherName) {
        this.childMotherName = childMotherName;
    }

    public String getChildFatherNumber() {
        return childFatherNumber;
    }

    public void setChildFatherNumber(String childFatherNumber) {
        this.childFatherNumber = childFatherNumber;
    }

    public String getChildMotherNumber() {
        return childMotherNumber;
    }

    public void setChildMotherNumber(String childMotherNumber) {
        this.childMotherNumber = childMotherNumber;
    }

    public String getChildAge() {
        return childAge;
    }

    public void setChildAge(String childAge) {
        this.childAge = childAge;
    }

    public String getChildBirthDate() {
        return childBirthDate;
    }

    public void setChildBirthDate(String childBirthDate) {
        this.childBirthDate = childBirthDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
