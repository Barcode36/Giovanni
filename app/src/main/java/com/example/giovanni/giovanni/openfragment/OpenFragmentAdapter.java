package com.example.giovanni.giovanni.openfragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Persona;

import java.util.ArrayList;
import java.util.List;

public class OpenFragmentAdapter extends RecyclerView.Adapter<OpenFragmentAdapter.ViewHolder> {

    private List<Persona> list;
    private OnItemViewClicked onItemViewClicked;

    public OpenFragmentAdapter(OnItemViewClicked onItemViewClicked) {
        this.list = new ArrayList<>();
        this.onItemViewClicked = onItemViewClicked;
    }

    public void setList(List<Persona> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new OpenFragmentAdapter.ViewHolder(view);
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
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nome;
        public TextView cognome;

        public ViewHolder(View itemView) {
            super(itemView);
            this.nome = itemView.findViewById(R.id.textNome);
            this.cognome = itemView.findViewById(R.id.textCognome);
        }
    }

    public interface OnItemViewClicked {
        void onItemClicked(Persona persona);
    }
}