package com.example.giovanni.giovanni.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;
import com.example.giovanni.giovanni.R;

public class BaseFragment extends Fragment {

    private FrameLayout frameLayout;
    private FragmentManager fm;
    protected Context mContext;
    protected MultiSwipeRefreshLayout multiSwipeRefreshLayout;

    @Override
    public void onAttach(Context context) {
        this.mContext = context;
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fm = getActivity().getSupportFragmentManager();
        frameLayout = (FrameLayout) getActivity().findViewById(R.id.frame_main_container);

        if (multiSwipeRefreshLayout != null) {
            multiSwipeRefreshLayout.setColorSchemeResources(android.R.color.black);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stopSwipeRefresh();
    }

    public void stopSwipeRefresh() {
        if (multiSwipeRefreshLayout != null) {
            multiSwipeRefreshLayout.setRefreshing(false);
            multiSwipeRefreshLayout.destroyDrawingCache();
            multiSwipeRefreshLayout.clearAnimation();
        }
    }

    protected void startSwipeRefresh() {
        if (multiSwipeRefreshLayout != null && !multiSwipeRefreshLayout.isRefreshing())
            multiSwipeRefreshLayout.setRefreshing(true);
    }
}