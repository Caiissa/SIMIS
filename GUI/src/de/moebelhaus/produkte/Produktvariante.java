package de.moebelhaus.produkte;

public class Produktvariante {

    private int varianteId;
    private String farbe;
    private String groesse;
    private String material;
    private double preis;
    private boolean verfuegbar;

    public Produktvariante(int varianteId, String farbe, String groesse,
                           String material, double preis, boolean verfuegbar) {
        this.varianteId = varianteId;
        this.farbe = farbe;
        this.groesse = groesse;
        this.material = material;
        this.preis = preis;
        this.verfuegbar = verfuegbar;
    }

    public int getVarianteId() { return varianteId; }
    public String getFarbe() { return farbe; }
    public String getGroesse() { return groesse; }
    public String getMaterial() { return material; }
    public double getPreis() { return preis; }
    public boolean isVerfuegbar() { return verfuegbar; }
}
