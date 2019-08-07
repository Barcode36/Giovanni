package com.example.giovanni.giovanni.recyclerviewviewpager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.utils.CustomButton;
import com.example.giovanni.giovanni.model.Persona;

import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.ViewHolder> {

    private List<Persona> list;
    private OnItemViewClicked onItemViewClicked;

    public PersonaAdapter(OnItemViewClicked onItemViewClicked, List<Persona> list) {
        this.onItemViewClicked = onItemViewClicked;
        this.list = list;
    }

    public void setList(List<Persona> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    // In holder è passato l'oggetto viewHolder del metodo onCreateViewHolder().
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Persona persona = list.get(position);
        String name = persona.getNome() + " " + persona.getCognome();
        String msisdn = String.valueOf(persona.getNumero());
        holder.nome.setText(name);
        holder.numero.setText(msisdn);

        // Se scrivo holder.customButton, il listener è associato solo al CustomButton.
        // Se scrivo holder.nome, il listener è associato solo al nome.
        // Se scrivo holder.numero, il listener è associato solo al numero.
        // Se scrivo holder.itemView, il listener è associato all'intero item della RecyclerView.
        holder.customButton.setOnClickListener(v -> {
            // onItemClicked() è il metodo dell'interfaccia OnItemViewClicked
            onItemViewClicked.onItemClicked(persona, position);
        });
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.row_persona;
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

        private CustomButton customButton;
        private TextView nome;
        private TextView numero;

        public ViewHolder(View view) {
            super(view);
            customButton = view.findViewById(R.id.custombutton);
            nome = view.findViewById(R.id.text_nome);
            numero = view.findViewById(R.id.text_numero);
        }
    }
}