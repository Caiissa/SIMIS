package de.moebelhaus.kunden;

public class Kunde {

    private int id;
    private String vorname;
    private String nachname;
    private String email;

    public Kunde(int id, String vorname, String nachname, String email) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
    }

    public int getId() { return id; }
    public String getVorname() { return vorname; }
    public String getNachname() { return nachname; }
    public String getEmail() { return email; }
}
