package ac.rs.singidunum.aplikacija;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BankaActivity extends AppCompatActivity implements View.OnClickListener {
    private String inputOd, inputDo, inputDatumPolaska, inputDatumPovratka, letIdOdlazak, brojLetaOdlazak, vremeOdlazak, letIdPovratak, brojLetaPovratak, vremePovratak, putnici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banka);


        findViewById(R.id.dugmePosalji).setOnClickListener(this);

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
        putnici = new JSONObject((Map) b.getSerializable("putnici")).toString();
    }

    @SuppressLint("WrongViewCast")
    public void kontaktirajApi(){
        String brojKartice = ((EditText) findViewById(R.id.brojKartice)).getText().toString();
        String datumIstekaKartice = ((EditText) findViewById(R.id.datumIstekaKartice)).getText().toString();
        String cvvKod = ((EditText) findViewById(R.id.cvvKod)).getText().toString();

        List<Api.Element> podaci = new ArrayList<>();
        podaci.add(new Api.Element("letIdOdlazak", letIdOdlazak));
        podaci.add(new Api.Element("letIdPovratak", letIdPovratak));
        podaci.add(new Api.Element("putnici", putnici));
        podaci.add(new Api.Element("brojKartice", brojKartice));
        podaci.add(new Api.Element("datumIstekaKartice", datumIstekaKartice));
        podaci.add(new Api.Element("cvvKod", cvvKod));

        Api.postDataJson("http://192.168.1.3/unosLeta/index.php", podaci, new ReadDataHandler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(@NonNull Message msg) {
                String odgovor = getJson();

                try {
                    JSONObject json = new JSONObject(odgovor);
                    PorukaModel p = PorukaModel.parseJSONObject(json);

                    if(Boolean.parseBoolean(p.getIsImaNovca()) == false) {
                        ((TextView) findViewById(R.id.poruka)).setText("Nemate dovoljno novca na računu za ovu transakciju");
                    } else if(Boolean.parseBoolean(p.getIsUnetiPodaci()) == false) {
                        ((TextView) findViewById(R.id.poruka)).setText("Unos podataka nije uspeo");
                    } else {
                        otvoriNovuAktivnost(p.getBrojTransakcije(), p.getBrojRezervacije());
                    }

                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        kontaktirajApi();
    }

    public void otvoriNovuAktivnost(String brojTransakcije, String brojRezervacije){
        Intent i = new Intent(this, PrikazRezervacijeActivity.class);
        Bundle b = new Bundle();

        b.putString("od", inputOd);
        b.putString("do", inputDo);
        b.putString("datumPolaska", inputDatumPolaska);
        b.putString("datumPovratka", inputDatumPovratka);
        b.putString("brojLetaOdlazak", brojLetaOdlazak);
        b.putString("vremeOdlazak", vremeOdlazak);
        b.putString("brojLetaPovratak", brojLetaPovratak);
        b.putString("vremePovratak", vremePovratak);
        b.putString("brojTransakcije", brojTransakcije);
        b.putString("brojRezervacije", brojRezervacije);

        Baza db = new Baza(this);
        db.addRezervacija(inputOd, inputDo, inputDatumPolaska, inputDatumPovratka, brojLetaOdlazak, brojLetaPovratak, vremeOdlazak, vremePovratak, brojRezervacije);

        i.putExtras(b);
        startActivity(i);
    }
}