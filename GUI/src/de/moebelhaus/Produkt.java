package de.moebelhaus;

public class Produkt {

    private int id;
    private String vorname;
    private String nachname;

    public Produkt(int id, String vorname, String nachname) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return vorname;
    }

    public String getBestand() {
        return nachname;
    }
}
