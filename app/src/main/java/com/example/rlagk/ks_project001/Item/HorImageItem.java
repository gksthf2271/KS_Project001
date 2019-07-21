package com.example.rlagk.ks_project001.Item;

import android.net.Uri;

import com.example.rlagk.ks_project001.DB.Contact;
import com.example.rlagk.ks_project001.utils.Utils;

import androidx.annotation.NonNull;

public class HorImageItem {
    private String mID;
    private String mDate;
    private String mTitle;
    private String mDescription;
    private int mImage;
    private Uri mUri;

    public HorImageItem(Uri uri) {
        this.setUri(uri);
    }

    public HorImageItem(int image) {
        this.setImage(image);
    }

    public HorImageItem(String id, String date, String title, String description, Uri uri) {
        this.setID(id);
        this.setDate(date);
        this.setTitle(title);
        this.setDescription(description);
        this.setUri(uri);
    }

    public HorImageItem(@NonNull Contact contact){
        this.setID(String.valueOf(contact.getId()));
        this.setDate(contact.getDate());
        this.setTitle(contact.getTitle());
        this.setDescription(contact.getDescription());
        this.setUri(contact.getImageUriList());
    }

    public String getID() {
        return mID;
    }

    public void setID(String mID) {
        this.mID = mID;
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

    public int getImage() {
        return mImage;
    }

    public void setImage(int mImage) {
        this.mImage = mImage;
    }

    public Uri getUri() {
//        Utils.splitString(mUri.toString());
        return mUri;
    }

    public void setUri(Uri mUri) {
        this.mUri = mUri;
    }
}
