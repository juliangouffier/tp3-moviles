package com.example.tp3moviles;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp3moviles.databinding.ActivityDetailBinding;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    public static final String EXTRA_TITULO = "titulo";
    public static final String EXTRA_AUTOR = "autor";
    public static final String EXTRA_ANIO = "anio";
    public static final String EXTRA_PAGINAS = "paginas";
    public static final String EXTRA_GENEROS = "generos";
    public static final String EXTRA_DESCRIPCION = "descripcion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String titulo = intent.getStringExtra(EXTRA_TITULO);
        String autor = intent.getStringExtra(EXTRA_AUTOR);
        int anio = intent.getIntExtra(EXTRA_ANIO, 0);
        int paginas = intent.getIntExtra(EXTRA_PAGINAS, 0);
        ArrayList<String> generos = intent.getStringArrayListExtra(EXTRA_GENEROS);
        String descripcion = intent.getStringExtra(EXTRA_DESCRIPCION);

        binding.tvTitulo.setText(titulo);
        binding.tvAutor.setText(autor);
        binding.tvPaginas.setText(paginas + " páginas");
        binding.tvAnio.setText(String.valueOf(anio));

        if (generos != null && generos.size() > 0) {
            StringBuilder generosText = new StringBuilder();
            for (int i = 0; i < generos.size(); i++) {
                generosText.append(generos.get(i));
                if (i < generos.size() - 1) {
                    generosText.append("  |  ");
                }
            }
            binding.tvGeneros.setText(generosText.toString());
        }

        binding.tvDescripcion.setText(descripcion);

        binding.btnVolver.setOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
