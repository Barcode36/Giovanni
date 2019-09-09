package com.example.giovanni.giovanni.databinding.dburl;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.DataBindingModel;

import java.util.List;

public class UrlAdapter extends UrlBaseAdapter {

    private List<DataBindingModel> list;

    public UrlAdapter(List<DataBindingModel> modelList) {
        list = modelList;
    }

    @Override
    public Object getDataAtPosition(int position) {
        return list.get(position);
    }

    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.item_url;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}