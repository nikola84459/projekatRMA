package ac.rs.singidunum.aplikacija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String SHARED_PREFERENCES_PREFIX = "PodaciSharedPreferencesPrefix";
    private final static String SHARED_PREFERENCES_KEY_ODLAZNI = "odlazni";
    private final static String SHARED_PREFERENCES_KEY_DOLAZNI = "dolazni";
    private final static String SHARED_PREFERENCES_KEY_DATUM_POLASKA_GODINA = "datum_polaska_godina";
    private final static String SHARED_PREFERENCES_KEY_DATUM_POLASKA_MESEC = "datum_polaska_mesec";
    private final static String SHARED_PREFERENCES_KEY_DATUM_POLASKA_DAN = "datum_polaska_dan";
    private final static String SHARED_PREFERENCES_KEY_DATUM_POVRATKA_GODINA = "datum_povratka_godina";
    private final static String SHARED_PREFERENCES_KEY_DATUM_POVRATKA_MESEC = "datum_povratka_mesec";
    private final static String SHARED_PREFERENCES_KEY_DATUM_POVRATKA_DAN = "datum_povratka_dan";
    private final static String SHARED_PREFERENCES_KEY_BROJ_OSOBA = "broj_osoba";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.dugmePretrazi).setOnClickListener(this);
        findViewById(R.id.dugmeCekiranje).setOnClickListener(this);
        findViewById(R.id.dugmeRezervacije).setOnClickListener(this);

        procitajPodatke();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dugmePretrazi:
                this.prosledi();
                break;
            case R.id.dugmeCekiranje:
                this.cekiranje();
                break;
            case R.id.dugmeRezervacije:
                this.rezervacije();
                break;
        }
    }

     public void prosledi(){
         Intent i = new Intent(this, PrikazLetaActivity.class);
         Bundle b = new Bundle();

         String inputOd = ((EditText) findViewById(R.id.inputOd)).getText().toString();
         String inputDo = ((EditText) findViewById(R.id.inputDo)).getText().toString();
         DatePicker inputDatumPolazka = (DatePicker) findViewById(R.id.inputDatumPolazka);
         DatePicker inputDatumPovratka = (DatePicker) findViewById(R.id.inputDatumPovratka);
         Spinner inputBrojOsoba = (Spinner) findViewById(R.id.inputBrojOsoba);
         String brojOsoba = (String) inputBrojOsoba.getSelectedItem();
         String inputDatumPolazkaString = null;
         String inputDatumPovratkaString = null;
         if(inputDatumPolazka.getMonth() > 9) {
              inputDatumPolazkaString = String.format("%4d-%1d-%2d", inputDatumPolazka.getYear(), inputDatumPolazka.getMonth() + 1, inputDatumPolazka.getDayOfMonth()).replaceAll(" ", "");
         } else {
             inputDatumPolazkaString = String.format("%4d-%1d%2d-%2d", inputDatumPolazka.getYear(), 0, inputDatumPolazka.getMonth() + 1, inputDatumPolazka.getDayOfMonth()).replaceAll(" ", "");
         }

         if(inputDatumPovratka.getMonth() > 9) {
             inputDatumPovratkaString = String.format("%4d-%1d-%2d", inputDatumPovratka.getYear(), inputDatumPovratka.getMonth() + 1, inputDatumPovratka.getDayOfMonth()).replaceAll(" ","");
         } else {
             inputDatumPovratkaString = String.format("%4d-%1d%2d-%2d", inputDatumPovratka.getYear(), 0 ,inputDatumPovratka.getMonth() + 1, inputDatumPovratka.getDayOfMonth()).replaceAll(" ","");
         }


         b.putString("inputOd",inputOd);
         b.putString("inputDo",inputDo);
         b.putString("inputDatumPolazka",inputDatumPolazkaString);
         b.putString("inputDatumPovratka",inputDatumPovratkaString);
         b.putString("inputBrojOsoba",brojOsoba);

         sacuvajPodatke();

         i.putExtras(b);
         startActivity(i);

     }

     public void cekiranje(){
        Intent i = new Intent(this, CekiranjeActivity.class);
        startActivity(i);
     }

     public void rezervacije() {
        Intent i = new Intent(this, PregledRezervacijaActivity.class);
        startActivity(i);
     }

    @Override
    protected void onStop() {
        super.onStop();
        sacuvajPodatke();
    }

    public void sacuvajPodatke() {
        String inputOd = ((EditText) findViewById(R.id.inputOd)).getText().toString();
        String inputDo = ((EditText) findViewById(R.id.inputDo)).getText().toString();
        DatePicker inputDatumPolazka = (DatePicker) findViewById(R.id.inputDatumPolazka);
        DatePicker inputDatumPovratka = (DatePicker) findViewById(R.id.inputDatumPovratka);
        Spinner inputBrojOsoba = (Spinner) findViewById(R.id.inputBrojOsoba);
        int brojOsoba = inputBrojOsoba.getSelectedItemPosition();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_PREFIX, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(SHARED_PREFERENCES_KEY_ODLAZNI, inputOd);
        editor.putString(SHARED_PREFERENCES_KEY_DOLAZNI, inputDo);
        editor.putInt(SHARED_PREFERENCES_KEY_DATUM_POLASKA_GODINA, inputDatumPolazka.getYear());
        editor.putInt(SHARED_PREFERENCES_KEY_DATUM_POLASKA_MESEC, inputDatumPolazka.getMonth());
        editor.putInt(SHARED_PREFERENCES_KEY_DATUM_POLASKA_DAN, inputDatumPolazka.getDayOfMonth());
        editor.putInt(SHARED_PREFERENCES_KEY_DATUM_POVRATKA_GODINA, inputDatumPovratka.getYear());
        editor.putInt(SHARED_PREFERENCES_KEY_DATUM_POVRATKA_MESEC, inputDatumPovratka.getMonth());
        editor.putInt(SHARED_PREFERENCES_KEY_DATUM_POVRATKA_DAN, inputDatumPovratka.getDayOfMonth());
        editor.putInt(SHARED_PREFERENCES_KEY_BROJ_OSOBA, brojOsoba);
        editor.commit();
    }

     public void procitajPodatke(){
         DatePicker inputDatumPolazka = (DatePicker) findViewById(R.id.inputDatumPolazka);
         DatePicker inputDatumPovratka = (DatePicker) findViewById(R.id.inputDatumPovratka);
         Spinner inputBrojOsoba = (Spinner) findViewById(R.id.inputBrojOsoba);

         Calendar c = Calendar.getInstance();
         SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_PREFIX,0);
         String odlazni = sharedPreferences.getString(SHARED_PREFERENCES_KEY_ODLAZNI, "");
         String dolazni = sharedPreferences.getString(SHARED_PREFERENCES_KEY_DOLAZNI, "");
         int datumPolaskaGodina = sharedPreferences.getInt(SHARED_PREFERENCES_KEY_DATUM_POLASKA_GODINA, c.get(Calendar.YEAR));
         int datumPolaskaMesec = sharedPreferences.getInt(SHARED_PREFERENCES_KEY_DATUM_POLASKA_MESEC, c.get(Calendar.MONTH));
         int datumPolaskaDan = sharedPreferences.getInt(SHARED_PREFERENCES_KEY_DATUM_POLASKA_DAN, c.get(Calendar.DAY_OF_MONTH));
         int datumPovratkaGodina = sharedPreferences.getInt(SHARED_PREFERENCES_KEY_DATUM_POVRATKA_GODINA, c.get(Calendar.YEAR));
         int datumPovratkaMesec = sharedPreferences.getInt(SHARED_PREFERENCES_KEY_DATUM_POVRATKA_MESEC, c.get(Calendar.MONTH));
         int datumPovratkaDan = sharedPreferences.getInt(SHARED_PREFERENCES_KEY_DATUM_POVRATKA_DAN, c.get(Calendar.DAY_OF_MONTH));
         int brojOsoba = sharedPreferences.getInt(SHARED_PREFERENCES_KEY_BROJ_OSOBA, 0);


         ((EditText) findViewById(R.id.inputOd)).setText(odlazni);
         ((EditText) findViewById(R.id.inputDo)).setText(dolazni);
         inputDatumPolazka.updateDate(datumPolaskaGodina, datumPolaskaMesec, datumPolaskaDan);
         inputDatumPovratka.updateDate(datumPovratkaGodina, datumPovratkaMesec, datumPovratkaDan);
         inputBrojOsoba.setSelection(brojOsoba);
     }
}