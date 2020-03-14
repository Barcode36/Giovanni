package com.example.giovanni.giovanni.customview;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;

public class SpinnerHintAdapter implements SpinnerAdapter, ListAdapter {

    private static final int PIVOT = 1;

    protected Context context;
    protected SpinnerAdapter adapter;
    private String message;
    private int hintLayout;
    private int hintDropdownLayout;
    private LayoutInflater layoutInflater;

    public SpinnerHintAdapter(Context context, SpinnerAdapter spinnerAdapter, int hintLayout, String message) {
        this(context, spinnerAdapter, hintLayout, message, -1);
    }

    private SpinnerHintAdapter(Context context, SpinnerAdapter spinnerAdapter, int hintLayout, String message, int hintDropdownLayout) {
        this.context = context;
        this.adapter = spinnerAdapter;
        this.hintLayout = hintLayout;
        this.message = message;
        this.hintDropdownLayout = hintDropdownLayout;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        // This provides the View for the Selected Item in the Spinner, not
        // the dropdown (unless dropdownView is not set).
        if (position == 0) {
            return getHintView(parent);
        }
        return adapter.getView(position - PIVOT, null, parent); // Could re-use
        // the convertView if possible.
    }

    private View getHintView(ViewGroup parent) {
        View view = layoutInflater.inflate(hintLayout, parent, false);
        TextView text1 = view.findViewById(R.id.text1);
        text1.setText(message);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        // Android BUG! http://code.google.com/p/android/issues/detail?id=17128 -
        // Spinner does not support multiple view types
        if (position == 0) {
            return hintDropdownLayout == -1 ?
                    new View(context) :
                    getHintDropdownView(parent);
        }

        // Could re-use the convertView if possible, use setTag...
        return adapter.getDropDownView(position - PIVOT, null, parent);
    }

    private View getHintDropdownView(ViewGroup parent) {
        return layoutInflater.inflate(hintDropdownLayout, parent, false);
    }

    @Override
    public int getCount() {
        int count = adapter.getCount();
        return count == 0 ? 0 : count + PIVOT;
    }

    @Override
    public Object getItem(int position) {
        return position == 0 ? null : adapter.getItem(position - PIVOT);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position >= PIVOT ? adapter.getItemId(position - PIVOT) : position - PIVOT;
    }

    @Override
    public boolean hasStableIds() {
        return adapter.hasStableIds();
    }

    @Override
    public boolean isEmpty() {
        return adapter.isEmpty();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        adapter.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        adapter.unregisterDataSetObserver(observer);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return position != 0; // Don't allow the 'hint' item to be picked.
    }
}