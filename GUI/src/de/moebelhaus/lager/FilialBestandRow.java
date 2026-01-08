package de.moebelhaus.lager;

public class FilialBestandRow {

    private final int filialeId;
    private final String filialName;
    private final int bestand;

    public FilialBestandRow(int filialeId, String filialName, int bestand) {
        this.filialeId = filialeId;
        this.filialName = filialName;
        this.bestand = bestand;
    }

    public int getFilialeId() { return filialeId; }
    public String getFilialName() { return filialName; }
    public int getBestand() { return bestand; }
}
