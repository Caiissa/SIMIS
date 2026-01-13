package de.moebelhaus.bestellung;

import java.time.LocalDate;

public class Bestellung {

    private int bestellungID;
    private int filialeID;
    private LocalDate datumBestellung;
    private int menge;
    private String status;

    public Bestellung(int bestellungID, int filialeID,
                      LocalDate datumBestellung, int menge, String status) {
        this.bestellungID = bestellungID;
        this.filialeID = filialeID;
        this.datumBestellung = datumBestellung;
        this.menge = menge;
        this.status = status;
    }

    public int getBestellungID() {
        return bestellungID;
    }

    public int getFilialeID() {
        return filialeID;
    }

    public LocalDate getDatumBestellung() {
        return datumBestellung;
    }

    public int getMenge() {
        return menge;
    }

    public String getStatus() {
        return status;
    }
}
