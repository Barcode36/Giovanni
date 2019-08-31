package com.example.giovanni.giovanni.recyclerview.recyclercambiaprezzo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.giovanni.giovanni.R;

class ArticoliViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    TextView tNome;
    TextView tPrezzo;
    TextView tData;

    ArticoliViewHolder(View v, final Context context) {
        super(v);
        cardView = itemView.findViewById(R.id.card_articolo);
        tNome = v.findViewById(R.id.text_nome);
        tPrezzo = v.findViewById(R.id.text_prezzo);
        tData = v.findViewById(R.id.text_data);
    }
}