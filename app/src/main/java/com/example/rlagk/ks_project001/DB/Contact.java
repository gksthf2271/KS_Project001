package com.example.rlagk.ks_project001.DB;

import java.util.Date;

public class Contact {
    String mId;
    String mDate;
    String mTitle;
    String mDescription;

    public Contact(){

    }
    public Contact(String id, String date, String title, String description){
        this.mId = id;
        this.mDate = date;
        this.mTitle = title;
        this.mDescription = description;
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

}

