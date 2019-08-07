package com.example.giovanni.giovanni.twolistviewonebuttonadapter;

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

public class AdapterContatto extends ArrayAdapter<Persona> {

    private List<Persona> contatti;
    private LayoutInflater inflater;

    public AdapterContatto(@NonNull Context context, int resource, @NonNull List<Persona> objects) {
        super(context, resource, objects);
        this.contatti = objects;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_contatto_item, null);
        }

        Persona contatto = getItem(position);

        TextView tID;
        TextView tNome;
        TextView tNumero;

        tID = convertView.findViewById(R.id.textID);
        tNome = convertView.findViewById(R.id.textNome);
        tNumero = convertView.findViewById(R.id.textNumero);

        String ID = String.valueOf(contatto.getId());
        String nome = contatto.getNome() + " " + contatto.getCognome();
        String msisdn = contatto.getMsisdn();

        tID.setText(ID);
        tNome.setText(nome);
        tNumero.setText(msisdn);

        return convertView;
    }

    @Nullable
    @Override
    public Persona getItem(int position) {
        for(int i=0; i<contatti.size(); i++) {
            if(i == position) {
                return contatti.get(i);
            }
        }
        return null;
    }
}