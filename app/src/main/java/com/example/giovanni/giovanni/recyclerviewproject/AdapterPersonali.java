package com.example.giovanni.giovanni.recyclerviewproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Progetto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdapterPersonali extends RecyclerView.Adapter<AdapterPersonali.ViewHolder> {

    private List<Progetto> list;

    public AdapterPersonali(List<Progetto> list) {
        this.list = list;
    }

    @Override
    public AdapterPersonali.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new AdapterPersonali.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterPersonali.ViewHolder holder, int position) {
        Progetto progetto = list.get(position);
        holder.tID.setText(String.valueOf(progetto.getId()));
        holder.tProgetto.setText(progetto.getNome());
        holder.tVario.setText(formatDate(progetto.getData()));
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

    private String formatDate(Date data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
        return simpleDateFormat.format(data);
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