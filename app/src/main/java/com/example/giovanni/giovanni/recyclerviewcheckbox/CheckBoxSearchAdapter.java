package com.example.giovanni.giovanni.recyclerviewcheckbox;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Persona;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxSearchAdapter extends RecyclerView.Adapter<CheckBoxSearchAdapter.ViewHolder> {

    private List<Persona> list;
    private OnItemViewClicked onItemViewClicked;

    public CheckBoxSearchAdapter(OnItemViewClicked onItemViewClicked) {
        this.list = new ArrayList<>();
        this.onItemViewClicked = onItemViewClicked;
    }

    public void setList(List<Persona> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new CheckBoxSearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Persona persona = list.get(position);
        holder.nome.setText(persona.getNome());
        holder.cognome.setText(persona.getCognome());
        holder.msisdn.setText(persona.getMsisdn());

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(persona.isChecked());
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            persona.setChecked(isChecked);
            onItemViewClicked.onItemClicked(persona, isChecked);
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

        CheckBox checkBox;
        TextView nome;
        TextView cognome;
        TextView msisdn;

        public ViewHolder(View itemView) {
            super(itemView);
            this.checkBox = itemView.findViewById(R.id.checkbox_item);
            this.nome = itemView.findViewById(R.id.text_nome_checkbox);
            this.cognome = itemView.findViewById(R.id.text_cognome_checkbox);
            this.msisdn = itemView.findViewById(R.id.text_msisdn_checkbox);
        }
    }

    public interface OnItemViewClicked {
        boolean onItemClicked(Persona persona, boolean isChecked);
    }
}