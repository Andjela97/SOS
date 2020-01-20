package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PregledLekova extends AppCompatActivity {

    DBBroker dbb;
    ListView listaJutro;
    ListView listaPodne;
    ListView listaVece;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled_lekova);

        dbb = new DBBroker(this);
        listaJutro = (ListView) findViewById(R.id.listJutro);
        listaPodne = (ListView) findViewById(R.id.listPodne);
        listaVece = (ListView) findViewById(R.id.listVece);




    }
}
