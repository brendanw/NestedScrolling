package me.brendanweinstein.nestedscroll;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * thumb moving up -> dy is positive
 * thumb moving down -> dy is negative
 *
 * velocityY is negative for going up
 * velocityY is positive for thumb fling down
 */
public class ParentScrollView extends NestedScrollView {
    private static final String TAG = ParentScrollView.class.getSimpleName();

    public ParentScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        AppBarLayout a;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //SLog.d(TAG, "onTouchEvent: ");
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //SLog.d(TAG, "onInterceptTouchEvent: ");
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * Need to return true here to continue hearing updates from the child
     * for the duration of the scroll
     */
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        int [] pos = new int[2];
        target.getLocationOnScreen(pos);
        //Log.d(TAG, "recyclerTop: " + pos[1]);
        //Log.d(TAG, "isRecyclerScrolledTop: " + isRecyclerViewScrolledToTop(target));
        if (pos[1] <= 0 && dy > 0) {
            //Log.d(TAG, "onNestedPreScroll: child consumes");
            //allow the child to consume the event
        } else if (pos[1] <= 0 && !isRecyclerViewScrolledToTop(target)) {
            //Log.d(TAG, "onNestedPreScroll: child consumes");
            //allow the child to consume the event
        } else {
            //Log.d(TAG, "onNestedPreScroll: parent consumes");
            //we consume the event
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    static boolean isRecyclerViewScrolledToTop(View view) {
        RecyclerView recyclerView = (RecyclerView) view;
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        return layoutManager.findFirstCompletelyVisibleItemPosition() == 0;
    }

    /*
     *
 * velocityY is negative for going up
 * velocityY is positive for thumb fling down
     */
    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        SLog.d(TAG, "onNestedPreFling: " + velocityY);
        if (isRecyclerViewScrolledToTop(target)) {
            fling((int)velocityY);
            return true;
        }
        return false;
    }


    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.d(TAG, "onNestedFling: " + velocityY);
        Log.d(TAG, "onNestedFling: consumed= " + consumed);
        if (isRecyclerViewScrolledToTop(target)) {
            Log.d(TAG, "onNestedFling: continuing fling in parent");
            fling((int)velocityY);
            return true;
        }
        return false;
    }
}
