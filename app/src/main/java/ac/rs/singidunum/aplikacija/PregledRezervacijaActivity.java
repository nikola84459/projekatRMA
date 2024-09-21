package ac.rs.singidunum.aplikacija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class PregledRezervacijaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled_rezervacija);

        prikaziRezervacije();
    }

    public void prikaziRezervacije() {
        Baza db = new Baza(this);
        List<RezervacijaModel> rezervacije = db.getAllRezervacije();

        LinearLayout rezervacijeLayout = (LinearLayout) findViewById(R.id.tabelaRezervacije);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(RezervacijaModel rezervacija : rezervacije) {
            LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.rezervacije_layout, null);
            ((TextView) layout.findViewById(R.id.tekstPoruka)).setText("Broj rezervacije: " +rezervacija.getBrojRezervacije());
            ((TextView) layout.findViewById(R.id.odPolazak)).setText(rezervacija.getOdlazni());
            ((TextView) layout.findViewById(R.id.doPolazak)).setText(rezervacija.getDolazni());
            ((TextView) layout.findViewById(R.id.odPovratak)).setText(rezervacija.getDolazni());
            ((TextView) layout.findViewById(R.id.doPovratak)).setText(rezervacija.getOdlazni());
            ((TextView) layout.findViewById(R.id.brojLetaPolazak)).setText(rezervacija.getBrojLetaOdlazak());
            ((TextView) layout.findViewById(R.id.brojLetaPovratak)).setText(rezervacija.getBrojLetaPovratak());
            ((TextView) layout.findViewById(R.id.datumPolazak)).setText(rezervacija.getDatumPolaska());
            ((TextView) layout.findViewById(R.id.datumPovratak)).setText(rezervacija.getDatumPovratka());
            ((TextView) layout.findViewById(R.id.vremePolazak)).setText(rezervacija.getVremeOdlazak());
            ((TextView) layout.findViewById(R.id.vremePovratak)).setText(rezervacija.getVremePovratak());

            layout.setPadding(0, 60, 0, 0);
            rezervacijeLayout.addView(layout);
        }
    }
}