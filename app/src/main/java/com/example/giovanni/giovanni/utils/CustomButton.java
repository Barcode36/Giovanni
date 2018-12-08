package com.example.giovanni.giovanni.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;

public class CustomButton extends RelativeLayout {

    private CustomButtonViewHolder holder;

    public CustomButton(Context context) {
        super(context);
        init(context, null, 0);
    }

    // Questo costruttore consente all'editor di layout di creare e modificare un'istanza della View.
    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    protected void init(Context context, @Nullable AttributeSet attrs, int defStyle) {

        // LayoutInflater instantiates a layout XML file into its corresponding View objects.
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // The View class represents the basic building blockfor user interface components.
        View myView = inflater.inflate(R.layout.custom_button, this, true);
        // A View occupies a rectangular area on the screen and is responsible for drawing and event handling. View is the
        // base class for widgets, which are used to create interactive UI components (buttons, text fields, etc.).
        if (holder == null) {
            holder = new CustomButtonViewHolder();
            holder.imageView = myView.findViewById(R.id.image_android);
            holder.textView = myView.findViewById(R.id.text_android);
            holder.background = myView.findViewById(R.id.background);
        }
        // holder.textView.setText("text custom");
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (holder != null && holder.background != null) {
            holder.background.setSelected(selected);
        }
    }

    // onMeasure() is your opportunity to tell Android how big you want your custom view to be dependent the layout constraints
    // provided by the parent. EXACTLY means the "layout_width" or "layout_height" value was set to a specific value.
    // You should probably make your view this size.
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int originalWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int originalHeight = View.MeasureSpec.getSize(heightMeasureSpec);

        int min = Math.min(originalWidth, originalHeight);

        super.onMeasure(View.MeasureSpec.makeMeasureSpec(min, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(min, View.MeasureSpec.EXACTLY));
    }

    public class CustomButtonViewHolder {

        ImageView imageView;
        TextView textView;
        View background;
    }
}
