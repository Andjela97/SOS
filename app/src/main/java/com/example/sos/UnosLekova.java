package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UnosLekova extends AppCompatActivity {

    DBBroker dbb;
    EditText naziv,genIme;
    Button btnDodaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_lekova);
        dbb = new DBBroker(this);

        naziv = (EditText) findViewById(R.id.txtNaziv);
        genIme = (EditText) findViewById(R.id.txtGenIme);
        btnDodaj = (Button) findViewById(R.id.btnDodajLek);

        addData();

    }

    public void addData(){
        btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lek l = new Lek();
                l.setNaziv(naziv.getText().toString());
                l.setGenericko_ime(genIme.getText().toString());
                long vr = dbb.dodajLek(l);
                if(vr != -1){
                    Toast.makeText(UnosLekova.this,"Unet lek!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(UnosLekova.this,"Lek nije unet!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
