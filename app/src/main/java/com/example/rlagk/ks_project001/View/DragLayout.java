package com.example.rlagk.ks_project001.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import com.example.rlagk.ks_project001.R;

public class DragLayout extends FrameLayout {

    public static final String TAG = "DragLayout";
    private int bottomDragVisibleHeight;
    private int bototmExtraIndicatorHeight;
    private int dragTopDest = 0;
    private static final int DECELERATE_THRESHOLD = 120;
    private static final int DRAG_SWITCH_DISTANCE_THRESHOLD = 100;
    private static final int DRAG_SWITCH_VEL_THRESHOLD = 800;

    private static final float MIN_SCALE_RATIO = 0.5f;
    private static final float MAX_SCALE_RATIO = 1.0f;

    private static final int STATE_CLOSE = 1;
    private static final int STATE_EXPANDED = 2;
    private int downState;

    private final ViewDragHelper mDragHelper;
    private final GestureDetectorCompat moveDetector;
    private int mTouchSlop = 5;
    private int originX, originY;
    private View bottomView, topView;

    private GotoDetailListener gotoDetailListener;

    public DragLayout(Context context) {
        this(context, null);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG,"DragLayout(...)");

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.app, 0, 0);
        bottomDragVisibleHeight = (int) a.getDimension(R.styleable.app_bottomDragVisibleHeight, 0);
        bototmExtraIndicatorHeight = (int) a.getDimension(R.styleable.app_bototmExtraIndicatorHeight, 0);
        a.recycle();

        mDragHelper = ViewDragHelper
                .create(this, 1.0f, new DragHelperCallback());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_TOP);
        moveDetector = new GestureDetectorCompat(context, new MoveDetector());
        moveDetector.setIsLongpressEnabled(false);


        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG,"onFinishInflate(...)");
        bottomView = getChildAt(0);
        topView = getChildAt(1);

        topView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick(...)");
                int state = getCurrentState();
                if (state == STATE_CLOSE) {

                    if (mDragHelper.smoothSlideViewTo(topView, originX, dragTopDest)) {
                        ViewCompat.postInvalidateOnAnimation(DragLayout.this);
                    }
                } else {
                    gotoDetailActivity();
                }
            }
        });
    }

    private void gotoDetailActivity() {
        Log.d(TAG,"gotoDetailActivity(...)");
        if (null != gotoDetailListener) {
            gotoDetailListener.gotoDetail();
        }
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            Log.d(TAG,"onViewPositionChanged(...)");
            if (changedView == topView) {
                processLinkageView();
            }
        }

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            Log.d(TAG,"tryCaptureView(...)");
            if (child == topView) {
                return true;
            }
            return false;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            Log.d(TAG,"clampViewPositionVertical(...)");
            int currentTop = child.getTop();
            if (top > child.getTop()) {
                return currentTop + (top - currentTop) / 2;
            }

            int result;
            if (currentTop > DECELERATE_THRESHOLD * 3) {
                result = currentTop + (top - currentTop) / 2;
            } else if (currentTop > DECELERATE_THRESHOLD * 2) {
                result = currentTop + (top - currentTop) / 4;
            } else if (currentTop > 0) {
                result = currentTop + (top - currentTop) / 8;
            } else if (currentTop > -DECELERATE_THRESHOLD) {
                result = currentTop + (top - currentTop) / 16;
            } else if (currentTop > -DECELERATE_THRESHOLD * 2) {
                result = currentTop + (top - currentTop) / 32;
            } else if (currentTop > -DECELERATE_THRESHOLD * 3) {
                result = currentTop + (top - currentTop) / 48;
            } else {
                result = currentTop + (top - currentTop) / 64;
            }
            return result;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.d(TAG,"clampViewPositionHorizontal(...)");
            return child.getLeft();
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return 600;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return 600;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            Log.d(TAG,"onViewReleased(...)");
            int finalY = originY;
            if (downState == STATE_CLOSE) {
                if (originY - releasedChild.getTop() > DRAG_SWITCH_DISTANCE_THRESHOLD || yvel < -DRAG_SWITCH_VEL_THRESHOLD) {
                    finalY = dragTopDest;
                }
            } else {
                boolean gotoBottom = releasedChild.getTop() - dragTopDest > DRAG_SWITCH_DISTANCE_THRESHOLD || yvel > DRAG_SWITCH_VEL_THRESHOLD;
                if (!gotoBottom) {
                    finalY = dragTopDest;

                    if (dragTopDest - releasedChild.getTop() > mTouchSlop) {
                        gotoDetailActivity();
                        postResetPosition();
                        return;
                    }
                }
            }

            if (mDragHelper.smoothSlideViewTo(releasedChild, originX, finalY)) {
                ViewCompat.postInvalidateOnAnimation(DragLayout.this);
            }
        }
    }

    private void processLinkageView() {
        Log.d(TAG,"processLinkageView(...)");
        if (topView.getTop() > originY) {
            bottomView.setAlpha(0);
        } else {
            float alpha = (originY - topView.getTop()) * 0.01f;
            if (alpha > 1) {
                alpha = 1;
            }
            bottomView.setAlpha(alpha);
            int maxDistance = originY - dragTopDest;
            int currentDistance = topView.getTop() - dragTopDest;
            float scaleRatio = 1;
            float distanceRatio = (float) currentDistance / maxDistance;
            if (currentDistance > 0) {
                scaleRatio = MIN_SCALE_RATIO + (MAX_SCALE_RATIO - MIN_SCALE_RATIO) * (1 - distanceRatio);
            }
            bottomView.setScaleX(scaleRatio);
            bottomView.setScaleY(scaleRatio);
        }
    }


    private void postResetPosition() {
        Log.d(TAG,"postResetPosition(...)");
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                topView.offsetTopAndBottom(dragTopDest - topView.getTop());
            }
        }, 500);
    }

    class MoveDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float dx,
                                float dy) {
            Log.d(TAG,"onScroll(...)");
            return Math.abs(dy) + Math.abs(dx) > mTouchSlop;
        }
    }

    @Override
    public void computeScroll() {
        Log.d(TAG,"computeScroll(...)");
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private int getCurrentState() {
        Log.d(TAG,"getCurrentState(...)");
        int state;
        if (Math.abs(topView.getTop() - dragTopDest) <= mTouchSlop) {
            state = STATE_EXPANDED;
        } else {
            state = STATE_CLOSE;
        }
        return state;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG,"onLayout(...)");
        if (!changed) {
            return;
        }

        super.onLayout(changed, left, top, right, bottom);

        originX = (int) topView.getX();
        originY = (int) topView.getY();
        dragTopDest = bottomView.getBottom() - bottomDragVisibleHeight - topView.getMeasuredHeight();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG,"onInterceptTouchEvent(...)");
        boolean yScroll = moveDetector.onTouchEvent(ev);
        boolean shouldIntercept = false;
        try {
            shouldIntercept = mDragHelper.shouldInterceptTouchEvent(ev);
        } catch (Exception e) {
        }

        int action = ev.getActionMasked();
        if (action == MotionEvent.ACTION_DOWN) {
            downState = getCurrentState();
            mDragHelper.processTouchEvent(ev);
        }

        return shouldIntercept && yScroll;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG,"onMeasure(...)");

        int bottomMarginTop = (bottomDragVisibleHeight + topView.getMeasuredHeight() / 2 - bottomView.getMeasuredHeight() / 2) / 2 - bototmExtraIndicatorHeight;
        FrameLayout.LayoutParams lp1 = (LayoutParams) bottomView.getLayoutParams();
        lp1.setMargins(0, bottomMarginTop, 0, 0);
        bottomView.setLayoutParams(lp1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Log.d(TAG,"onTouchEvent(...)");
        try {
            mDragHelper.processTouchEvent(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public void setGotoDetailListener(GotoDetailListener gotoDetailListener) {
        Log.d(TAG,"setGotoDetailListener(...)");
        this.gotoDetailListener = gotoDetailListener;
    }

    public interface GotoDetailListener {
        public void gotoDetail();
    }
}
