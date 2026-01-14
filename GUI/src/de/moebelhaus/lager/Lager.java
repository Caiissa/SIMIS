package de.moebelhaus.lager;

public class Lager {

    private int lagerID;
    private int bestellungID;
    private int mengeVerfuegbar;

    public Lager(int lagerID, int bestellungID, int mengeVerfuegbar) {
        this.lagerID = lagerID;
        this.bestellungID = bestellungID;
        this.mengeVerfuegbar = mengeVerfuegbar;
    }

    public int getLagerID() {
        return lagerID;
    }

    public void setLagerID(int lagerID) {
        this.lagerID = lagerID;
    }

    public int getBestellungID() {
        return bestellungID;
    }

    public void setBestellungID(int bestellungID) {
        this.bestellungID = bestellungID;
    }

    public int getMengeVerfuegbar() {
        return mengeVerfuegbar;
    }

    public void setMengeVerfuegbar(int mengeVerfuegbar) {
        this.mengeVerfuegbar = mengeVerfuegbar;
    }
}
