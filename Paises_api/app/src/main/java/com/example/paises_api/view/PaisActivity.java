package com.example.paises_api.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.paises_api.R;
import com.example.paises_api.adapter.PaisListAdapter;
import com.example.paises_api.controller.PaisController;
import com.example.paises_api.model.Pais;

import java.util.ArrayList;

public class PaisActivity extends AppCompatActivity {

    private PaisController controller;
    private RecyclerView rvPaises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais);

        rvPaises = findViewById(R.id.rvPaises);

        controller = new PaisController(this);
        controller.retornaPaises();
        carregarListaPaises();
    }

    private void carregarListaPaises(){
        ArrayList<Pais> listaPaises = controller.retornaPaises();
        PaisListAdapter adapter = new PaisListAdapter(listaPaises, this);
        rvPaises.setLayoutManager(new LinearLayoutManager(this));
        rvPaises.setAdapter(adapter);

    }
}