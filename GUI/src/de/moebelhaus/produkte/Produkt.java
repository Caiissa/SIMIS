package de.moebelhaus.produkte;

public class Produkt {

    private int produktId;
    private String name;
    private double basispreis;

    public Produkt(int produktId, String name, double basispreis) {
        this.produktId = produktId;
        this.name = name;
        this.basispreis = basispreis;
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
}
