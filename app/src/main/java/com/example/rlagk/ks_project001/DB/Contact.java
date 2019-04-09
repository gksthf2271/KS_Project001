package com.example.rlagk.ks_project001.DB;

import java.util.Date;

public class Contact {
    String mId;
    String mDate;
    String mTitle;
    String mDescription;
    String mImageUriList;

    public Contact(){

    }
    public Contact(String id, String date, String title, String description, String imageUri){
        this.mId = id;
        this.mDate = date;
        this.mTitle = title;
        this.mDescription = description;
        this.mImageUriList = imageUri;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }


    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }


    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getImageUriList() {
        return mImageUriList;
    }

    public void setImageUriList(String mImageUriList) {
        this.mImageUriList = mImageUriList;
    }

}

