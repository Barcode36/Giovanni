package com.example.giovanni.giovanni.mvpaddtextchangedlistener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.bean.Persona;

import java.util.ArrayList;
import java.util.List;

public class PersonaArrayAdapter extends ArrayAdapter<Persona> {

    private final List<Persona> list;

    PersonaArrayAdapter(@NonNull Context context, @NonNull List<Persona> persone) { // int resource
        super(context, 0, persone);
        list = new ArrayList<>(persone);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return personaFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_persona, parent, false);
        }
        TextView nome = convertView.findViewById(R.id.text_nome);

        Persona persona = getItem(position);
        if (persona != null) {
            nome.setText(persona.getNome());
        }

        return convertView;
    }

    private final Filter personaFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Persona> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(list);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Persona persona : list) {
                    if (persona.getNome().toLowerCase().contains(filterPattern)) {
                        suggestions.add(persona);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Persona) resultValue).getNome();
        }
    };
}