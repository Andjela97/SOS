package com.example.sos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBBroker extends SQLiteOpenHelper {

    private static final String DB_NAME = "baza.db";
    private static final int DB_VERSION = 1;

    public DBBroker(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE lekovi(lek_id INTEGER PRIMARY KEY AUTOINCREMENT, naziv TEXT, genericko_ime TEXT)");
        db.execSQL("CREATE TABLE podsetnici(podsetnik_id INTEGER PRIMARY KEY AUTOINCREMENT, vreme_terapije TEXT,lek_id INTEGER," +
                " FOREIGN KEY (lek_id) REFERENCES lekovi(lek_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS lekovi");
        db.execSQL("DROP TABLE IF EXISTS podsetnici");
        onCreate(db);
    }




    public ArrayList<Lek> dajSveLekove(){
        ArrayList<Lek> lista = new ArrayList<>();
        String query = "SELECT  * FROM lekovi";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {

            Lek lek = new Lek();
            lek.setLek_id(cursor.getInt(0));
            lek.setNaziv(cursor.getString(1));
            lek.setGenericko_ime(cursor.getString(2));
            lista.add(lek);
        }
        return lista;
    }
    public long dodajLek(Lek l){
        System.out.println("PRE DODAVANJA");
        System.out.println(l);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("naziv",l.getNaziv());
        values.put("genericko_ime",l.getGenericko_ime());
        long rt = (db.insert("lekovi", null,values));

        db.close();
        return rt;
    }
    public void obrisiLek(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String upit = "delete from lekovi where lek_id = " + id;
        System.out.println(upit);
        db.execSQL(upit);
        db.close();
    }

    public void obrisiPodsetnik(ZaPrikazPrikaza p) {
        SQLiteDatabase db = this.getWritableDatabase();
        String upit = "delete from podsetnici where podsetnik_id = '"+p.getPodsetnik_id()+"'";
        System.out.println(upit);
        db.execSQL(upit);
        db.close();
    }


    public ArrayList<ZaPrikazPrikaza> dajSveLekoveZaVreme(Podsetnik.Vreme_terapije vreme_terapije){
        ArrayList<ZaPrikazPrikaza> lista = new ArrayList<>();
        String query = "SELECT  naziv,  podsetnik_id FROM lekovi l JOIN podsetnici p ON l.lek_id = p.lek_id " +
                "where vreme_terapije = '"+vreme_terapije+"'";
        System.out.println(query);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {

            ZaPrikazPrikaza zpp = new ZaPrikazPrikaza();
            zpp.setNaziv(cursor.getString(0));
            zpp.setPodsetnik_id(cursor.getInt(1));
            lista.add(zpp);
        }
        return lista;
    }
    public ArrayList<Podsetnik> dajSvePodsetnike(){
        ArrayList<Podsetnik> lista = new ArrayList<>();
        String query = "SELECT  * FROM podsetnici";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {

            Podsetnik p = new Podsetnik();
            p.setPodsetnik_id(cursor.getInt(0));
            if (cursor.getString(1).equals(Podsetnik.Vreme_terapije.jutro.name()))
            p.setVreme_terapije(Podsetnik.Vreme_terapije.jutro);
            else  if (cursor.getString(1).equals(Podsetnik.Vreme_terapije.podne.name()))
                p.setVreme_terapije(Podsetnik.Vreme_terapije.podne);
            else
                p.setVreme_terapije(Podsetnik.Vreme_terapije.vece);
            p.setLek_id(cursor.getInt(2));
            System.out.println(p.getLek_id());
            System.out.println(p.getVreme_terapije().name());
            lista.add(p);
        }
        return lista;
    }

    public long dodajPodsetnik(Podsetnik p){
        ArrayList<Podsetnik> lista = dajSvePodsetnike();
        if (lista.contains(p)){
            System.out.println("NECE DA MOZE");
            return -1;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("lek_id",p.getLek_id());
        values.put("vreme_terapije",p.getVreme_terapije().name());
        long rt = (db.insert("podsetnici", null,values));

        db.close();
        return rt;
    }
}