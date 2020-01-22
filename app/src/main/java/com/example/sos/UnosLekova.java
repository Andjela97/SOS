package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UnosLekova extends AppCompatActivity {

    DBBroker dbb;
    EditText naziv,genIme;
    Button btnDodaj;
    final Adapter adapter = new Adapter();
    ArrayList<Lek> izabraniLekovi = new ArrayList<>();
    ArrayList<Lek> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_lekova);
        dbb = new DBBroker(this);

        naziv = (EditText) findViewById(R.id.txtNaziv);
        genIme = (EditText) findViewById(R.id.txtGenIme);
        btnDodaj = (Button) findViewById(R.id.btnDodajLek);

        Button obrisi = findViewById(R.id.btnObrisi);
        obrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbb.obrisiLekove(izabraniLekovi);
                lista=dbb.dajSveLekove();
                adapter.notifyDataSetChanged();
                Toast.makeText(UnosLekova.this,"Uspesno obrisano!",Toast.LENGTH_LONG).show();


            }
        });
        addData();
       lista = dbb.dajSveLekove();
        ListView listView = findViewById(R.id.moguciLekovi);

        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (!izabraniLekovi.contains(lista.get(i)))
                    izabraniLekovi.add(lista.get(i));
                else izabraniLekovi.remove(lista.get(i));

            }
        });

    }

    public void addData(){
        btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lek l = new Lek();
                l.setNaziv(naziv.getText().toString());
                l.setGenericko_ime(genIme.getText().toString());
                long vr = dbb.dodajLek(l);
                lista=dbb.dajSveLekove();
                adapter.notifyDataSetChanged();
                if(vr != -1){
                    Toast.makeText(UnosLekova.this,"Unet lek!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(UnosLekova.this,"Lek nije unet!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return lista.size();
        }

        @Override
        public Object getItem(int i) {
            return lista.get(i);
        }

        @Override
        public long getItemId(int i) {
            Lek kom = (Lek) getItem(i);
            return kom.getLek_id();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.prikaz_list_item, null);

            TextView txtNaziv = view.findViewById(R.id.txtNazivLeka);
            //CheckBox cekirano = view.findViewById(R.id.checkBox);

            txtNaziv.setText(lista.get(i).getNaziv());
            // cekirano.setChecked(lista.get(i).isSelected());

            return view;
        }
    }

}
