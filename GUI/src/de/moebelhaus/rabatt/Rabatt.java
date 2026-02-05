package de.moebelhaus.rabatt;

import java.time.LocalDate;

public class Rabatt {

    private int aktionID;
    private double rabattProzent;
    private String startDatum;
    private String endDatum;
    private String bezeichnung;

    public Rabatt(int aktionID, double rabattProzent,
                  String startDatum, String endDatum,
                  String bezeichnung) {
        this.aktionID = aktionID;
        this.rabattProzent = rabattProzent;
        this.startDatum = startDatum;
        this.endDatum = endDatum;
        this.bezeichnung = bezeichnung;
    }

    public int getAktionID() {
        return aktionID;
    }

    public double getRabattProzent() {
        return rabattProzent;
    }

    public String getStartDatum() {
        return startDatum;
    }

    public String getEndDatum() {
        return endDatum;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }
}
