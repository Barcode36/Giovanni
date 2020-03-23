package com.example.giovanni.giovanni.databinding.dburl;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

public abstract class UrlBaseAdapter extends RecyclerView.Adapter<UrlBaseAdapter.UrlBaseViewHolder> {

    @NonNull
    @Override
    public UrlBaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, getLayoutIdForType(viewType), viewGroup, false);

        return new UrlBaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UrlBaseViewHolder holder, int position) {

        holder.bind(getDataAtPosition(position));
    }

    public abstract Object getDataAtPosition(int position);

    public abstract int getLayoutIdForType(int viewType);

    static class UrlBaseViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        UrlBaseViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Object obj) {
            binding.setVariable(BR.obj, obj);
            binding.executePendingBindings();
        }
    }
}