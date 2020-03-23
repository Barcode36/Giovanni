package com.example.giovanni.giovanni.recyclerview.openfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Persona;

import java.util.ArrayList;
import java.util.List;

public class OpenFragmentAdapter extends RecyclerView.Adapter<OpenFragmentAdapter.ViewHolder> {

    private List<Persona> list;
    private OnItemViewClicked onItemViewClicked;

    OpenFragmentAdapter(OnItemViewClicked onItemViewClicked) {
        this.list = new ArrayList<>();
        this.onItemViewClicked = onItemViewClicked;
    }

    public void setList(List<Persona> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Persona persona = list.get(position);
        holder.nome.setText(persona.getNome());
        holder.cognome.setText(persona.getCognome());
        holder.itemView.setOnClickListener(v -> onItemViewClicked.onItemClicked(persona));
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_persona;
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        else
            return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nome;
        public TextView cognome;

        public ViewHolder(View itemView) {
            super(itemView);
            this.nome = itemView.findViewById(R.id.text_nome);
            this.cognome = itemView.findViewById(R.id.text_cognome);
        }
    }

    public interface OnItemViewClicked {
        void onItemClicked(Persona persona);
    }
}