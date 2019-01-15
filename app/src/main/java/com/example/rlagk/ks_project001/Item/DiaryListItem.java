package com.example.rlagk.ks_project001.Item;

public class DiaryListItem {
//    private static final String TAG = DiaryListItem.class.getName();

    int mImage;
    String mTitle;
    String mDate;
    String mDescription;

    public int getImage() {
        return mImage;
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

    public DiaryListItem(int image, String title, String date) {
        this.mImage = image;
        this.mTitle = title;
        this.mDate = date;
    }

    public DiaryListItem(int image, String title, String description, String date) {
        this.mImage = image;
        this.mTitle = title;
        this.mDescription = description;
        this.mDate = date;
    }

}
