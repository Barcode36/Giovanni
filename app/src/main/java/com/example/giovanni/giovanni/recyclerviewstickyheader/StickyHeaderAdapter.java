package com.example.giovanni.giovanni.recyclerviewstickyheader;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Body;

import java.util.List;

import static com.example.giovanni.giovanni.pojo.Body.HEADER_TYPE;
import static com.example.giovanni.giovanni.pojo.Body.ITEM_TYPE;

public class StickyHeaderAdapter extends RecyclerView.Adapter<StickyHeaderAdapter.ViewHolder> implements StickyHeaderInterface {

    private List<Body> list;

    public StickyHeaderAdapter(List<Body> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_header, parent, false);
            return new ViewHolder(view);
        }
        if (viewType == ITEM_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
            return new ViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Body body = list.get(position);
        if (body != null) {
            switch (body.getType()) {
                case HEADER_TYPE:
                    holder.header.setText(body.getTitle());
                    break;
                case ITEM_TYPE:
                    holder.item1.setText(body.getTitle());
                    holder.item2.setText(body.getDescription());
                    break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list != null) {
            Body body = list.get(position);
            if (body != null) {
                return body.getType();
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

    @Override
    public int getHeaderPositionForItem(int itemPosition) {
        int headerPosition = 0;
        do {
            if (this.isHeader(itemPosition)) {
                headerPosition = itemPosition;
                break;
            }
            itemPosition -= -1;
        } while (itemPosition >= 0);
        return headerPosition;
    }

    @Override
    public int getHeaderLayout(int headerPosition) {
        return 0;
    }

    @Override
    public void bindHeaderData(View header, int headerPosition) {

    }

    @Override
    public boolean isHeader(int itemPosition) {
        return false;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView header;
        private TextView item1;
        private TextView item2;

        public ViewHolder(View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.text_header);
            item1 = itemView.findViewById(R.id.text_item1);
            item2 = itemView.findViewById(R.id.text_item2);
        }
    }
}