package com.example.proassist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proassist.R;
import com.example.proassist.model.Falta;

import java.util.List;

public class FaltasAdapter extends RecyclerView.Adapter<FaltasAdapter.ViewHolder> {
    private List<Falta> faltas;
    private LayoutInflater mInflater;
    private Context ctx;
    public FaltasAdapter(Context ctx, List<Falta> books) {
        this.ctx = ctx;
        this.faltas = books;
        this.mInflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getItemViewType(int position) {
        return 1;
    }
    @NonNull
    @Override
    public FaltasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(
                R.layout.recycler_item_falta, parent, false));
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Falta actual = faltas.get(position);

        holder.nombreProfesor.setText(actual.getNombre());
        holder.apellidoProfesor.setText(actual.getApellido());
        holder.grupo.setText(actual.getGrupo());
        holder.hora.setText(actual.getHora().toString() + "ª hora");
    }
    @Override
    public int getItemCount() {
        return faltas.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreProfesor;
        TextView apellidoProfesor;
        TextView grupo;
        TextView hora;

        ViewHolder(View itemView){
            super(itemView);
            nombreProfesor = itemView.findViewById(R.id.recyclerNombre);
            apellidoProfesor = itemView.findViewById(R.id.recyclerApellido);
            grupo = itemView.findViewById(R.id.recyclerGrupo);
            hora = itemView.findViewById(R.id.recyclerHora);
        }
    }

}