package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class PregledPodsetnika extends AppCompatActivity implements DialogYesNo.DialogYesNoListener {

    DBBroker dbb;
    ListView listaJutro;
    ListView listaPodne;
    ListView listaVece;
    ArrayList<ZaPrikazPrikaza> lJutro = new ArrayList<>();
    ArrayList<ZaPrikazPrikaza> lPodne = new ArrayList<>();
    ArrayList<ZaPrikazPrikaza> lVece = new ArrayList<>();
    ZaPrikazPrikaza selektovanPod;
    final Adapter1 adapter1 = new Adapter1();
    final Adapter2 adapter2 = new Adapter2();
    final Adapter3 adapter3 = new Adapter3();

    Button btnNotif;


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Kanal notifikacija";
            String description = "Kanal notifikacija";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel kanal = new NotificationChannel("kanalID", name, importance);
            kanal.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(kanal);
        }
    }

    public void posaljiNotifJutro() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 52);

        ArrayList<ZaPrikazPrikaza> lekoviJutro = dbb.dajSveLekoveZaVreme(Podsetnik.Vreme_terapije.jutro);
        String kobasicaJutro = "Popijte sledece lekove: ";
        for (int i = 0; i < lekoviJutro.size(); i++) {
            kobasicaJutro += "" + lekoviJutro.get(i).getNaziv() + ((i == lekoviJutro.size() - 1) ? "." : ", ");
            System.out.println(kobasicaJutro);
        }
        Intent intent = new Intent(getApplicationContext(), ReminderBroadcast.class);
        intent.putExtra("imena", kobasicaJutro);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void posaljiNotifPodne() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 00);
        ArrayList<ZaPrikazPrikaza> lekoviPodne = dbb.dajSveLekoveZaVreme(Podsetnik.Vreme_terapije.podne);
        String kobasicaPodne = "Popijte sledece lekove: ";
        for (int i = 0; i < lekoviPodne.size(); i++) {
            kobasicaPodne += "" + lekoviPodne.get(i).getNaziv() + ((i == lekoviPodne.size() - 1) ? "." : ", ");
            System.out.println(kobasicaPodne);
        }
        Intent intent = new Intent(getApplicationContext(), ReminderBroadcast.class);

        intent = new Intent(getApplicationContext(), ReminderBroadcast.class);
        intent.putExtra("imena", kobasicaPodne);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);


        alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void posaljiNotifVece() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 00);
        ArrayList<ZaPrikazPrikaza> lekoviVece = dbb.dajSveLekoveZaVreme(Podsetnik.Vreme_terapije.vece);
        String kobasicaVece = "Popijte sledece lekove: ";
        for (int i = 0; i < lekoviVece.size(); i++) {
            kobasicaVece += "" + lekoviVece.get(i).getNaziv() + ((i == lekoviVece.size() - 1) ? "." : ", ");
            System.out.println(kobasicaVece);
        }
        Intent intent = new Intent(getApplicationContext(), ReminderBroadcast.class);

        intent = new Intent(getApplicationContext(), ReminderBroadcast.class);
        intent.putExtra("imena", kobasicaVece);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);


        alarmManager2.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled_podsetnika);

        createNotificationChannel();


        btnNotif = findViewById(R.id.btnNotifikacije);

        btnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posaljiNotifJutro();
                posaljiNotifPodne();
                posaljiNotifVece();

                Toast.makeText(PregledPodsetnika.this, "Obavestenja ukljucena!", Toast.LENGTH_SHORT).show();
            }
        });


        dbb = new DBBroker(this);
        listaJutro = (ListView) findViewById(R.id.listJutro);
        listaPodne = (ListView) findViewById(R.id.listPodne);
        listaVece = (ListView) findViewById(R.id.listVece);

        lJutro = dbb.dajSveLekoveZaVreme(Podsetnik.Vreme_terapije.jutro);
        lPodne = dbb.dajSveLekoveZaVreme(Podsetnik.Vreme_terapije.podne);
        lVece = dbb.dajSveLekoveZaVreme(Podsetnik.Vreme_terapije.vece);


        listaJutro.setAdapter(adapter1);
        listaPodne.setAdapter(adapter2);
        listaVece.setAdapter(adapter3);

        listaJutro.setChoiceMode(ListView.CHOICE_MODE_NONE);

        listaJutro.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                DialogYesNo dijalog = new DialogYesNo();
                dijalog.show(getSupportFragmentManager(), "dialog");
                selektovanPod = lJutro.get(i);
            }
        });

        listaPodne.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listaPodne.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                DialogYesNo dijalog = new DialogYesNo();
                dijalog.show(getSupportFragmentManager(), "dialog");
                selektovanPod = lPodne.get(i);
            }
        });

        listaVece.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listaVece.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                DialogYesNo dijalog = new DialogYesNo();
                dijalog.show(getSupportFragmentManager(), "dialog");
                selektovanPod = lVece.get(i);
            }
        });

    }

    public void kliknutoDa() {
        dbb.obrisiPodsetnik(selektovanPod);
        lJutro = dbb.dajSveLekoveZaVreme(Podsetnik.Vreme_terapije.jutro);
        lPodne = dbb.dajSveLekoveZaVreme(Podsetnik.Vreme_terapije.podne);
        lVece = dbb.dajSveLekoveZaVreme(Podsetnik.Vreme_terapije.vece);
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();


    }


    public class Adapter1 extends BaseAdapter {

        @Override
        public int getCount() {
            return lJutro.size();
        }

        @Override
        public Object getItem(int i) {
            return lJutro.get(i);
        }

        @Override
        public long getItemId(int i) {
            ZaPrikazPrikaza kom = (ZaPrikazPrikaza) getItem(i);
            return kom.getPodsetnik_id();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.bez_cb, null);

            TextView txtNaziv = view.findViewById(R.id.txtNazivLeka);

            txtNaziv.setText(lJutro.get(i).getNaziv());

            return view;
        }
    }

    public class Adapter2 extends BaseAdapter {

        @Override
        public int getCount() {
            return lPodne.size();
        }

        @Override
        public Object getItem(int i) {
            return lPodne.get(i);
        }

        @Override
        public long getItemId(int i) {
            ZaPrikazPrikaza kom = (ZaPrikazPrikaza) getItem(i);
            return kom.getPodsetnik_id();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.bez_cb, null);

            TextView txtNaziv = view.findViewById(R.id.txtNazivLeka);

            txtNaziv.setText(lPodne.get(i).getNaziv());

            return view;
        }
    }


    public class Adapter3 extends BaseAdapter {

        @Override
        public int getCount() {
            return lVece.size();
        }

        @Override
        public Object getItem(int i) {
            return lVece.get(i);
        }

        @Override
        public long getItemId(int i) {
            ZaPrikazPrikaza kom = (ZaPrikazPrikaza) getItem(i);
            return kom.getPodsetnik_id();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.bez_cb, null);

            TextView txtNaziv = view.findViewById(R.id.txtNazivLeka);

            txtNaziv.setText(lVece.get(i).getNaziv());

            return view;
        }
    }


}
