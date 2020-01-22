package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Lekovi extends AppCompatActivity {

    public Button btnPregled;
    public Button btnUnos;
    public Button btnPods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lekovi);

        btnPregled = (Button)findViewById(R.id.btnPregledLekova);
        btnUnos = (Button)findViewById(R.id.btnUnosLekova);
        btnPods = (Button)findViewById(R.id.btnPodsetnik);

        btnPregled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPregledLekova();
            }
        });
        btnUnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUnosLekova();
            }
        });
        btnPods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUnosPodsetnika();
            }
        });
    }


    public void openPregledLekova(){
        Intent intent = new Intent(this, PregledPodsetnika.class);
        startActivity(intent);
    }

    public void openUnosLekova(){
        Intent intent = new Intent(this, UnosLekova.class);
        startActivity(intent);
    }

    public void openUnosPodsetnika(){
        Intent intent = new Intent(this, UnosPodsetnika.class);
        startActivity(intent);
    }

}
