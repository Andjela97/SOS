package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class UnosPodsetnika extends AppCompatActivity {

    ArrayList<Lek> lista;
    DBBroker db;
    final Adapter adapter = new Adapter();
    CheckBox jutro = findViewById(R.id.checkBoxJutro);
    CheckBox podne = findViewById(R.id.checkBoxPodne);
    CheckBox noc = findViewById(R.id.checkBoxVece);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_podsetnika);

        ListView listView = findViewById(R.id.moguciLekovi);


        try {
            db = new DBBroker(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //db.dropTables();
        lista = db.dajSveLekove();

        Button potvrdiPotsetnik = findViewById(R.id.potvrdiPotsetnikBtn);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("JA KLIKNUTA");


            }
        });
        potvrdiPotsetnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Lek l : lista
                ) {
                    if (l.isSelected()) {
                        Podsetnik pom = new Podsetnik();
                        pom.setLek_id(l.getLek_id());
                        if (jutro.isChecked())
                            pom.setVreme_terapije(Podsetnik.Vreme_terapije.jutro);
                        if (podne.isChecked())
                            pom.setVreme_terapije(Podsetnik.Vreme_terapije.podne);
                        if (noc.isChecked()) pom.setVreme_terapije(Podsetnik.Vreme_terapije.vece);



                    }

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
//            SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs",MODE_PRIVATE);
//            view.setBackgroundColor(Color.parseColor('#'+sharedPreferences.getString("boja","FFFFFF")));
            TextView txtNaziv = view.findViewById(R.id.txtNazivLeka);
            CheckBox cekirano = view.findViewById(R.id.checkBox);

            txtNaziv.setText(lista.get(i).getNaziv());
            cekirano.setChecked(lista.get(i).isSelected());

            return view;
        }

    }
}
