package com.example.giovanni.giovanni.accordion;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Persona;
import java.util.ArrayList;
import java.util.List;

public class AccordionAdapter extends RecyclerView.Adapter<AccordionAdapter.ViewHolder> {

    private static final String SWITCH_TYPE = "switch";
    private static final String ACCORDION_TYPE = "accordion";
    private static final String CHECKBOX_TYPE = "checkbox";

    private List<Persona> list;
    private OnItemViewClicked onItemViewClicked;

    AccordionAdapter(OnItemViewClicked onItemViewClicked) {
        this.list = new ArrayList<>();
        this.onItemViewClicked = onItemViewClicked;
    }

    public void setList(List<Persona> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new AccordionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Persona persona = list.get(position);

        if (persona.getTipo().equals(SWITCH_TYPE)) {
            holder.nomeSwitch.setText(persona.getNome());
            holder.cognomeSwitch.setText(persona.getCognome());

            holder.switchCompat.setOnCheckedChangeListener(null);
            holder.switchCompat.setChecked(persona.isChecked());
            holder.switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
                persona.setChecked(isChecked);
                onItemViewClicked.onItemClicked(persona, isChecked);
            });
        }
        if (persona.getTipo().equals(ACCORDION_TYPE)) {
            holder.nomeAccordion.setText(persona.getNome());
            holder.cognomeAccordion.setText(persona.getCognome());

            holder.itemView.setOnClickListener(v -> {
                list.get(position).setChecked(!list.get(position).isChecked());
                onItemViewClicked.onItemClicked(list.get(position), list.get(position).isChecked());
            });
        }
        if (persona.getTipo().equals(CHECKBOX_TYPE)) {
            holder.nomeCheckBox.setText(persona.getNome());
            holder.cognomeCheckBox.setText(persona.getCognome());
            holder.msisdnCheckBox.setText(persona.getMsisdn());

            holder.checkBox.setOnCheckedChangeListener(null);
            holder.checkBox.setChecked(persona.isChecked());
            holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                persona.setChecked(isChecked);
                onItemViewClicked.onItemClicked(persona, isChecked);
            });
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (list != null && list.get(position) != null &&
                list.get(position).getTipo().equals(ACCORDION_TYPE)) {
            return R.layout.item_accordion;
        }
        if (list != null && list.get(position) != null &&
                list.get(position).getTipo().equals(CHECKBOX_TYPE)) {
            return R.layout.item_checkbox;
        }
        return R.layout.item_switch; // type: switch
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

        TextView nomeSwitch;
        TextView cognomeSwitch;
        SwitchCompat switchCompat;
        TextView nomeAccordion;
        TextView cognomeAccordion;
        CheckBox checkBox;
        TextView nomeCheckBox;
        TextView cognomeCheckBox;
        TextView msisdnCheckBox;

        public ViewHolder(View itemView) {
            super(itemView);
            this.nomeSwitch = itemView.findViewById(R.id.text_nome_switch);
            this.cognomeSwitch = itemView.findViewById(R.id.text_cognome_switch);
            this.switchCompat = itemView.findViewById(R.id.switch_compat);
            this.nomeAccordion = itemView.findViewById(R.id.text_nome_accordion);
            this.cognomeAccordion = itemView.findViewById(R.id.text_cognome_accordion);
            this.checkBox = itemView.findViewById(R.id.checkbox_item);
            this.nomeCheckBox = itemView.findViewById(R.id.text_nome_checkbox);
            this.cognomeCheckBox = itemView.findViewById(R.id.text_cognome_checkbox);
            this.msisdnCheckBox = itemView.findViewById(R.id.text_msisdn_checkbox);
        }
    }

    public interface OnItemViewClicked {
        boolean onItemClicked(Persona persona, boolean isChecked);
    }
}