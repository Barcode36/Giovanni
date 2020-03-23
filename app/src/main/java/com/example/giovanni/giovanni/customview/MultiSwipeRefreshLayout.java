package com.example.giovanni.giovanni.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;

import androidx.core.view.ViewCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MultiSwipeRefreshLayout extends SwipeRefreshLayout {

    private View[] swipeableChildren;

    public MultiSwipeRefreshLayout(Context context) {
        super(context);
    }

    public MultiSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Set the children which can trigger a refresh by swiping down when they are visible. These
     * views need to be a descendant of this view.
     */
    public void setSwipeableChildren(final int... ids) {
        assert ids != null;

        // Iterate through the ids and find the Views
        swipeableChildren = new View[ids.length];
        for (int i = 0; i < ids.length; i++) {
            swipeableChildren[i] = findViewById(ids[i]);
        }
    }

    // BEGIN_INCLUDE(can_child_scroll_up)
    @Override
    public boolean canChildScrollUp() {
        if (swipeableChildren != null && swipeableChildren.length > 0) {
            // Iterate through the scrollable children and check if any of them can not scroll up
            for (View view : swipeableChildren) {
                if (view != null && view.isShown() && !canViewScrollUp(view)) {
                    // If the view is shown, and can not scroll upwards, return false and start the gesture.
                    return false;
                }
            }
        }
        return true;
    }
    // END_INCLUDE(can_child_scroll_up)
    // BEGIN_INCLUDE(can_view_scroll_up)

    /**
     * Utility method to check whether a {@link View} can scroll up from it's current position.
     * Handles platform version differences, providing backwards compatible functionality where
     * needed.
     */
    @SuppressWarnings("deprecation")
    @SuppressLint("ObsoleteSdkInt")
    private static boolean canViewScrollUp(View view) {
        if (android.os.Build.VERSION.SDK_INT >= 14) {
            // For ICS and above we can call canScrollVertically() to determine this
            return ViewCompat.canScrollVertically(view, -1);
        } else {
            if (view instanceof AbsListView) {
                // Pre-ICS we need to manually check the first visible item and the child view's top value
                final AbsListView listView = (AbsListView) view;
                return listView.getChildCount() > 0 &&
                        (listView.getFirstVisiblePosition() > 0
                                || listView.getChildAt(0).getTop() < listView.getPaddingTop());
            } else {
                // For all other view types we just check the getScrollY() value
                return view.getScrollY() > 0;
            }
        }
    }
    // END_INCLUDE(can_view_scroll_up)
}