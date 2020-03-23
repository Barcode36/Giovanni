package com.example.giovanni.giovanni.databinding.dbmeteo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.DataBindingModel;

import java.util.List;

public class MeteoListAdapter extends RecyclerView.Adapter<MeteoListAdapter.MeteoListViewHolder> {

    private List<DataBindingModel> list;

    MeteoListAdapter(List<DataBindingModel> modelList) {
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

    static class MeteoListViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        MeteoListViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Object obj) {
            binding.setVariable(BR.obj, obj);
            binding.executePendingBindings();
        }
    }
}