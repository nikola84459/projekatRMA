package ac.rs.singidunum.aplikacija;

public class RezervacijaModel {

    public static final String IME_TABELE = "rezervacija";

    public static final String COLUMN_REZERVACIJA_ID = "rezervacija_id";
    public static final String COLUMN_ODLAZNI = "odlazni";
    public static final String COLUMN_DOLAZNI = "dolazni";
    public static final String COLUMN_DATUM_POLASKA = "datum_polaska";
    public static final String COLUMN_DATUM_POVRATKA = "datum_povratka";
    public static final String COLUMN_BROJ_LETA_ODLAZAK = "broj_leta_odlazak";
    public static final String COLUMN_BROJ_LETA_POVRATAK  = "broj_leta_povratak";
    public static final String COLUMN_VREME_ODLAZAK = "vreme_odlazak";
    public static final String COLUMN_VREME_POVRATAK = "vreme_povratak";
    public static final String COLUMN_BROJ_REZERVACIJE  = "broj_rezervacije";

    private String odlazni;
    private String dolazni;
    private String datumPolaska;
    private String datumPovratka;
    private int rezervacijeId;
    private String brojLetaOdlazak;
    private String vremeOdlazak;
    private String brojLetaPovratak;
    private String vremePovratak;
    private String brojRezervacije;

    public RezervacijaModel(int rezervacijeId, String odlazni, String dolazni, String datumPolaska, String datumPovratka, String brojLetaOdlazak, String vremeOdlazak, String brojLetaPovratak, String vremePovratak, String brojRezervacije) {
        this.odlazni = odlazni;
        this.dolazni = dolazni;
        this.datumPolaska = datumPolaska;
        this.datumPovratka = datumPovratka;
        this.rezervacijeId = rezervacijeId;
        this.brojLetaOdlazak = brojLetaOdlazak;
        this.vremeOdlazak = vremeOdlazak;
        this.brojLetaPovratak = brojLetaPovratak;
        this.vremePovratak = vremePovratak;
        this.brojRezervacije = brojRezervacije;
    }

    public String getOdlazni() {
        return odlazni;
    }

    public void setOdlazni(String odlazni) {
        this.odlazni = odlazni;
    }

    public String getDolazni() {
        return dolazni;
    }

    public void setDolazni(String dolazni) {
        this.dolazni = dolazni;
    }

    public String getDatumPolaska() {
        return datumPolaska;
    }

    public void setDatumPolaska(String datumPolaska) {
        this.datumPolaska = datumPolaska;
    }

    public String getDatumPovratka() {
        return datumPovratka;
    }

    public void setDatumPovratka(String datumPovratka) {
        this.datumPovratka = datumPovratka;
    }

    public int getRezervacijeId() {
        return rezervacijeId;
    }

    public void setRezervacijeId(int rezervacijeId) {
        this.rezervacijeId = rezervacijeId;
    }

    public String getBrojLetaOdlazak() {
        return brojLetaOdlazak;
    }

    public void setBrojLetaOdlazak(String brojLetaOdlazak) {
        this.brojLetaOdlazak = brojLetaOdlazak;
    }

    public String getVremeOdlazak() {
        return vremeOdlazak;
    }

    public void setVremeOdlazak(String vremeOdlazak) {
        this.vremeOdlazak = vremeOdlazak;
    }

    public String getBrojLetaPovratak() {
        return brojLetaPovratak;
    }

    public void setBrojLetaPovratak(String brojLetaPovratak) {
        this.brojLetaPovratak = brojLetaPovratak;
    }

    public String getVremePovratak() {
        return vremePovratak;
    }

    public void setVremePovratak(String vremePovratak) {
        this.vremePovratak = vremePovratak;
    }

    public String getBrojRezervacije() {
        return brojRezervacije;
    }

    public void setBrojRezervacije(String brojRezervacije) {
        this.brojRezervacije = brojRezervacije;
    }
}
