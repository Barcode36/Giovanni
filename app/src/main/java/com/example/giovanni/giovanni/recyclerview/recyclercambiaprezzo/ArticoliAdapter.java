package com.example.giovanni.giovanni.recyclerview.recyclercambiaprezzo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Articolo;
import java.util.List;

public class ArticoliAdapter extends RecyclerView.Adapter<ArticoliViewHolder> {

    private List<Articolo> articoli;
    private Context context;

    ArticoliAdapter(List<Articolo> articoli, Context context) {
        this.articoli = articoli;
        this.context = context;
    }

    @NonNull
    @Override
    public ArticoliViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ArticoliViewHolder(view, parent.getContext()); // getContext() ritorna il contesto del padre.
        // Oppure: return new ArticoliViewHolder(view, context);
    }

    // In holder è passato l'oggetto ArticoliViewHolder del metodo onCreateViewHolder().
    @Override
    public void onBindViewHolder(@NonNull ArticoliViewHolder holder, final int position) {

        Articolo articolo = articoli.get(position);
        holder.tNome.setText(articolo.getNome());
        holder.tPrezzo.setText(String.valueOf(articolo.getPrezzo()));
        holder.tData.setText(articolo.getData());

        holder.cardView.setOnClickListener(v -> {

            Intent intent = new Intent(context, CambiaPrezzoActivity.class);
            intent.putExtra("INSERT", position);
            ((Activity) v.getContext()).startActivityForResult(intent, 50);

            Toast.makeText(context, articolo.getNome(), Toast.LENGTH_SHORT).show();
            // Toast.makeText(context, holder.tNome.getText(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.row_articolo;
    }

    @Override
    public int getItemCount() {
        if (articoli == null)
            return 0;
        else
            return articoli.size();
    }

    void cambiaPrezzo(int posizione, double newPrezzo) {
        articoli.get(posizione).setPrezzo(newPrezzo);
    }
}