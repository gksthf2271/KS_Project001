package com.example.rlagk.ks_project001.quote;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.rlagk.ks_project001.BaseActivity;
import com.example.rlagk.ks_project001.Fragment.BaseFragment;
import com.example.rlagk.ks_project001.Fragment.Fragment_DiaryList;
import com.example.rlagk.ks_project001.Item.DiaryListItem;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.DiaryListView;

import butterknife.BindView;

/**
 * Lists all available quotes. This Activity supports a single pane (= smartphones) and a two pane mode (= large screens with >= 600dp width).
 *
 * Created by Andreas Schrade on 14.12.2015.
 */
public class ListActivity extends BaseActivity implements DiaryListView.OnSelectListener {
    /**
     * Whether or not the activity is running on a device with a large screen
     */
    public static final String TAG = ListActivity.class.getName();
    private boolean twoPaneMode;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setupToolbar();

        if (isTwoPaneLayoutUsed()) {
            twoPaneMode = true;
            Log.d(TAG,"TWO POANE TASDFES");
            enableActiveItemState();
        }

        if (savedInstanceState == null && twoPaneMode) {
            setupDetailFragment();
        }
    }

//    /**
//     * Called when an item has been selected
//     *
//     * @param id the selected quote ID
//     */
//    @Override
//    public void onItemSelected(String id) {
//        if (twoPaneMode) {
//            // Show the quote detail information by replacing the DetailFragment via transaction.
//            ArticleDetailFragment fragment = ArticleDetailFragment.newInstance(id);
//            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
//        } else {
//            // Start the detail activity in single pane mode.
//            Intent detailIntent = new Intent(this, ArticleDetailActivity.class);
//            detailIntent.putExtra(ArticleDetailFragment.ARG_ITEM_ID, id);
//            startActivity(detailIntent);
//        }
//    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupDetailFragment() {
//        ArticleDetailFragment fragment =  ArticleDetailFragment.newInstance(DummyContent.ITEMS.get(0).id);
//        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        loadFragment(Fragment_DiaryList.newInstance());
    }

    /**
     * Enables the functionality that selected items are automatically highlighted.
     */
    private void enableActiveItemState() {
//        ArticleListFragment fragmentById = (ArticleListFragment) getFragmentManager().findFragmentById(R.id.article_list);
//        fragmentById.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        loadFragment(Fragment_DiaryList.newInstance());
    }

    /**
     * Is the container present? If so, we are using the two-pane layout.
     *
     * @return true if the two pane layout is used.
     */
    private boolean isTwoPaneLayoutUsed() {
        return findViewById(R.id.fragment_container) != null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openDrawer();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getSelfNavDrawerItem() {
        return R.id.nav_quotes;
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }

    private void loadFragment(@NonNull BaseFragment fragment) {
        Log.v(TAG, "loadFragment(...)  " + fragment);
        FragmentManager fragmentManager = getFragmentManager();

        if (fragmentManager == null) {
            Log.w(TAG, "Failed to load a fragment (null FragmentManager)");
            return;
        }

        String className = fragment.getClass().getName();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, className)
                .addToBackStack(className)
                .commit();
    }

    @Override
    public void onItemClick(View v, int position, DiaryListItem item) {
        if (twoPaneMode) {
            // Show the quote detail information by replacing the DetailFragment via transaction.
            ArticleDetailFragment fragment = ArticleDetailFragment.newInstance(item);
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        } else {
            // Start the detail activity in single pane mode.
            Intent detailIntent = new Intent(this, ArticleDetailActivity.class);
            detailIntent.putExtra("Title",item.getTitle());
            detailIntent.putExtra("Date", item.getDate());
            detailIntent.putExtra("ImageResId",item.getImage());
            detailIntent.putExtra("Description", item.getDescription());
            startActivity(detailIntent);
        }
    }
}
