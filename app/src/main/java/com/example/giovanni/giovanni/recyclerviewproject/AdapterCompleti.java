package com.example.giovanni.giovanni.recyclerviewproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Dipendente;
import com.example.giovanni.giovanni.model.Progetto;

import java.util.List;

public class AdapterCompleti extends RecyclerView.Adapter<AdapterCompleti.ViewHolder> {

    private List<Progetto> list;
    private List<Dipendente> dipendenti;
    private Context context;
    private String user;

    public AdapterCompleti(List<Progetto> list, List<Dipendente> dipendenti, Context mContext) {
        this.list = list;
        this.dipendenti = dipendenti;
        this.context = mContext;
    }

    @Override
    public AdapterCompleti.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new AdapterCompleti.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterCompleti.ViewHolder holder, int position) {
        String userlogged = ResultProjectActivity.getUserLogged(); // Per sostituire il nome e il cognome dell'utente
                                                                // loggato con la stringa "tu".
        Progetto progetto = list.get(position);
        holder.tID.setText(String.valueOf(progetto.getId()));
        holder.tProgetto.setText(progetto.getNome());

        for(Dipendente dipendente : dipendenti) {
            if(progetto.getUser().equals(dipendente.getUsername())) {
                if (dipendente.getUsername().equals(userlogged)) {
                    user = "Tu";
                } else {
                    user = dipendente.getNome();
                }
            }
        }
        holder.tVario.setText(user);
        holder.tVario.setTextColor(context.getResources().getColor(R.color.red));
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.row_progetto;
    }

    @Override
    public int getItemCount() {
        if(list == null) {
            return 0;
        }
        else {
            return list.size();
        }
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