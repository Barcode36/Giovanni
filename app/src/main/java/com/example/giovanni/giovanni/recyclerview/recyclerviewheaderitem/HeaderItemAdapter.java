package com.example.giovanni.giovanni.recyclerview.recyclerviewheaderitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.bean.Body;

import java.util.List;

public class HeaderItemAdapter extends RecyclerView.Adapter<HeaderItemAdapter.ViewHolder> {

    private List<Body> list;

    HeaderItemAdapter(List<Body> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Body body = list.get(position);
        if (body != null) {
            if (body.getType() == 0) {
                holder.header.setText(body.getTitle());
            } else if (body.getType() == 1) {
                holder.item1.setText(body.getTitle());
                holder.item2.setText(body.getDescription());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        Body body = list.get(position);
        if (body != null) {
            switch (body.getType()) {
                case 0:
                    return R.layout.row_header;
                case 1:
                    return R.layout.row_item;
            }
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView header;
        private TextView item1;
        private TextView item2;

        public ViewHolder(View view) {
            super(view);
            header = view.findViewById(R.id.text_header);
            item1 = view.findViewById(R.id.text_item1);
            item2 = view.findViewById(R.id.text_item2);
        }
    }
}