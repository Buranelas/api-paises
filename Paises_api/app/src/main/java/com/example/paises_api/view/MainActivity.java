package com.example.paises_api.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.paises_api.R;
import com.example.paises_api.controller.PaisController;

public class MainActivity extends AppCompatActivity {

    private PaisController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new PaisController(this);
        controller.getPais(this);

    }

    public void pesquisarPaises(View view) {

        Intent intent = new Intent(MainActivity.this,
                PaisActivity.class);
        startActivity(intent);


    }
}