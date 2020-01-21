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
import android.widget.Toast;

import java.util.ArrayList;

public class UnosPodsetnika extends AppCompatActivity {

    ArrayList<Lek> lista;
    DBBroker db;
    final Adapter adapter = new Adapter();
    CheckBox jutro;
    CheckBox podne;
    CheckBox noc;
    Button potvrdiPodsetnik;
    ListView listView;
    ArrayList<Lek> izabraniLekovi = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_podsetnika);

        listView = findViewById(R.id.moguciLekovi);


        try {
            db = new DBBroker(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        lista = db.dajSveLekove();

        potvrdiPodsetnik = findViewById(R.id.potvrdiPotsetnikBtn);
        jutro = findViewById(R.id.checkBoxJutro);
        podne = findViewById(R.id.checkBoxPodne);
        noc = findViewById(R.id.checkBoxVece);

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

        potvrdiPodsetnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!noc.isChecked() & !jutro.isChecked() & !podne.isChecked()) {
                    Toast.makeText(UnosPodsetnika.this, "Morate izabrati vreme podsetnika!", Toast.LENGTH_LONG).show();
                    System.out.println(jutro.isSelected());
                    System.out.println(noc.isSelected());
                    System.out.println(podne.isSelected());
                    return;
                }

                if (izabraniLekovi.isEmpty()) {
                    Toast.makeText(UnosPodsetnika.this, "Morate izabrati bar jedan lek!", Toast.LENGTH_LONG).show();
                    return;
                }


                if (jutro.isChecked()) {
                    for (Lek l : izabraniLekovi) {
                        Podsetnik pom = new Podsetnik();
                        pom.setLek_id(l.getLek_id());
                        pom.setVreme_terapije(Podsetnik.Vreme_terapije.jutro);
                        db.dodajPodsetnik(pom);

                    }
                }
                if (podne.isChecked()) {
                    for (Lek l : izabraniLekovi) {
                        Podsetnik pom = new Podsetnik();
                        pom.setLek_id(l.getLek_id());
                        pom.setVreme_terapije(Podsetnik.Vreme_terapije.podne);
                        db.dodajPodsetnik(pom);

                    }
                }
                if (noc.isChecked()) {
                    for (Lek l : izabraniLekovi) {
                        Podsetnik pom = new Podsetnik();
                        pom.setLek_id(l.getLek_id());
                        pom.setVreme_terapije(Podsetnik.Vreme_terapije.vece);
                        db.dodajPodsetnik(pom);

                    }
                }
                Toast.makeText(UnosPodsetnika.this, "Uspesno uneti podsetnici!", Toast.LENGTH_LONG).show();
                finish();


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
