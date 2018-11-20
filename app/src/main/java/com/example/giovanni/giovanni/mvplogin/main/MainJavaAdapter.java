package com.example.giovanni.giovanni.mvplogin.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;

import java.util.List;

public class MainJavaAdapter extends RecyclerView.Adapter<MainJavaAdapter.ViewHolder> {

    public MainJavaAdapter(List<String> items, Listener listener) {
        this.items = items;
        this.listener = listener;
    }

    private List<String> items;
    private Listener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String item = items.get(position);
        holder.textView.setText(item);
        holder.textView.setOnClickListener(v -> listener.onItemClicked(item));
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.textview_item;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        ViewHolder(TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }

    interface Listener {
        void onItemClicked(String item);
    }
}