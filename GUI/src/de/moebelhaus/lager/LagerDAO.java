package de.moebelhaus.lager;

import de.moebelhaus.DBConnection;
import de.moebelhaus.produkte.Produkt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LagerDAO {

    public List<Lager> getAlleLager() {
        List<Lager> lagerListe = new ArrayList<>();

        String sql = """
            SELECT lagerID, bestellungID, menge_verfuegbar
            FROM lager
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Lager lager = new Lager(
                        rs.getInt("lagerID"),
                        rs.getInt("bestellungID"),
                        rs.getInt("menge_verfuegbar")
                );
                lagerListe.add(lager);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lagerListe;
    }

    public List<Produkt> getProdukteZuLager(int lagerID) {
        List<Produkt> produkte = new ArrayList<>();

        String sql = """
            SELECT p.produktID, p.name, p.basispreis, p.aktiv
            FROM produkt_lager pl
            JOIN produkt p ON p.produktID = pl.produktID
            WHERE pl.lagerID = ?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, lagerID);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Produkt produkt = new Produkt(
                            rs.getInt("produktID"),
                            rs.getString("name"),
                            rs.getDouble("basispreis"),
                            rs.getBoolean("aktiv")
                    );
                    produkte.add(produkt);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produkte;
    }
}
