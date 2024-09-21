package ac.rs.singidunum.aplikacija;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LetZaCekiranjeActivity extends AppCompatActivity  {

    private String ime, prezime, brRezervacije;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_let_za_cekiranje);


        pretrazi();
    }

    public void pretrazi() {
        Bundle b = getIntent().getExtras();

        ime = b.getString("ime");
        prezime = b.getString("prezime");
        brRezervacije = b.getString("brRezervacije");

        List<Api.Element> podaci = new ArrayList<>();
        podaci.add(new Api.Element("ime", ime));
        podaci.add(new Api.Element("prezime", prezime));
        podaci.add(new Api.Element("brRezervacije", brRezervacije));

        Api.postDataJson("http://192.168.1.3/cekiranje/index.php", podaci, new ReadDataHandler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(@NonNull Message msg) {
                String odgovor = getJson();
                LinearLayout tabelaLayout = (LinearLayout) findViewById(R.id.tabelaRezervacije);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                try {
                    JSONArray niz = new JSONArray(odgovor);
                    LinkedList<LetModel> letovi = LetModel.parseJSONArray(niz);

                    if(!letovi.isEmpty()) {
                        for (LetModel l : letovi) {
                            TableLayout tabela = (TableLayout) inflater.inflate(R.layout.cekiranje_layout, null);
                            ((TextView) tabela.findViewById(R.id.brojLetaPolazak)).setText(l.getBrojLeta());
                            ((TextView) tabela.findViewById(R.id.odPolazak)).setText(l.getPolazak());
                            ((TextView) tabela.findViewById(R.id.doPolazak)).setText(l.getDolazak());
                            ((TextView) tabela.findViewById(R.id.datumPolazak)).setText(l.getDatum());
                            ((TextView) tabela.findViewById(R.id.vremePolazak)).setText(l.getVreme());
                            ((Button) tabela.findViewById(R.id.dugmeRezervisi)).setTag(l.getId());
                            ((Button) tabela.findViewById(R.id.dugmeRezervisi)).setOnClickListener(dugmeCekiraj);


                            tabela.setPadding(0, 50, 0, 0);
                            tabelaLayout.addView(tabela);

                        }
                    } else {
                        ((TextView) findViewById(R.id.poruka)).setText("Vaša rezervacija nije pronadjena.");
                    }
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });
    }

    public void cekiraj(String letId) {
        List<Api.Element> podaci = new ArrayList<>();
        podaci.add(new Api.Element("ime", ime));
        podaci.add(new Api.Element("prezime", prezime));
        podaci.add(new Api.Element("brRezervacije", brRezervacije));
        podaci.add(new Api.Element("letId", letId));

        Api.postDataJson("http://192.168.1.3/cekiranje/unosCekiranja.php", podaci, new ReadDataHandler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(@NonNull Message msg) {
                String odgovor = getJson();

                try {

                    JSONArray niz = new JSONArray(odgovor);
                    LinkedList<LetModel> letovi = LetModel.parseJSONArray(niz);

                    LinearLayout tabelaLayout = (LinearLayout) findViewById(R.id.tabelaRezervacije);
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    tabelaLayout.removeAllViews();

                    for (LetModel l : letovi) {
                        TableLayout tabela = (TableLayout) inflater.inflate(R.layout.cekiranje_layout, null);
                        ((TextView) tabela.findViewById(R.id.brojLetaPolazak)).setText(l.getBrojLeta());
                        ((TextView) tabela.findViewById(R.id.odPolazak)).setText(l.getPolazak());
                        ((TextView) tabela.findViewById(R.id.doPolazak)).setText(l.getDolazak());
                        ((TextView) tabela.findViewById(R.id.datumPolazak)).setText(l.getDatum());
                        ((TextView) tabela.findViewById(R.id.vremePolazak)).setText(l.getVreme());
                        ((Button) tabela.findViewById(R.id.dugmeRezervisi)).setTag(l.getId());
                        ((Button) tabela.findViewById(R.id.dugmeRezervisi)).setOnClickListener(dugmeCekiraj);

                        tabela.setPadding(0,50,0,0);
                        tabelaLayout.addView(tabela);
                    }

                    ((TextView) findViewById(R.id.poruka)).setText("Uspešno ste se čekirali na let.");
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });
    }

    private View.OnClickListener dugmeCekiraj = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String letId = (String) v.getTag();

            cekiraj(letId);
        }
    };
}