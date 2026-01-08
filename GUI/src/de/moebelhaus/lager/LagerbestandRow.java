package de.moebelhaus.lager;

public class LagerbestandRow {

    private final int varianteId;
    private final String produktName;
    private final String farbe;
    private final String groesse;
    private final String material;
    private final int bestandTotal;

    public LagerbestandRow(int varianteId, String produktName, String farbe,
                           String groesse, String material, int bestandTotal) {
        this.varianteId = varianteId;
        this.produktName = produktName;
        this.farbe = farbe;
        this.groesse = groesse;
        this.material = material;
        this.bestandTotal = bestandTotal;
    }

    public int getVarianteId() { return varianteId; }
    public String getProduktName() { return produktName; }
    public String getFarbe() { return farbe; }
    public String getGroesse() { return groesse; }
    public String getMaterial() { return material; }
    public int getBestandTotal() { return bestandTotal; }
}
