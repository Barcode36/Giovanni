package com.example.giovanni.giovanni.gradientdrawable;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.giovanni.giovanni.R;

public class GradientDrawableActivity extends AppCompatActivity {

    private View view;
    private RelativeLayout relativeContainer;
    private ImageView bar;
    private int progressbarwidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradientdrawable);

        ViewGroup viewGroup = findViewById(R.id.gradient_drawable_container);
        view = getLayoutInflater().inflate(R.layout.activity_gradientdrawable, viewGroup, false);

//        LayoutInflater inflater = LayoutInflater.from(GradientDrawableActivity.this);
//        View view1 = inflater.inflate(R.layout.activity_gradientdrawable, null);

        viewGroup.addView(view);

        View content = view.findViewById(R.id.content);
        Button buttonMoveBar = findViewById(R.id.button_move_bar);
        relativeContainer = content.findViewById(R.id.relative_container);
        RelativeLayout progressBar = content.findViewById(R.id.progress_bar);
        bar = content.findViewById(R.id.bar);

        int azzurro = Color.parseColor("#007FFF");

        GradientDrawable drawableBar = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[] {
                getResources().getColor(R.color.red),
                getResources().getColor(R.color.yellow),
                azzurro});

        drawableBar.setCornerRadius(50f);
        progressBar.setBackgroundDrawable(drawableBar);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (relativeContainer != null) {
                    progressbarwidth = (relativeContainer.getWidth() * 1000 )/5;
                }
            }
        });
        view.requestLayout();

        buttonMoveBar.setOnClickListener(v -> {
//          bar.setTranslationX(progressbarwidth);
            bar.setTranslationX(200);
        });
    }

//    Se GradientDrawableActivity fosse stato un Fragment, l'inflate l'avrei fatto in questo modo:
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.activity_gradientdrawable, container, false);
//        return view;
//    }
}