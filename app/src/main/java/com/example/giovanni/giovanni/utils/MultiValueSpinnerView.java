package com.example.giovanni.giovanni.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.giovanni.giovanni.R;

public class MultiValueSpinnerView extends View {

    private float x;
    private float y;
    private float w;
    private float h;

    private float i0;
    private float i1;
    private float i2;
    private float i3;

    private float thickStrokeWidth;
    private float thinStrokeWidth;

    private Paint track;
    private Paint p1;
    private Paint p2;
    private Paint p3;
    private Paint p4;

    public MultiValueSpinnerView(Context context) {

        super(context);

        setDefaults();

        init();
    }

    public MultiValueSpinnerView(Context context, AttributeSet attrs) {

        super(context, attrs);

        setDefaults();

        applyAttributes(attrs);
    }

    public MultiValueSpinnerView(Context context, AttributeSet attrs, int theme) {

        super(context, attrs, theme);

        setDefaults();

        applyAttributes(attrs);
    }

    private void setDefaults() {

        x = 0;
        y = 0;
        w = 0;
        h = 0;

        i0 = 0;
        i1 = 0;
        i2 = 0;
        i3 = 0;

        thickStrokeWidth = getResources().getDisplayMetrics().density * 8;
        thinStrokeWidth = getResources().getDisplayMetrics().density * 1;
    }

    private void init() {

        track = new Paint(Paint.ANTI_ALIAS_FLAG);
        track.setStyle(Paint.Style.STROKE);
        track.setColor(0xfffafafa);

        p1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p4 = new Paint(Paint.ANTI_ALIAS_FLAG);

        p1.setStyle(Paint.Style.STROKE);
        p2.setStyle(Paint.Style.STROKE);
        p3.setStyle(Paint.Style.STROKE);
        p4.setStyle(Paint.Style.STROKE);

        p1.setColor(0xff283c45);
        p2.setColor(0xff21b8c6);
        p3.setColor(0xff9ea4a8);
        p4.setColor(0xffe2e3e4);

        track.setStrokeWidth(thickStrokeWidth);
        p1.setStrokeWidth(thinStrokeWidth);
        p2.setStrokeWidth(thickStrokeWidth);
        p3.setStrokeWidth(thickStrokeWidth);
        p4.setStrokeWidth(thickStrokeWidth);
    }

    private void applyAttributes(AttributeSet attrs) {

        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.MultiValueSpinnerView, 0, 0);

        try {
            thickStrokeWidth = a.getDimension(R.styleable.MultiValueSpinnerView_thickStrokeWidth, thickStrokeWidth);
            thinStrokeWidth = a.getDimension(R.styleable.MultiValueSpinnerView_thinStrokeWidth, thinStrokeWidth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.recycle();
        }

        init();
    }

    private void postInvalidateCompat() {

        if (isAttachedToWindow())
            ViewCompat.postInvalidateOnAnimation(this);

        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        w = h = Math.min(getMeasuredWidth(), getMeasuredHeight());
        x = (w == getMeasuredWidth()) ? 0 : (getMeasuredWidth() - w) * .5F;
        y = (h == getMeasuredHeight()) ? 0 : (getMeasuredHeight() - h) * .5F;
    }

    @Override
    protected void onAttachedToWindow() {

        super.onAttachedToWindow();

        postInvalidateCompat();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        canvas.drawArc(x + thickStrokeWidth * .5F, y + thickStrokeWidth * .5F, x + w - thickStrokeWidth * .5F, y + h - thickStrokeWidth * .5F, -90, 360, false, track);
        canvas.drawArc(x + thickStrokeWidth * .5F, y + thickStrokeWidth * .5F, x + w - thickStrokeWidth * .5F, y + h - thickStrokeWidth * .5F, -90, (360.0F / i0) * i1, false, p4);
        canvas.drawArc(x + thickStrokeWidth * .5F, y + thickStrokeWidth * .5F, x + w - thickStrokeWidth * .5F, y + h - thickStrokeWidth * .5F, -90, (360.0F / i0) * (i2 + i3), false, p3);
        canvas.drawArc(x + thickStrokeWidth * .5F, y + thickStrokeWidth * .5F, x + w - thickStrokeWidth * .5F, y + h - thickStrokeWidth * .5F, -90, (360.0F / i0) * i2, false, p2);
        canvas.drawArc(x + thinStrokeWidth * .5F, y + thinStrokeWidth * .5F, x + w - thinStrokeWidth * .5F, y + h - thinStrokeWidth * .5F, -90, (360.0F / i0) * i1, false, p1);
    }

    /**
     * @param i0: ore Spettanti; è il valore totale
     * @param i1: ore Maturate; disegna la sezione grigio chiaro e la linea nera esterna
     * @param i2: ore Fruite; disegna la sezione blu
     * @param i3: ore Pianificate; disegna la sezione grigio scuro
     */

    public void setValues(float i0, float i1, float i2, float i3) {

        this.i0 = i0;
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;

        postInvalidateCompat();
    }
}