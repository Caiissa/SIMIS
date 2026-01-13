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

    public int getBestellungID() {
        return bestellungID;
    }

    public int getMengeVerfuegbar() {
        return mengeVerfuegbar;
    }
}
