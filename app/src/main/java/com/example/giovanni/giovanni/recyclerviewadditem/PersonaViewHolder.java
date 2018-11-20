package com.example.giovanni.giovanni.recyclerviewadditem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;

public class PersonaViewHolder extends RecyclerView.ViewHolder {

    public TextView nome;
    public TextView numero;
    public TextView giovanni;

    public PersonaViewHolder(View view) {
        super(view);

        nome = view.findViewById(R.id.text_nome);
        numero = view.findViewById(R.id.text_numero);
        giovanni = view.findViewById(R.id.text_header);
    }
}