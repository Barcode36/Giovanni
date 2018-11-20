package com.example.giovanni.giovanni.recyclercambiaprezzo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Articolo;

import java.util.List;

public class ArticoliAdapter extends RecyclerView.Adapter<ArticoliAdapter.ViewHolder> {

    private List<Articolo> articoli;
    private Context context;

    public ArticoliAdapter(List<Articolo> articoli, Context context) {
        this.articoli = articoli;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view, parent.getContext()); // getContext() ritorna il contesto del padre.
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Articolo articolo = articoli.get(position);
        holder.tNome.setText(articolo.getNome());
        holder.tPrezzo.setText(String.valueOf(articolo.getPrezzo()));
        holder.tData.setText(articolo.getData());

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CambiaPrezzoActivity.class);
            intent.putExtra("INSERT", position);
            ((Activity) v.getContext()).startActivityForResult(intent, 50);
        });
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.dettaglio_articolo;
    }

    @Override
    public int getItemCount() {
        if (articoli == null) {
            return 0;
        } else {
            return articoli.size();
        }
    }

    void cambiaPrezzo(int posizione, double newPrezzo) {
        articoli.get(posizione).setPrezzo(newPrezzo);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView tNome;
        private TextView tPrezzo;
        private TextView tData;
        public Articolo articolo;

        public ViewHolder(View v, final Context context) {
            super(v);
            cardView = itemView.findViewById(R.id.card);
            tNome = v.findViewById(R.id.textNome);
            tPrezzo = v.findViewById(R.id.textPrezzo);
            tData = v.findViewById(R.id.textData);

            cardView.setOnClickListener(view -> {
                String message = articolo.getNome() + ", prezzo: " + articolo.getPrezzo();
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            });
        }
    }
}