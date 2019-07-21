package com.example.rlagk.ks_project001.Item;

import android.net.Uri;

public class DiaryListItem {
//    private static final String TAG = DiaryListItem.class.getName();

    long mId;
    String mTitle;
    String mDate;
    String mDescription;
    Uri mImageUri;

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getDescription(){
        return mDescription;
    }

    public Uri getImageUri() {
        return mImageUri;
    }

    public DiaryListItem(Uri image, String title, String date) {
        this.mImageUri = image;
        this.mTitle = title;
        this.mDate = date;
    }

    public DiaryListItem(long id, Uri image, String title, String description, String date) {
        this.mId = id;
        this.mImageUri = image;
        this.mTitle = title;
        this.mDescription = description;
        this.mDate = date;
    }

}
