package com.example.sos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Podesavanja extends AppCompatActivity {
    EditText ime;

    EditText adresa;
    EditText datumrodj;
    EditText kontakt;
    EditText veza;
    EditText telefon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podesavanja);
        Button ponisti = findViewById(R.id.btnponisti);
        Button sacuvaj = findViewById(R.id.btnSacuvaj);
        ponisti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sacuvaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sacuvaj();
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        ime = findViewById(R.id.editTextImePrez);
        adresa = findViewById(R.id.editTextAdresa);
        datumrodj = findViewById(R.id.editTextDatum);
        kontakt = findViewById(R.id.editTextImePrezKontakta);
        veza = findViewById(R.id.editTextVeza);
        telefon = findViewById(R.id.editTextVeza2);

        ime.setText(sharedPreferences.getString("ime",null));
        adresa.setText(sharedPreferences.getString("adresa",null));
        datumrodj.setText(sharedPreferences.getString("datum",null));
        kontakt.setText(sharedPreferences.getString("kontakt",null));
        veza.setText(sharedPreferences.getString("veza",null));
        telefon.setText(sharedPreferences.getString("telefon",null));


    }

    private void sacuvaj() {

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ime", ime.getText().toString().trim());
        editor.putString("adresa", adresa.getText().toString().trim());
        editor.putString("datum", datumrodj.getText().toString().trim());
        editor.putString("kontakt", kontakt.getText().toString().trim());
        editor.putString("veza", veza.getText().toString().trim());
        editor.putString("telefon", telefon.getText().toString().trim());

        editor.apply();
        finish();
    }
}
