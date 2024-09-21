package ac.rs.singidunum.aplikacija;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

public class LetModel {
    private String id,brojLeta, polazak, dolazak, datum, vreme, cena, podatak;


    public LetModel() {
    }

    public LetModel(String id, String brojLeta, String polazak, String dolazak, String datum, String vreme) {
        this.brojLeta = brojLeta;
        this.polazak = polazak;
        this.dolazak = dolazak;
        this.datum = datum;
        this.vreme = vreme;
        this.id = id;
    }
    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getBrojLeta() {
        return brojLeta;
    }

    public void setBrojLeta(String brojLeta) {
        this.brojLeta = brojLeta;
    }

    public String getPolazak() {
        return polazak;
    }

    public void setPolazak(String polazak) {
        this.polazak = polazak;
    }

    public String getDolazak() {
        return dolazak;
    }

    public void setDolazak(String dolazak) {
        this.dolazak = dolazak;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }
    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }


    public static LetModel parseJSONObject(JSONObject object){
        LetModel let = new LetModel();
        try {
            if(object.has("broj_leta")){
                let.setBrojLeta(object.getString("broj_leta"));
            }

            if(object.has("od")){
                let.setPolazak(object.getString("od"));
            }

            if(object.has("do")){
                let.setDolazak(object.getString("do"));
            }

            if (object.has("datum")){
                let.setDatum(object.getString("datum"));
            }

            if(object.has("vreme")){
                let.setVreme(object.getString("vreme"));
            }

            if(object.has("let_id")){
                let.setId(object.getString("let_id"));
            }

            if(object.has("cena")){
                let.setCena(object.getString("cena"));
            }



        } catch (Exception e){
            e.getMessage();
        }

        return let;
    }

    public static LinkedList<LetModel> parseJSONArray(JSONArray niz){
        LinkedList<LetModel> let = new LinkedList<>();

        try{
           for (int i = 0; i < niz.length(); i++) {
                LetModel l = parseJSONObject(niz.getJSONObject(i));
                let.add(l);
           }
        } catch (Exception e){
            e.getMessage();
        }

        return let;
    }
}
