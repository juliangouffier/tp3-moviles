package com.example.tp3moviles;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tp3moviles.adapter.SugerenciasAdapter;
import com.example.tp3moviles.databinding.ActivityMainBinding;
import com.example.tp3moviles.model.Libro;
import com.example.tp3moviles.viewmodel.BookViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SugerenciasAdapter.OnSugerenciaClickListener {

    private ActivityMainBinding binding;
    private BookViewModel viewModel;
    private SugerenciasAdapter sugerenciasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(BookViewModel.class);

        sugerenciasAdapter = new SugerenciasAdapter(this);
        binding.recyclerSugerencias.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerSugerencias.setAdapter(sugerenciasAdapter);

        binding.etBusqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                actualizarSugerencias(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        binding.btnBuscar.setOnClickListener(v -> {
            String titulo = binding.etBusqueda.getText().toString();
            viewModel.buscarLibro(titulo);
        });

        viewModel.getLibroEncontrado().observe(this, this::mostrarDetalleLibro);
        viewModel.getMensajeError().observe(this, this::mostrarError);
    }

    private void actualizarSugerencias(String texto) {
        List<Libro> sugerencias = viewModel.filtrarLibros(texto);
        if (sugerencias.isEmpty()) {
            binding.recyclerSugerencias.setVisibility(View.GONE);
        } else {
            binding.recyclerSugerencias.setVisibility(View.VISIBLE);
            sugerenciasAdapter.setSugerencias(sugerencias);
        }
    }

    @Override
    public void onSugerenciaClick(Libro libro) {
        binding.etBusqueda.setText(libro.getTitulo());
        binding.recyclerSugerencias.setVisibility(View.GONE);
        abrirDetalle(libro);
    }

    private void mostrarDetalleLibro(Libro libro) {
        if (libro != null) {
            abrirDetalle(libro);
        }
    }

    private void abrirDetalle(Libro libro) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_TITULO, libro.getTitulo());
        intent.putExtra(DetailActivity.EXTRA_AUTOR, libro.getAutor());
        intent.putExtra(DetailActivity.EXTRA_ANIO, libro.getAnio());
        intent.putExtra(DetailActivity.EXTRA_PAGINAS, libro.getPaginas());
        intent.putStringArrayListExtra(DetailActivity.EXTRA_GENEROS, new ArrayList<>(libro.getGeneros()));
        intent.putExtra(DetailActivity.EXTRA_DESCRIPCION, libro.getDescripcion());
        startActivity(intent);
        viewModel.limpiarError();
    }

    private void mostrarError(String mensaje) {
        if (mensaje != null) {
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}