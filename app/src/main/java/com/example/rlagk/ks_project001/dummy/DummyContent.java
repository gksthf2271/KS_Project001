package com.example.rlagk.ks_project001.dummy;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import com.example.rlagk.ks_project001.Item.HorImageItem;
import com.example.rlagk.ks_project001.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Just dummy content. Nothing special.
 *
 * Created by Andreas Schrade on 14.12.2015.
 */
public class DummyContent {

    public DummyContent(Context context){
        mContext = context;
        init();
    }

    public static final boolean isDebug = true;

    /**
     * An array of sample items.
     */
    public static final List<HorImageItem> ITEMS = new ArrayList<>();
    private Context mContext;

    /**
     * A map of sample items. Key: sample ID; Value: Item.
     */
    public static final Map<String, HorImageItem> ITEM_MAP = new HashMap<>(5);

    static {
//        addItem(new DummyItem("1", R.drawable.p1, "Quote #1", "Steve Jobs", "Focusing is about saying No."));
//        addItem(new DummyItem("2", R.drawable.p2, "Quote #2", "Napoleon Hill","A quitter never wins and a winner never quits."));
//        addItem(new DummyItem("3", R.drawable.p3, "Quote #3", "Pablo Picaso", "Action is the foundational key to all success."));
//        addItem(new DummyItem("4", R.drawable.p4, "Quote #4", "Napoleon Hill","Our only limitations are those we set up in our own minds."));
//        addItem(new DummyItem("5", R.drawable.p5, "Quote #5", "Steve Jobs","Deciding what not do do is as important as deciding what to do."));
    }

    private void init() {
        ITEMS.clear();
        addItem(new HorImageItem("1", "20190810", "Steve Jobs","Focusing is about saying No.", getUriToDrawble(R.drawable.p1)));
        addItem(new HorImageItem("2", "20190810", "Napoleon Hill","Action is the foundational key to all success.", getUriToDrawble(R.drawable.p2)));
        addItem(new HorImageItem("3", "20190810", "Pablo Picaso","Our only limitations are those we set up in our own minds.", getUriToDrawble(R.drawable.p3)));
        addItem(new HorImageItem("4", "20190810", "Napoleon Hill","Deciding what not do do is as important as deciding what to do.", getUriToDrawble(R.drawable.p4)));
        addItem(new HorImageItem("5", "20190810", "Steve Jobs","Deciding what not do do is as important as deciding what to do.", getUriToDrawble(R.drawable.p5)));
        addItem(new HorImageItem("6", "20190810", "Steve Jobs","Deciding what not do do is as important as deciding what to do.", getUriToDrawble(R.drawable.p1)));
        addItem(new HorImageItem("7", "20190810", "Napoleon Hill","Deciding what not do do is as important as deciding what to do.", getUriToDrawble(R.drawable.p2)));
        addItem(new HorImageItem("8", "20190810", "Steve Jobs","Deciding what not do do is as important as deciding what to do.", getUriToDrawble(R.drawable.p3)));
        addItem(new HorImageItem("9", "20190810", "Napoleon Hill","Deciding what not do do is as important as deciding what to do.", getUriToDrawble(R.drawable.p4)));
        addItem(new HorImageItem("10", "20190810", "Pablo Picaso","Deciding what not do do is as important as deciding what to do.", getUriToDrawble(R.drawable.p5)));
        addItem(new HorImageItem("11", "20190810", "Steve Jobs","Deciding what not do do is as important as deciding what to do.", getUriToDrawble(R.drawable.p1)));
        addItem(new HorImageItem("12", "20190810", "Pablo Picaso","Deciding what not do do is as important as deciding what to do.", getUriToDrawble(R.drawable.p3)));
        addItem(new HorImageItem("13", "20190810", "Steve Jobs","Deciding what not do do is as important as deciding what to do.", getUriToDrawble(R.drawable.p4)));
    }

    private static void addItem(HorImageItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getID(), item);
    }

    public static class DummyItem {
        public final String id;
        public final int photoId;
        public final String title;
        public final String author;
        public final String content;

        public DummyItem(String id, int photoId, String title, String author, String content) {
            this.id = id;
            this.photoId = photoId;
            this.title = title;
            this.author = author;
            this.content = content;
        }
    }

    private Uri getUriToDrawble(int drawable){
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + mContext.getResources().getResourcePackageName(drawable)
                + '/' + mContext.getResources().getResourceTypeName(drawable)
                + '/' + mContext.getResources().getResourceEntryName(drawable));
        return imageUri;
    }
}
