package ac.rs.singidunum.aplikacija;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.LinkedList;

public class PrikazLetaActivity extends AppCompatActivity implements OdlazniLetFragment.OnFragmentInteractionListener, PovratniLetFragment.OnFragmentInteractionListener {
   private String inputBrojOsoba, inputOd, inputDo, inputDatumPolaska, inputDatumPovratka, letIdOdlazak, brojLetaOdlazak, vremeOdlazak, cenaPolazak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_leta);

        if(savedInstanceState != null) {
            letIdOdlazak = savedInstanceState.getString("letIdOdlazak");
            brojLetaOdlazak = savedInstanceState.getString("brojLetaOdlazak");
            vremeOdlazak = savedInstanceState.getString("vremeOdlazak");
            cenaPolazak = savedInstanceState.getString("cenaOdlazak");
        }

        pretraziLetove();
    }

    public void pretraziLetove() {
        Bundle b = getIntent().getExtras();

        inputOd = b.getString("inputOd");
        inputDo = b.getString("inputDo");
        inputDatumPolaska = b.getString("inputDatumPolazka");
        inputDatumPovratka = b.getString("inputDatumPovratka");
        inputBrojOsoba = b.getString("inputBrojOsoba");

        Fragment povratniLet = getSupportFragmentManager().findFragmentByTag("fragmentPovratni");
        Fragment odlazniLet = getSupportFragmentManager().findFragmentByTag("fragmentOdlazni");
        if(povratniLet == null) {
            if(odlazniLet == null) {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment, OdlazniLetFragment.newInstance(inputOd, inputDo, inputDatumPolaska), "fragmentOdlazni").commit();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("letIdOdlazak", letIdOdlazak);
        outState.putString("brojLetaOdlazak", brojLetaOdlazak);
        outState.putString("vremeOdlazak", vremeOdlazak);
        outState.putString("cenaOdlazak", cenaPolazak);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void odabranOdlazniLet(String odlazni, String odrediste, String datumPovratka, String letId, String brojLeta, String vreme, String cena) {
        brojLetaOdlazak = brojLeta;
        letIdOdlazak = letId;
        vremeOdlazak = vreme;
        cenaPolazak = cena;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, PovratniLetFragment.newInstance(odlazni, odrediste, inputDatumPovratka),
                                                            "fragmentPovratni").addToBackStack(null).commit();
    }

    @Override
    public void odabranLetPovratak(String letId, String brojLeta, String vreme, String cena) {
        Intent i = new Intent(this, PotvrdiLetActivity.class);
        Bundle b = new Bundle();

        b.putString("od", inputOd);
        b.putString("do", inputDo);
        b.putString("datumPolaska", inputDatumPolaska);
        b.putString("datumPovratka", inputDatumPovratka);
        b.putString("letIdOdlazak", letIdOdlazak);
        b.putString("brojLetaOdlazak", brojLetaOdlazak);
        b.putString("vremeOdlazak", vremeOdlazak);
        b.putString("letIdPovratak", letId);
        b.putString("brojLetaPovratak", brojLeta);
        b.putString("vremePovratak", vreme);
        b.putString("brojOsoba", inputBrojOsoba);
        b.putString("cenaPovratak", cena);
        b.putString("cenaOdlazak", cenaPolazak);

        i.putExtras(b);
        startActivity(i);
    }
}