package ac.rs.singidunum.aplikacija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PutniciActivity extends AppCompatActivity implements View.OnClickListener {
    private String inputBrojOsoba, inputOd, inputDo, inputDatumPolaska, inputDatumPovratka, letIdOdlazak, brojLetaOdlazak, vremeOdlazak, letIdPovratak, brojLetaPovratak, vremePovratak;
    LinearLayout formaPutnici;
    Map<String, String[]> putnici = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_putnici);

        formaPutnici();
    }

    public void formaPutnici(){
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

        formaPutnici = (LinearLayout) findViewById(R.id.formaPutnici);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(int i = 1; i <= Integer.parseInt(inputBrojOsoba); i++){
            LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.putnici_layout, null);
            ((TextView) layout.findViewById(R.id.brojPutnika)).setText("Putnik " + i);
            ((EditText) layout.findViewById(R.id.inputIme)).setTag("inputImePutnik" + i);
            ((EditText) layout.findViewById(R.id.inputPrezime)).setTag("inputPrezimePutnik" + i);
            ((EditText) layout.findViewById(R.id.inputBrPasosa)).setTag("inputBrPasosaPutnik" + i);

            formaPutnici.addView(layout);
        }

        LinearLayout layout2 = (LinearLayout) inflater.inflate(R.layout.dugme_layout, null);
        layout2.setGravity(Gravity.RIGHT);
        ((Button) layout2.findViewById(R.id.dugmePotvrdi)).setOnClickListener(this);

        formaPutnici.addView(layout2);

    }

    @Override
    public void onClick(View v) {
        unosPutnici();
    }

    public void unosPutnici(){
        boolean isUnetiPodaci = false;

        for(int i = 1; i <= Integer.parseInt(inputBrojOsoba); i++){
            String putnikIme = ((EditText) formaPutnici.findViewWithTag("inputImePutnik" + i)).getText().toString();
            String putnikPrezime = ((EditText) formaPutnici.findViewWithTag("inputPrezimePutnik" + i)).getText().toString();
            String putnikBrPasosa = ((EditText) formaPutnici.findViewWithTag("inputBrPasosaPutnik" + i)).getText().toString();

            if(!putnikIme.isEmpty() && !putnikPrezime.isEmpty() && !putnikBrPasosa.isEmpty()) {
                isUnetiPodaci = true;
                putnici.put("putnik" + i, new String[] {putnikIme, putnikPrezime, putnikBrPasosa});
            } else {
                isUnetiPodaci = false;
            }

        }
        if (isUnetiPodaci) {
            otvoriNovuAktivnost();
        } else {
            ((TextView) findViewById(R.id.tekstPoruka)).setText("Morate popuniti sva polja.");
        }
    }

    public void otvoriNovuAktivnost(){
        Intent i = new Intent(this, BankaActivity.class);
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
        b.putSerializable("putnici", (Serializable) putnici);

        i.putExtras(b);
        startActivity(i);
    }
}