package com.example.giovanni.giovanni.recyclerview.recyclerviewadditem;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.giovanni.giovanni.R;

public class PersonaViewHolder extends RecyclerView.ViewHolder {

    public TextView nome;
    public TextView numero;
    public TextView giovanni;

    PersonaViewHolder(View view) {
        super(view);

        nome = view.findViewById(R.id.text_nome);
        numero = view.findViewById(R.id.text_numero);
        giovanni = view.findViewById(R.id.text_header);
    }
}