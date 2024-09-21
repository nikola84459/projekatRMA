package ac.rs.singidunum.aplikacija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CekiranjeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cekiranje);


        findViewById(R.id.dugmePretraga).setOnClickListener(this);
    }

    public void unosPodataka(){
        String inputIme = ((EditText) findViewById(R.id.inputIme)).getText().toString();
        String inputPrezime = ((EditText) findViewById(R.id.inputPrezime)).getText().toString();
        String inputBrRezervacije = ((EditText) findViewById(R.id.inputBrRezervacije)).getText().toString();

        if(!inputIme.isEmpty() && !inputPrezime.isEmpty() && !inputBrRezervacije.isEmpty()) {
            Intent i = new Intent(this, LetZaCekiranjeActivity.class);
            Bundle b = new Bundle();

            b.putString("ime", inputIme);
            b.putString("prezime", inputPrezime);
            b.putString("brRezervacije", inputBrRezervacije);

            i.putExtras(b);
            startActivity(i);
        } else {
            ((TextView) findViewById(R.id.tekstPoruka)).setText("Morate popuniti sva polja.");
        }
    }

    @Override
    public void onClick(View v) {
        unosPodataka();
    }
}