package de.moebelhaus.produkte;

public class Produkt {

    private int produktId;
    private String name;
    private double basispreis;
    private boolean aktiv;

    public Produkt(int produktId, String name, double basispreis, boolean aktiv) {
        this.produktId = produktId;
        this.name = name;
        this.basispreis = basispreis;
        this.aktiv = aktiv;
    }

    public int getProduktId() {
        return produktId;
    }

    public String getName() {
        return name;
    }

    public double getBasispreis() {
        return basispreis;
    }

    public boolean isAktiv() {
        return aktiv;
    }
}
