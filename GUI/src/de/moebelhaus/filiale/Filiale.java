package de.moebelhaus.filiale;

public class Filiale {

    private int filialeID;
    private String name;
    private String adresse;
    private String plz;
    private String ort;
    private String telefon;
    private String oeffnungszeiten;
    private int mengeVerfuegbar;

    public Filiale(int filialeID, String name, String adresse,
                   String plz, String ort, String telefon,
                   String oeffnungszeiten, int mengeVerfuegbar) {
        this.filialeID = filialeID;
        this.name = name;
        this.adresse = adresse;
        this.plz = plz;
        this.ort = ort;
        this.telefon = telefon;
        this.oeffnungszeiten = oeffnungszeiten;
        this.mengeVerfuegbar = mengeVerfuegbar;
    }

    public int getFilialeID() {
        return filialeID;
    }

    public String getName() {
        return name;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getPlz() {
        return plz;
    }

    public String getOrt() {
        return ort;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getOeffnungszeiten() {
        return oeffnungszeiten;
    }

    public int getMengeVerfuegbar() {
        return mengeVerfuegbar;
    }
}
