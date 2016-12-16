package me.brendanweinstein.nestedscroll;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.Display;
import android.view.WindowManager;

public class MainActivity extends Activity {

    private NestedScrollView parentScrollView;
    private NestedScrollView innerScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentScrollView = (NestedScrollView) findViewById(R.id.parent_scroll);
        innerScrollView = (NestedScrollView) findViewById(R.id.inner_scroll);
        innerScrollView.getLayoutParams().height = getScreenHeight(this);
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
