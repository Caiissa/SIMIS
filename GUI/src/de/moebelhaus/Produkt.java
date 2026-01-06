package de.moebelhaus;

public class Produkt {

    private int id;
    private String name;
    private int bestand;

    public Produkt(int id, String name, int bestand) {
        this.id = id;
        this.name = name;
        this.bestand = bestand;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBestand() {
        return bestand;
    }
}
