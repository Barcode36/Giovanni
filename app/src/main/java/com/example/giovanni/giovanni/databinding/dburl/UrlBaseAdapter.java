package com.example.giovanni.giovanni.databinding.dburl;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;

public abstract class UrlBaseAdapter extends RecyclerView.Adapter<UrlBaseAdapter.UrlBaseViewHolder> {

    @NonNull
    @Override
    public UrlBaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, getLayoutIdForType(viewType), viewGroup, false);

        return new UrlBaseAdapter.UrlBaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UrlBaseViewHolder holder, int position) {

        holder.bind(getDataAtPosition(position));
    }

    public abstract Object getDataAtPosition(int position);

    public abstract int getLayoutIdForType(int viewType);

    public class UrlBaseViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        public UrlBaseViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj) {
            binding.setVariable(BR.obj, obj);
            binding.executePendingBindings();
        }
    }
}