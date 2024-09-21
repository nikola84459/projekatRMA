package ac.rs.singidunum.aplikacija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PrikazRezervacijeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_rezervacije);

        findViewById(R.id.dugmePovratak).setOnClickListener(this);


        prikazRezervacije();
    }

    public void prikazRezervacije(){
        Bundle b = getIntent().getExtras();

        String inputOd = b.getString("od");
        String inputDo = b.getString("do");
        String inputDatumPolaska = b.getString("datumPolaska");
        String inputDatumPovratka = b.getString("datumPovratka");
        String brojLetaOdlazak = b.getString("brojLetaOdlazak");
        String vremeOdlazak = b.getString("vremeOdlazak");
        String brojLetaPovratak = b.getString("brojLetaPovratak");
        String vremePovratak = b.getString("vremePovratak");
        String brojTransakcije = b.getString("brojTransakcije");
        String brojRezervacije = b.getString("brojRezervacije");

        ((TextView) findViewById(R.id.tekstPoruka)).setText("Broj rezervacije: " + brojRezervacije);
        ((TextView) findViewById(R.id.brojTransakcije)).setText("Broj transakcije: " + brojTransakcije);
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

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}