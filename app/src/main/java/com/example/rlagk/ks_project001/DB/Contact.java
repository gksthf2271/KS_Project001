package com.example.rlagk.ks_project001.DB;

import android.net.Uri;

import java.util.Date;

public class Contact {
    Long mId;
    String mDate;
    String mTitle;
    String mDescription;
    Uri mImageUriList;

    public Contact(){

    }
    public Contact(Long id, String date, String title, String description, Uri imageUri){
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


    public long getId() {
        return mId;
    }

    public void setId(Long mId) {
        this.mId = mId;
    }

    public Uri getImageUriList() {
        return mImageUriList;
    }

    public void setImageUriList(Uri mImageUriList) {
        this.mImageUriList = mImageUriList;
    }

}

