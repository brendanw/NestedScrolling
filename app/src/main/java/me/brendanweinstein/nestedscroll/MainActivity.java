package me.brendanweinstein.nestedscroll;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import me.brendanweinstein.nestedscroll.opensource.ObservableRecyclerView;
import me.brendanweinstein.nestedscroll.opensource.ObservableScrollViewCallbacks;
import me.brendanweinstein.nestedscroll.opensource.ScrollState;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private NestedScrollView parentScrollView;
    private ObservableRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //parentScrollView = (NestedScrollView) findViewById(R.id.parent_scroll);

        recyclerView = (ObservableRecyclerView) findViewById(R.id.inner_scroll);
        recyclerView.getLayoutParams().height = getScreenHeight(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.addScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            @Override
            public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
                Log.d(TAG, "onScrollChanged: ");
            }

            @Override
            public void onDownMotionEvent() {
                Log.d(TAG, "onDownMotionEvent: ");
            }

            @Override
            public void onUpOrCancelMotionEvent(ScrollState scrollState) {
                Log.d(TAG, "onUpOrCancelMotionEvent: ");
            }
        });

        TestAdapter adapter = new TestAdapter();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public static int getScreenHeight(Context context) {
        Display display =
                ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                        .getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }
}
