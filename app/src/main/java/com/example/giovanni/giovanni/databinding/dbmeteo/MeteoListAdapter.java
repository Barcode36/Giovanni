package com.example.giovanni.giovanni.databinding.dbmeteo;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.DataBindingModel;

import java.util.List;

public class MeteoListAdapter extends RecyclerView.Adapter<MeteoListAdapter.MeteoListViewHolder> {

    private List<DataBindingModel> list;

    public MeteoListAdapter(List<DataBindingModel> modelList) {
        list = modelList;
    }

    @NonNull
    @Override
    public MeteoListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_meteo, viewGroup, false);

        return new MeteoListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MeteoListViewHolder holder, int position) {

        final DataBindingModel model = list.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MeteoListViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        public MeteoListViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj) {
            binding.setVariable(BR.obj, obj);
            binding.executePendingBindings();
        }
    }
}