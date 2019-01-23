package com.example.rlagk.ks_project001.Item;

import android.net.Uri;

public class HorImageItem {
    int mImage;
    Uri mUri;

    public int getImage() {
        return mImage;
    }

    public Uri getUri() {
        return mUri;
    }

    public HorImageItem(Uri uri) {
        this.mUri = uri;
    }

    public HorImageItem(int image) {
        this.mImage = image;
    }
}
