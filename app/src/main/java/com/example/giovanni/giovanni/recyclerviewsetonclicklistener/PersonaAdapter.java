package com.example.giovanni.giovanni.recyclerviewsetonclicklistener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Persona;

import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaViewHolder> {

    private List<Persona> list;
    private Context context;

    public PersonaAdapter(List<Persona> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public PersonaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new PersonaViewHolder(view, context);
    }

    // In holder è passato l'oggetto viewHolder del metodo onCreateViewHolder().
    @Override
    public void onBindViewHolder(final PersonaViewHolder holder, int position) {
        final Persona persona = list.get(position);
        holder.nome.setText(persona.getNome() + " " + persona.getCognome());
        holder.numero.setText("" + persona.getNumero());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, persona.getNome() + " " + persona.getCognome(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "" + holder.nome.getText(), Toast.LENGTH_SHORT).show();
            }
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
}