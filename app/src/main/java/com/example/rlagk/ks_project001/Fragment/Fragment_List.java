/*
package com.example.rlagk.ks_project001.Fragment;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.example.rlagk.ks_project001.R;
import com.nostra13.universalimageloader.core.ImageLoader;

*/
/**
 * Created by rlagk on 2018-04-15.
 *//*


public class Fragment_List extends Fragment {

    public static final String EXTRA_IMAGE_URL = "detailImageUrl";

    public static final String IMAGE_TRANSITION_NAME = "transitionImage";
    public static final String ADDRESS1_TRANSITION_NAME = "address1";
    public static final String ADDRESS2_TRANSITION_NAME = "address2";
    public static final String ADDRESS3_TRANSITION_NAME = "address3";
    public static final String ADDRESS4_TRANSITION_NAME = "address4";
    public static final String ADDRESS5_TRANSITION_NAME = "address5";
    public static final String RATINGBAR_TRANSITION_NAME = "ratingBar";

    public static final String HEAD1_TRANSITION_NAME = "head1";
    public static final String HEAD2_TRANSITION_NAME = "head2";
    public static final String HEAD3_TRANSITION_NAME = "head3";
    public static final String HEAD4_TRANSITION_NAME = "head4";

    private View address1, address2, address3, address4, address5;
    private ImageView imageView;
    private RatingBar ratingBar;

    private LinearLayout listContainer;
    private static final String[] headStrs = {HEAD1_TRANSITION_NAME, HEAD2_TRANSITION_NAME, HEAD3_TRANSITION_NAME, HEAD4_TRANSITION_NAME};
    private static final int[] imageIds = {R.drawable.head1, R.drawable.head2, R.drawable.head3, R.drawable.head4};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, null);
        imageView = (ImageView) rootView.findViewById(R.id.image);
        address1 = rootView.findViewById(R.id.address1);
        address2 = rootView.findViewById(R.id.address2);
        address3 = rootView.findViewById(R.id.address3);
        address4 = rootView.findViewById(R.id.address4);
        address5 = rootView.findViewById(R.id.address5);
        ratingBar = (RatingBar) rootView.findViewById(R.id.rating);
        listContainer = (LinearLayout) rootView.findViewById(R.id.detail_list_container);

            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        String imageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        ImageLoader.getInstance().displayImage(imageUrl, imageView);

        ViewCompat.setTransitionName(imageView, IMAGE_TRANSITION_NAME);
        ViewCompat.setTransitionName(address1, ADDRESS1_TRANSITION_NAME);
        ViewCompat.setTransitionName(address2, ADDRESS2_TRANSITION_NAME);
        ViewCompat.setTransitionName(address3, ADDRESS3_TRANSITION_NAME);
        ViewCompat.setTransitionName(address4, ADDRESS4_TRANSITION_NAME);
        ViewCompat.setTransitionName(address5, ADDRESS5_TRANSITION_NAME);
        ViewCompat.setTransitionName(ratingBar, RATINGBAR_TRANSITION_NAME);

        dealListView();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void dealListView() {
        for (int i = 0; i < 20; i++) {
            View childView = getView().inflate(R.layout.detail_list_item, null);
            listContainer.addView(childView);
            ImageView headView = (ImageView) childView.findViewById(R.id.head);
            if (i < headStrs.length) {
                headView.setImageResource(imageIds[i % imageIds.length]);
                ViewCompat.setTransitionName(headView, headStrs[i]);
            }
        }
    }
}
*/
