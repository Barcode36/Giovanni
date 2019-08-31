package com.example.giovanni.giovanni.listview.twolistviewadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Persona;
import java.util.List;

public class ContattoAdapter extends ArrayAdapter<Persona> {

    private List<Persona> contatti;
    private LayoutInflater inflater;

    ContattoAdapter(@NonNull Context context, int resource, @NonNull List<Persona> objects) {
        super(context, resource, objects);
        this.contatti = objects;
        this.inflater = LayoutInflater.from(context);
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        if (view == null)
            view = inflater.inflate(R.layout.list_contatto_item, null);

        Persona contatto = getItem(position);

        TextView tID;
        TextView tNome;
        TextView tNumero;

        tID = view.findViewById(R.id.text_id);
        tNome = view.findViewById(R.id.text_nome);
        tNumero = view.findViewById(R.id.text_msisdn);

        if (contatto != null) {
            String id = String.valueOf(contatto.getId());
            String nome = contatto.getNome() + " " + contatto.getCognome();
            String msisdn = contatto.getMsisdn();

            tID.setText(id);
            tNome.setText(nome);
            tNumero.setText(msisdn);
        }

        return view;
    }

    @Nullable
    @Override
    public Persona getItem(int position) {
        for(int i=0; i<contatti.size(); i++) {
            if (i == position)
                return contatti.get(i);
        }
        return null;
    }
}