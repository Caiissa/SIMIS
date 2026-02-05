package de.moebelhaus.rueckgabe;

import java.time.LocalDate;

public class Rueckgabe {

    private int rueckgabeID;
    private boolean lagernd;
    private boolean verkaufen;
    private int kundeID;
    private LocalDate datum;
    private String grund;
    private double betragErstattung;

    public Rueckgabe(int rueckgabeID, boolean lagernd, boolean verkaufen,
                     int kundeID, LocalDate datum,
                     String grund, double betragErstattung) {
        this.rueckgabeID = rueckgabeID;
        this.lagernd = lagernd;
        this.verkaufen = verkaufen;
        this.kundeID = kundeID;
        this.datum = datum;
        this.grund = grund;
        this.betragErstattung = betragErstattung;
    }

    public int getRueckgabeID() {
        return rueckgabeID;
    }

    public boolean isLagernd() {
        return lagernd;
    }

    public boolean isVerkaufen() {
        return verkaufen;
    }

    public int getKundeID() {
        return kundeID;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public String getGrund() {
        return grund;
    }

    public double getBetragErstattung() {
        return betragErstattung;
    }
}
