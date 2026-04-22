package com.example.tp3moviles.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tp3moviles.R;
import com.example.tp3moviles.model.Libro;

import java.util.ArrayList;
import java.util.List;

public class SugerenciasAdapter extends RecyclerView.Adapter<SugerenciasAdapter.ViewHolder> {

    private List<Libro> sugerencias;
    private OnSugerenciaClickListener listener;

    public interface OnSugerenciaClickListener {
        void onSugerenciaClick(Libro libro);
    }

    public SugerenciasAdapter(OnSugerenciaClickListener listener) {
        this.sugerencias = new ArrayList<>();
        this.listener = listener;
    }

    public void setSugerencias(List<Libro> nuevasSugerencias) {
        this.sugerencias = nuevasSugerencias;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sugerencia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Libro libro = sugerencias.get(position);
        holder.tvTitulo.setText(libro.getTitulo());
        holder.itemView.setOnClickListener(v -> listener.onSugerenciaClick(libro));
    }

    @Override
    public int getItemCount() {
        return sugerencias.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTituloSugerencia);
        }
    }
}
