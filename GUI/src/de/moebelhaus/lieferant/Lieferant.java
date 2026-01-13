package de.moebelhaus.lieferant;

public class Lieferant {

    private int lieferantID;
    private String name;
    private String email;
    private String adresse;
    private String telNumber;
    private String kntPerson;

    public Lieferant(int lieferantID, String name, String email, String adresse, String telNumber, String kntPerson) {
        this.lieferantID = lieferantID;
        this.name = name;
        this.email = email;
        this.adresse = adresse;
        this.telNumber = telNumber;
        this.kntPerson = kntPerson;
    }

    public int getLieferantID() {
        return lieferantID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getKntPerson() {
        return kntPerson;
    }
}
