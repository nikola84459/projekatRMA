package ac.rs.singidunum.aplikacija;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Baza extends SQLiteOpenHelper {
    private static final String BAZA_FILE_NAME = "baza-rezervacije";

    public Baza(Context context) {
        super(context, BAZA_FILE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String upit = String.format(
                "CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                RezervacijaModel.IME_TABELE, RezervacijaModel.COLUMN_REZERVACIJA_ID, RezervacijaModel.COLUMN_ODLAZNI, RezervacijaModel.COLUMN_DOLAZNI, RezervacijaModel.COLUMN_DATUM_POLASKA,
                RezervacijaModel.COLUMN_DATUM_POVRATKA, RezervacijaModel.COLUMN_BROJ_LETA_ODLAZAK, RezervacijaModel.COLUMN_BROJ_LETA_POVRATAK, RezervacijaModel.COLUMN_VREME_ODLAZAK,
                RezervacijaModel.COLUMN_VREME_POVRATAK, RezervacijaModel.COLUMN_BROJ_REZERVACIJE
        );

        db.execSQL(upit);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = String.format("DROP TABLE IF EXISTS %s", RezervacijaModel.IME_TABELE);
        db.execSQL(query);
        onCreate(db);
    }

    public void addRezervacija (String odlazni, String dolazni, String datumPolaska, String datumPovratka, String brojLetaOdlazak, String brojLetaPovratak, String vremeOdlazak, String vremePovratak, String brojRezervacije) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(RezervacijaModel.COLUMN_ODLAZNI, odlazni);
        cv.put(RezervacijaModel.COLUMN_DOLAZNI, dolazni);
        cv.put(RezervacijaModel.COLUMN_DATUM_POLASKA, datumPolaska);
        cv.put(RezervacijaModel.COLUMN_DATUM_POVRATKA, datumPovratka);
        cv.put(RezervacijaModel.COLUMN_BROJ_LETA_ODLAZAK, brojLetaOdlazak);
        cv.put(RezervacijaModel.COLUMN_BROJ_LETA_POVRATAK, brojLetaPovratak);
        cv.put(RezervacijaModel.COLUMN_VREME_ODLAZAK, vremeOdlazak);
        cv.put(RezervacijaModel.COLUMN_VREME_POVRATAK, vremePovratak);
        cv.put(RezervacijaModel.COLUMN_BROJ_REZERVACIJE, brojRezervacije);

       db.insert(RezervacijaModel.IME_TABELE, null, cv);
    }

    public void editRezervacija (int rezervacija_id, String odlazni, String dolazni, String datumPolaska, String datumPovratka, String brojLetaOdlazak, String brojLetaPovratak, String vremeOdlazak, String vremePovratak, String brojRezervacije) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(RezervacijaModel.COLUMN_ODLAZNI, odlazni);
        cv.put(RezervacijaModel.COLUMN_DOLAZNI, dolazni);
        cv.put(RezervacijaModel.COLUMN_DATUM_POLASKA, datumPolaska);
        cv.put(RezervacijaModel.COLUMN_DATUM_POVRATKA, datumPovratka);
        cv.put(RezervacijaModel.COLUMN_BROJ_LETA_ODLAZAK, brojLetaOdlazak);
        cv.put(RezervacijaModel.COLUMN_BROJ_LETA_POVRATAK, brojLetaPovratak);
        cv.put(RezervacijaModel.COLUMN_VREME_ODLAZAK, vremeOdlazak);
        cv.put(RezervacijaModel.COLUMN_VREME_POVRATAK, vremePovratak);
        cv.put(RezervacijaModel.COLUMN_BROJ_REZERVACIJE, brojRezervacije);

        db.update(RezervacijaModel.IME_TABELE, cv, RezervacijaModel.COLUMN_REZERVACIJA_ID + " = ?", new String[]{String.valueOf(rezervacija_id)} );
    }

    public int deleteRezervacija (int rezervacija_id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(RezervacijaModel.IME_TABELE, RezervacijaModel.COLUMN_REZERVACIJA_ID + " = ?", new String[] {String.valueOf(rezervacija_id)});
    }

    public RezervacijaModel getRezervacijaById (int rezervacija_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format("SELECT * FROM %s WHERE %s = ?", RezervacijaModel.IME_TABELE, RezervacijaModel.COLUMN_REZERVACIJA_ID);
        Cursor rezultat = db.rawQuery(query,new String[] {String.valueOf(rezervacija_id)});
        if(rezultat.moveToFirst()){
            String odlazni = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_ODLAZNI));
            String dolazni = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_DOLAZNI));
            String datumPolazak = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_DATUM_POLASKA));
            String datumPovratak = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_DATUM_POVRATKA));
            String vremeOdlazak = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_VREME_ODLAZAK));
            String vremePovratak = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_DATUM_POVRATKA));
            String brojLetaOdlazak = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_BROJ_LETA_ODLAZAK));
            String brojLetaPovratak = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_BROJ_LETA_POVRATAK));
            String brojRezervacije = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_BROJ_REZERVACIJE));
            return new RezervacijaModel(rezervacija_id, odlazni, dolazni, datumPolazak,  datumPovratak, brojLetaOdlazak, vremeOdlazak, brojLetaPovratak, vremePovratak, brojRezervacije);
        } else {
            return null;
        }
    }

    public List<RezervacijaModel> getAllRezervacije (){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format("SELECT * FROM %s ", RezervacijaModel.IME_TABELE);
        Cursor rezultat = db.rawQuery(query, null);

        rezultat.moveToFirst();

        List<RezervacijaModel> rezervacije = new ArrayList<>(rezultat.getCount());

        while (!rezultat.isAfterLast()){
            int rezervacija_id = rezultat.getInt(rezultat.getColumnIndex(RezervacijaModel.COLUMN_REZERVACIJA_ID));
            String odlazni = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_ODLAZNI));
            String dolazni = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_DOLAZNI));
            String datumPolazak = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_DATUM_POLASKA));
            String datumPovratak = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_DATUM_POVRATKA));
            String brojLetaOdlazak  = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_BROJ_LETA_ODLAZAK));
            String brojLetaPovratak = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_BROJ_LETA_POVRATAK));
            String vremeOdlazak = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_VREME_ODLAZAK));
            String vremePovratak = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_VREME_POVRATAK));
            String brojRezervacije = rezultat.getString(rezultat.getColumnIndex(RezervacijaModel.COLUMN_BROJ_REZERVACIJE));
            rezervacije.add(new RezervacijaModel(rezervacija_id, odlazni, dolazni, datumPolazak, datumPovratak, brojLetaOdlazak, vremeOdlazak, brojLetaPovratak, vremePovratak, brojRezervacije));
            rezultat.moveToNext();
        }

        return rezervacije;
    }

}
