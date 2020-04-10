package com.example.giovanni.giovanni.recyclerview.recyclerviewproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Persona;
import com.example.giovanni.giovanni.model.Progetto;
import java.util.List;

public class AdapterCompleti extends RecyclerView.Adapter<AdapterCompleti.ViewHolder> {

    private List<Progetto> list;
    private List<Persona> dipendenti;
    private Context context;
    private String user;

    AdapterCompleti(List<Progetto> list, List<Persona> dipendenti, Context mContext) {
        this.list = list;
        this.dipendenti = dipendenti;
        this.context = mContext;
    }

    @NonNull
    @Override
    public AdapterCompleti.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new AdapterCompleti.ViewHolder(view);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBindViewHolder(@NonNull AdapterCompleti.ViewHolder holder, int position) {
        String userlogged = ResultProjectActivity.getUserLogged(); // Sostituisce il nome e il cognome dell'utente loggato con la stringa "tu".
        Progetto progetto = list.get(position);
        holder.tID.setText(String.valueOf(progetto.getId()));
        holder.tProgetto.setText(progetto.getNome());

        for (Persona dipendente : dipendenti) {
            if (progetto.getUser().equals(dipendente.getUsername())) {
                if (dipendente.getUsername().equals(userlogged))
                    user = "Tu";
                else
                    user = dipendente.getNome();
            }
        }
        holder.tVario.setText(user);
        holder.tVario.setTextColor(context.getResources().getColor(R.color.rosso_2));
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.row_progetto;
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        else return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tID;
        private TextView tProgetto;
        private TextView tVario;

        ViewHolder(View v) {
            super(v);
            tID = v.findViewById(R.id.textID);
            tProgetto = v.findViewById(R.id.textProgetto);
            tVario = v.findViewById(R.id.textVario);
        }
    }
}