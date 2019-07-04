package com.example.giovanni.giovanni.viewpagertablayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.utils.BaseFragment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FragmentPull extends BaseFragment {

    private TextView dataAttuale;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pull, container, false);

        dataAttuale = view.findViewById(R.id.textDataAttuale);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        multiSwipeRefreshLayout = view.findViewById(R.id.multi_swipe_refresh_layout);
        multiSwipeRefreshLayout.setSwipeableChildren(R.id.linear_layout_child);
        multiSwipeRefreshLayout.setOnRefreshListener(() -> {

            // PER TRASFORMARE LA DATA ATTUALE DAL TIPO DATE AL TIPO STRING
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.ITALY);
            String data = sdf.format(new Date());
            dataAttuale.setText(data);

            // NOTA 1: Locale.ITALY può anche essere omesso.
            // NOTA 2: la a fa apparire AM o PM a seconda dei casi.

            String dataModificata = data.replaceFirst("01", "02");
            Log.i("TAG", "Data: " + data + "\nData modificata: " + dataModificata);

            stopSwipeRefresh();
        });
    }
}