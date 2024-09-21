package ac.rs.singidunum.aplikacija;

import org.json.JSONObject;

public class PorukaModel {
    private String isImaNovca, isUnetiPodaci, brojRezervacije, brojTransakcije;

    public PorukaModel(String isImaNovca, String isUnetiPodaci, String brojRezervacije, String brojTransakcije) {
        this.isImaNovca = isImaNovca;
        this.isUnetiPodaci = isUnetiPodaci;
        this.brojRezervacije = brojRezervacije;
        this.brojTransakcije = brojTransakcije;
    }

    public PorukaModel() {
    }

    public String getIsImaNovca() {
        return isImaNovca;
    }

    public void setIsImaNovca(String isImaNovca) {
        this.isImaNovca = isImaNovca;
    }

    public String getIsUnetiPodaci() {
        return isUnetiPodaci;
    }

    public void setIsUnetiPodaci(String isUnetiPodaci) {
        this.isUnetiPodaci = isUnetiPodaci;
    }

    public String getBrojRezervacije() {
        return brojRezervacije;
    }

    public void setBrojRezervacije(String brojRezervacije) {
        this.brojRezervacije = brojRezervacije;
    }

    public String getBrojTransakcije() {
        return brojTransakcije;
    }

    public void setBrojTransakcije(String brojTransakcije) {
        this.brojTransakcije = brojTransakcije;
    }

    public static PorukaModel parseJSONObject(JSONObject object){
        PorukaModel poruka = new PorukaModel();
        try {
            if(object.has("is_ima_novca")){
                poruka.setIsImaNovca(object.getString("is_ima_novca"));
            }

            if(object.has("is_uneti_podaci")){
                poruka.setIsUnetiPodaci(object.getString("is_uneti_podaci"));
            }

            if(object.has("broj_rezervacije")){
                poruka.setBrojRezervacije(object.getString("broj_rezervacije"));
            }

            if (object.has("broj_transakcije")){
                poruka.setBrojTransakcije(object.getString("broj_transakcije"));
            }

        } catch (Exception e){
            e.getMessage();
        }

        return poruka;
    }
}
