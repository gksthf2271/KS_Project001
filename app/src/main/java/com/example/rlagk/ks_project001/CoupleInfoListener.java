package com.example.rlagk.ks_project001;

import android.net.Uri;

import androidx.annotation.Nullable;

public interface CoupleInfoListener {
    void onUpdateCoupleViwe(@Nullable Uri leftUri, @Nullable Uri rightUri, @Nullable String date);
    void onClearCoupleView(boolean isRight, Uri uri);
}
