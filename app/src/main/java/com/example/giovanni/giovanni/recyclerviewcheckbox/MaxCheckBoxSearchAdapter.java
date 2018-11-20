package com.example.giovanni.giovanni.recyclerviewcheckbox;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Persona;

import java.util.ArrayList;
import java.util.List;

public class MaxCheckBoxSearchAdapter extends RecyclerView.Adapter<MaxCheckBoxSearchAdapter.ViewHolder> {

    private List<Persona> list;
    private OnItemViewClicked onItemViewClicked;

    public MaxCheckBoxSearchAdapter(OnItemViewClicked onItemViewClicked) {
        this.list = new ArrayList<>();
        this.onItemViewClicked = onItemViewClicked;
    }

    public void setList(List<Persona> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new MaxCheckBoxSearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Persona persona = list.get(position);
        holder.nome.setText(persona.getNome());
        holder.cognome.setText(persona.getCognome());
        holder.msisdn.setText(persona.getMsisdn());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!onItemViewClicked.onItemClicked(persona, isChecked)) {
                    buttonView.setOnCheckedChangeListener(null);
                    buttonView.setChecked(false);
                    buttonView.setOnCheckedChangeListener(this);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_checkbox;
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

        public CheckBox checkBox;
        public TextView nome;
        public TextView cognome;
        public TextView msisdn;

        public ViewHolder(View itemView) {
            super(itemView);
            this.checkBox = itemView.findViewById(R.id.recyclerview_checkbox);
            this.nome = itemView.findViewById(R.id.textNome);
            this.cognome = itemView.findViewById(R.id.textCognome);
            this.msisdn = itemView.findViewById(R.id.textMsisdn);
        }
    }

    public interface OnItemViewClicked {
        boolean onItemClicked(Persona persona, boolean isChecked);
    }
}