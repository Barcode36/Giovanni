package com.example.giovanni.giovanni.recyclerview.gridlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.bean.Persona;

import java.util.List;

public class GridLayoutAdapter extends RecyclerView.Adapter<GridLayoutAdapter.GridLayoutViewHolder> {

    private List<Persona> list;
    private OnItemViewClicked onItemViewClicked;
    private Context context;

    private int index;

    GridLayoutAdapter(OnItemViewClicked onItemViewClicked, Context context) {
        this.onItemViewClicked = onItemViewClicked;
        this.context = context;
    }

    public void setList(List<Persona> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public GridLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new GridLayoutViewHolder(view);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBindViewHolder(@NonNull GridLayoutViewHolder holder, final int position) {

        Persona persona = list.get(position);
        holder.textViewGridLayout.setText(persona.getNome());

        holder.checkBoxGridLayout.setOnCheckedChangeListener(null);
        holder.checkBoxGridLayout.setChecked(persona.isChecked());
        holder.checkBoxGridLayout.setOnCheckedChangeListener((buttonView, isChecked) -> {
            persona.setChecked(isChecked);
            onItemViewClicked.onItemClicked(persona, isChecked);
        });

        holder.iconGridLayout.setOnClickListener(v -> {
            index = position;
            notifyDataSetChanged();
        });

        if (index == position) {
            holder.textViewGridLayout.setTextColor(context.getResources().getColor(R.color.verde_3));
            holder.iconGridLayout.setColorFilter(context.getResources().getColor(R.color.verde_3));
        }
        else {
            holder.textViewGridLayout.setTextColor(context.getResources().getColor(R.color.azzurro_4));
            holder.iconGridLayout.setColorFilter(context.getResources().getColor(R.color.transparent_1));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_gridlayout;
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        else
            return list.size();
    }

    public static class GridLayoutViewHolder extends RecyclerView.ViewHolder {

        ImageView iconGridLayout;
        CheckBox checkBoxGridLayout;
        TextView textViewGridLayout;

        GridLayoutViewHolder(View view) {
            super(view);
            iconGridLayout = view.findViewById(R.id.icon_gridlayout);
            checkBoxGridLayout = view.findViewById(R.id.checkbox_gridlayout);
            textViewGridLayout = view.findViewById(R.id.textview_gridlayout);
        }
    }

    public interface OnItemViewClicked {
        void onItemClicked(Persona persona, boolean isChecked);
    }
}