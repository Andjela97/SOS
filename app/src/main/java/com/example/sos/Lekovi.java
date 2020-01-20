package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Lekovi extends AppCompatActivity {

    public Button btnPregled;
    public Button btnUnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPregled = (Button)findViewById(R.id.btnPregledLekova);
        btnUnos = (Button)findViewById(R.id.btnUnosLekova);
        btnPregled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPregledLekova();
                openUnosLekova();
            }
        });
    }


    public void openPregledLekova(){
        Intent intent = new Intent(this, PregledLekova.class);
        startActivity(intent);
    }

    public void openUnosLekova(){
        Intent intent = new Intent(this, UnosLekova.class);
        startActivity(intent);
    }
}
