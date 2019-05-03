package song.of.god.ui.ChapterDetailScreen.ViewPager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class GitaScrollView extends ScrollView {
    public GitaScrollView(Context context) {
        super(context);
    }

    public GitaScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GitaScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GitaScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
//        View view = (View) getChildAt(getChildCount() - 1);
//        int diff = (view.getBottom() - (getHeight() + getScrollY()));
//
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            if (diff == 0) {
//                return false;
//            }
//        }

        return false;
    }
}
