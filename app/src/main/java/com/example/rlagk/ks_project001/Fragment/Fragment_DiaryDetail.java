package com.example.rlagk.ks_project001.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rlagk.ks_project001.DB.Contact;
import com.example.rlagk.ks_project001.DB.DBHelperUtils;
import com.example.rlagk.ks_project001.DB.DatabaseManager;
import com.example.rlagk.ks_project001.Item.DiaryListItem;
import com.example.rlagk.ks_project001.Item.HorImageItem;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.rlagk.ks_project001.dummy.DummyContent.isDebug;

/**
 * Shows the mQuote detail page.
 * <p>
 * Created by Andreas Schrade on 14.12.2015.
 */
public class Fragment_DiaryDetail extends BaseFragment {

    /**
     * The argument represents the dummy item ID of this fragment.
     */
    public static final String ARG_ITEM_ID = "item_bundle";

    /**
     * The dummy content of this fragment.
     */
    private static Object mDiaryListItem;

    private Bundle mBundle;

    @BindView(R.id.img_Background)
    ImageView mBackdropImg;

    @BindView(R.id.txt_Title)
    TextView mTitleView;

    @BindView(R.id.txt_Date)
    TextView mDateView;

    @BindView(R.id.txt_Text)
    TextView mTextView;

    @BindView(R.id.floatBtn)
    FloatingActionButton mFloatingBtn;

    private static volatile Fragment_DiaryDetail sInstance;

    public static Fragment_DiaryDetail getInstance() {
        if (sInstance == null) {
            sInstance = new Fragment_DiaryDetail();
        }
        return sInstance;
    }
    public Fragment_DiaryDetail() {
    }

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
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_diary_detail);
        ButterKnife.bind(this.getActivity());
        initView();
        return rootView;
    }

    private void initView() {
        if (mBundle != null) {
            loadBackdrop();
            String date = mBundle.getString("Date");
            if (date.length() < 8 || date.equals("")) {
                mDateView.setText(date);
            } else {
                date = date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6,date.length());
            }
            String title = mBundle.getString("Title");
            String description = mBundle.getString("Description");
            mTitleView.setText(title.equals("") ? "제목을 추가해주세요!" : title);
            mDateView.setText(date);
            mTextView.setText(description.equals("") ? "내용을 추가해주세요!" : description);
        }
    }

    private void loadBackdrop() {
        String bundleString = mBundle.getString("ImageUri");
        String firstImage = Utils.firstUri(bundleString);
        Glide.with(getContext())
                .load(firstImage)
                .error(R.drawable.ic_close)
                .into(mBackdropImg);
    }

    public static Fragment_DiaryDetail newInstance(DiaryListItem item) {
        mDiaryListItem = item;
        Fragment_DiaryDetail fragment = new Fragment_DiaryDetail();
        Bundle args = new Bundle();
        Bundle bundle = new Bundle();
        bundle.putString("Title", item.getTitle());
        bundle.putString("Date", item.getDate());
        bundle.putString("ImageUri", item.getImageUri().toString());
        bundle.putString("Description", item.getDescription());
        args.putBundle(Fragment_DiaryDetail.ARG_ITEM_ID, bundle);
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment_DiaryDetail newInstance(HorImageItem item) {
        mDiaryListItem = item;
        Fragment_DiaryDetail fragment = new Fragment_DiaryDetail();
        Bundle args = new Bundle();
        Bundle bundle = new Bundle();
        bundle.putString("Title", item.getTitle());
        bundle.putString("Date", item.getDate());
        bundle.putString("ImageUri", item.getUri() == null ? "" : item.getUri().toString());
        bundle.putString("Description", item.getDescription());
        args.putBundle(Fragment_DiaryDetail.ARG_ITEM_ID, bundle);
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.floatBtn)
    public void onClickFloatBtn(View view) {
        DBHelperUtils dbHelperUtils = DatabaseManager.getInstance().getDB();
        Contact contact = null;
        if (!isDebug) {
            if (mDiaryListItem instanceof HorImageItem) {
                contact = dbHelperUtils.getContact(Long.parseLong(((HorImageItem) mDiaryListItem).getID()));
            } else if (mDiaryListItem instanceof DiaryListItem) {
                contact = dbHelperUtils.getContact(((DiaryListItem) mDiaryListItem).getId());
            }

            dbHelperUtils.deleteContact(contact);
        }
        getFragmentManager().popBackStack();
    }
}
