package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PregledLekova extends AppCompatActivity {

    DBBroker dbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled_lekova);

        dbb = new DBBroker(this);

    }
}
