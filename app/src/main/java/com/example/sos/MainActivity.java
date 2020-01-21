package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    public Button btn;
    public Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btnLekovi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLekovi();
            }
        });
        btn1 = (Button)findViewById(R.id.btnsos);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSOS();
            }
        });

        ImageButton dugmePodesavanja =  findViewById(R.id.btnpodesavanja);
        dugmePodesavanja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPodesavanja();
            }
        });
    }


    public void openLekovi(){
        Intent intent = new Intent(this, Lekovi.class);
        startActivity(intent);
    }public void openSOS(){
        Intent intent = new Intent(this, SOS.class);
        startActivity(intent);
    }

    public void openPodesavanja(){
        Intent intent = new Intent(this, Podesavanja.class);
        startActivity(intent);
    }
}
