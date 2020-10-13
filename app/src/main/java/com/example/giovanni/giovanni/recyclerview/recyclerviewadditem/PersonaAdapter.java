package com.example.giovanni.giovanni.recyclerview.recyclerviewadditem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.bean.Persona;

import java.util.ArrayList;
import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaViewHolder> {

    private List<Persona> list;

    public PersonaAdapter(List<Persona> list) {
        this.list = list;
        setData(list);
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new PersonaViewHolder(view);
    }

    // In holder è passato l'oggetto viewHolder del metodo onCreateViewHolder().
    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Persona persona = list.get(position);

        if (persona.getNome() != null && persona.getNome().equals("Giovanni")) {
            String fullNameAge = persona.getNome() + " " + persona.getCognome() + ", " + persona.getNumero();
            holder.giovanni.setText(fullNameAge);
        } else {
            String fullName = persona.getNome() + " " + persona.getCognome();
            String age = String.valueOf(persona.getNumero());
            holder.nome.setText(fullName);
            holder.numero.setText(age);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Persona persona = list.get(position);
        if (persona.getNome() != null && persona.getNome().equals("Giovanni")) {
            return R.layout.row_header;
        } else {
            return R.layout.row_persona;
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    private void addItem() {
        Persona persona = new Persona();
        persona.setNome("Giovanni");
        persona.setCognome("Petito");
        persona.setNumero(123);
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        // Il primo parametro del metodo add() indica in che posizione della lista deve essere inserito l'oggetto
        // Persona. In questo caso, trattandosi di list.size(), l'oggetto verrà inserito in ultima posizione.
        this.list.add(list.size(), persona);
    }

    public void setData(List<Persona> list) {
        this.list = new ArrayList<>();
        if (list.size() > 0) {
            this.list.addAll(list);
        }
        addItem();
    }
}