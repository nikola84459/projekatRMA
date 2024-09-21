package ac.rs.singidunum.aplikacija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class PotvrdiLetActivity extends AppCompatActivity implements View.OnClickListener {
    private String inputBrojOsoba, inputOd, inputDo, inputDatumPolaska, inputDatumPovratka, letIdOdlazak, brojLetaOdlazak, vremeOdlazak, letIdPovratak, brojLetaPovratak, vremePovratak, cenaPolazak, cenaPovratak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potvrdi_let);

        findViewById(R.id.dugmePotvrda).setOnClickListener(this);

        popuniTabele();
    }


    public void popuniTabele(){
        Bundle b = getIntent().getExtras();

        inputOd = b.getString("od");
        inputDo = b.getString("do");
        inputDatumPolaska = b.getString("datumPolaska");
        inputDatumPovratka = b.getString("datumPovratka");
        letIdOdlazak = b.getString("letIdOdlazak");
        brojLetaOdlazak = b.getString("brojLetaOdlazak");
        vremeOdlazak = b.getString("vremeOdlazak");
        letIdPovratak = b.getString("letIdPovratak");
        brojLetaPovratak = b.getString("brojLetaPovratak");
        vremePovratak = b.getString("vremePovratak");
        inputBrojOsoba = b.getString("brojOsoba");
        cenaPolazak = b.getString("cenaOdlazak");
        cenaPovratak = b.getString("cenaPovratak");

        ((TextView) findViewById(R.id.brojLetaPolazak)).setText(brojLetaOdlazak);
        ((TextView) findViewById(R.id.odPolazak)).setText(inputOd);
        ((TextView) findViewById(R.id.doPolazak)).setText(inputDo);
        ((TextView) findViewById(R.id.datumPolazak)).setText(inputDatumPolaska);
        ((TextView) findViewById(R.id.vremePolazak)).setText(vremeOdlazak);

        ((TextView) findViewById(R.id.brojLetaPovratak)).setText(brojLetaPovratak);
        ((TextView) findViewById(R.id.odPovratak)).setText(inputDo);
        ((TextView) findViewById(R.id.doPovratak)).setText(inputOd);
        ((TextView) findViewById(R.id.datumPovratak)).setText(inputDatumPovratka);
        ((TextView) findViewById(R.id.vremePovratak)).setText(vremePovratak);
        int ukupnaCena = (Integer.parseInt(cenaPolazak) + Integer.parseInt(cenaPovratak)) * Integer.parseInt(inputBrojOsoba);
        ((TextView) findViewById(R.id.cena)).setText("Ukupna cena: " + ukupnaCena + " RSD");
    }

    @Override
    public void onClick(View v) {
        otvoriNoviActivity();
    }

    public void otvoriNoviActivity(){
        Intent i = new Intent(this, PutniciActivity.class);
        Bundle b = new Bundle();

         b.putString("od", inputOd);
         b.putString("do", inputDo);
         b.putString("datumPolaska", inputDatumPolaska);
         b.putString("datumPovratka", inputDatumPovratka);
         b.putString("letIdOdlazak", letIdOdlazak);
         b.putString("brojLetaOdlazak", brojLetaOdlazak);
         b.putString("vremeOdlazak", vremeOdlazak);
         b.putString("letIdPovratak", letIdPovratak);
         b.putString("brojLetaPovratak", brojLetaPovratak);
         b.putString("vremePovratak", vremePovratak);
         b.putString("brojOsoba", inputBrojOsoba);

         i.putExtras(b);
         startActivity(i);
    }
}