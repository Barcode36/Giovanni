package com.example.giovanni.giovanni.recyclerviewsetonclicklistener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;

public class PersonaViewHolder extends RecyclerView.ViewHolder {

    public TextView nome;
    public TextView numero;

    public PersonaViewHolder(View view, final Context context) {
        super(view);
        nome = view.findViewById(R.id.text_nome);
        numero = view.findViewById(R.id.text_numero);
    }
}