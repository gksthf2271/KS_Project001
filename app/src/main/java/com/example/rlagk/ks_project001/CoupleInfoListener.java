package com.example.rlagk.ks_project001;

import android.net.Uri;

public interface CoupleInfoListener {
    void onUpdateCoupleViwe(Uri leftUri, Uri rightUri, String date);
    void onClearCoupleView(boolean isRight, Uri uri);
}
