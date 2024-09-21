package ac.rs.singidunum.aplikacija;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PovratniLetFragment extends Fragment {

    LinkedList<LetModel> letovi;

    private static final String ARG_ODLAZNI = "Odlazni";
    private static final String ARG_DO = "Dolazni";
     private static final String ARG_DATUM_POVRATKA = "Datum povratak";


    private String odlazni = "";
    private String odrediste = "";
    private String datumPovratka = "";

    private PovratniLetFragment.OnFragmentInteractionListener mListener;

    public PovratniLetFragment() {

    }

    public static PovratniLetFragment newInstance(String odlazni, String odrediste, String datumPovratka) {
        PovratniLetFragment fragment = new PovratniLetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ODLAZNI, odlazni);
        args.putString(ARG_DO, odrediste);
        args.putString(ARG_DATUM_POVRATKA, datumPovratka);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            odlazni = getArguments().getString(ARG_ODLAZNI);
            odrediste = getArguments().getString(ARG_DO);
            datumPovratka = getArguments().getString(ARG_DATUM_POVRATKA);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_povratni_let, container, false);

        LinearLayout tabela = (LinearLayout) v.findViewById(R.id.tabelaRezervacije);
        popuniTabelu(tabela, v);

        return v;
    }

    public void popuniTabelu(final LinearLayout tabelaLayout, final View v){
        List<Api.Element> podaci = new ArrayList<>();
        podaci.add(new Api.Element("od", odrediste));
        podaci.add(new Api.Element("do", odlazni));
        podaci.add(new Api.Element("datum", datumPovratka));

        Api.postDataJson("http://192.168.1.3/Let/index.php", podaci, new ReadDataHandler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(@NonNull Message msg) {
                String odgovor = getJson();
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                try {
                    JSONArray niz = new JSONArray(odgovor);
                    letovi = LetModel.parseJSONArray(niz);

                    if(!letovi.isEmpty()) {
                        for (LetModel l : letovi) {
                            TableLayout tabela = (TableLayout) inflater.inflate(R.layout.let_layout, null);
                            ((TextView) tabela.findViewById(R.id.brojLeta)).setText(l.getBrojLeta());
                            ((TextView) tabela.findViewById(R.id.od)).setText(l.getPolazak());
                            ((TextView) tabela.findViewById(R.id.odrediste)).setText(l.getDolazak());
                            ((TextView) tabela.findViewById(R.id.datum)).setText(l.getDatum());
                            ((TextView) tabela.findViewById(R.id.vreme)).setText(l.getVreme());
                            ((TextView) tabela.findViewById(R.id.cena)).setText(l.getCena() + " RSD");
                            ((Button) tabela.findViewById(R.id.dugmeRezervisi)).setTag(l.getId());
                            ((Button) tabela.findViewById(R.id.dugmeRezervisi)).setOnClickListener(dugmeRezervisi);

                            tabela.setPadding(0, 50, 0, 0);
                            tabelaLayout.addView(tabela);
                        }
                    } else {
                       ((TextView) v.findViewById(R.id.tekstPoruka)).setText("Nije pronadjen ni jedan povratni let za traženi datum.");

                    }
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PovratniLetFragment.OnFragmentInteractionListener) {
            mListener = (PovratniLetFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    private View.OnClickListener dugmeRezervisi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String letId = (String) v.getTag();

            if(mListener != null){
                for(LetModel l : letovi){
                    if(l.getId().equals(letId)){
                        mListener.odabranLetPovratak(l.getId(), l.getBrojLeta(), l.getVreme(), l.getCena());
                        break;
                    }
                }
            }
        }
    };

    public interface OnFragmentInteractionListener {
        void odabranLetPovratak(String letId, String brojLeta, String vreme, String cena);
    }
}