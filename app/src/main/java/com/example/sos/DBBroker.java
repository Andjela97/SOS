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
    public Lek dajLek(int id){
        String query = "SELECT  * FROM lekovi where lek_id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Lek lek = new Lek();
        lek.setLek_id(cursor.getInt(0));
        lek.setNaziv(cursor.getString(1));
        lek.setGenericko_ime(cursor.getString(2));
        return lek;

    }
    public ArrayList<Lek> dajSveLekoveZaVreme(Podsetnik.Vreme_terapije vreme_terapije){
        ArrayList<Lek> lista = new ArrayList<>();
        String query = "SELECT  l.lek_id, naziv, genericko_ime FROM lekovi l JOIN podsetnici p ON l.lek_id = p.lek_id " +
                "where vreme_terapije = '"+vreme_terapije+"'";
        System.out.println(query);
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
    public long dodajPodsetnik(Podsetnik p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("lek_id",p.getLek_id());
        values.put("vreme_terapije",p.getVreme_terapije().name());
        long rt = (db.insert("podsetnici", null,values));

        db.close();
        return rt;
    }
}