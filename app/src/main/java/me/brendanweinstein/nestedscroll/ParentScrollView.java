package me.brendanweinstein.nestedscroll;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * thumb moving up -> dy is positive
 * thumb moving down -> dy is negative
 */
public class ParentScrollView extends NestedScrollView {
    private static final String TAG = ParentScrollView.class.getSimpleName();

    public ParentScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
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
        if (pos[1] <= 0 && dy > 0) {
            Log.d(TAG, "onNestedPreScroll: child consumes");
            //allow the child to consume the event
        } else if (pos[1] <= 0 && target.getScrollY() > 0) {
            Log.d(TAG, "onNestedPreScroll: child consumes");
            //allow the child to consume the event
        } else {
            Log.d(TAG, "onNestedPreScroll: parent consumes");
            //we consume the event
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        SLog.d(TAG, "onNestedScroll: ");
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onStopNestedScroll(View target) {
        //SLog.d(TAG, "onStopNestedScroll: ");
        super.onStopNestedScroll(target);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        SLog.d(TAG, "onNestedPreFling: ");
        return false;
        //return super.onNestedPreFling(target, velocityX, velocityY);
    }


    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        SLog.d(TAG, "onNestedFling: ");
        return false;
        //return super.onNestedFling(target, velocityX, velocityY, consumed);
    }
}
