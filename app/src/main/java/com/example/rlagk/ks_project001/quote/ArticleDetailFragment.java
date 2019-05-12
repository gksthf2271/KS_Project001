package com.example.rlagk.ks_project001.quote;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rlagk.ks_project001.BaseActivity;
import com.example.rlagk.ks_project001.Fragment.BaseFragment;
import com.example.rlagk.ks_project001.Item.DiaryListItem;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.dummy.DummyContent;
import com.example.rlagk.ks_project001.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Shows the mQuote detail page.
 *
 * Created by Andreas Schrade on 14.12.2015.
 */
public class ArticleDetailFragment extends BaseFragment {

    /**
     * The argument represents the dummy item ID of this fragment.
     */
    public static final String ARG_ITEM_ID = "item_bundle";

    /**
     * The dummy content of this fragment.
     */
    private DummyContent.DummyItem dummyItem;

    private Bundle mBundle;

    @BindView(R.id.quote)
    TextView mQuote;

    @BindView(R.id.author)
    TextView mAuthor;

    @BindView(R.id.backdrop)
    ImageView mBackdropImg;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    @BindView(R.id.floatBtn)
    FloatingActionButton mFloatingBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // load dummy item by using the passed item ID.
            mBundle = getArguments().getBundle(ARG_ITEM_ID);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_article_detail);
        ButterKnife.bind(this.getActivity());
        if (!((BaseActivity) getActivity()).providesActivityToolbar()) {
            // No Toolbar present. Set include_toolbar:
            ((BaseActivity) getActivity()).setToolbar((Toolbar) rootView.findViewById(R.id.toolbar));
        }

        if (mBundle != null) {
            loadBackdrop();
            mCollapsingToolbar.setTitle(mBundle.getString("Title"));
            mAuthor.setText(mBundle.getString("Date"));
            mQuote.setText(mBundle.getString("Description"));
        }
        return rootView;
    }

    private void loadBackdrop() {
        String bundleString = mBundle.getString("ImageUri");
        String firstImage = Utils.firstUri(bundleString);
        if(firstImage == null){
            return;
        }
        Glide.with(this)
                .load(firstImage)
                .centerCrop()
                .into(mBackdropImg);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.sample_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // your logic
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static ArticleDetailFragment newInstance(DiaryListItem item) {
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        Bundle args = new Bundle();
        Bundle bundle = new Bundle();
        bundle.putString("Title",item.getTitle());
        bundle.putString("Date", item.getDate());
        bundle.putString("ImageUri",item.getImageUri());
        bundle.putString("Description", item.getDescription());
        args.putBundle(ArticleDetailFragment.ARG_ITEM_ID, bundle);
        fragment.setArguments(args);
        return fragment;
    }

    public ArticleDetailFragment() {}

    @OnClick(R.id.floatBtn)
    public void onCLickFloatBtn(View view){

    }
}
