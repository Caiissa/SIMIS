package de.moebelhaus.produkte;

import de.moebelhaus.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduktDAO {

    public static List<Produkt> searchProdukte(String suchtext) {

        List<Produkt> produkte = new ArrayList<>();

        String sql = """
            SELECT PRODUKTID, NAME, BASISPREIS, AKTIV
            FROM PRODUKT
            WHERE LOWER(NAME) LIKE ?
            ORDER BY PRODUKTID
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + suchtext.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                produkte.add(new Produkt(
                        rs.getInt("PRODUKTID"),
                        rs.getString("NAME"),
                        rs.getDouble("BASISPREIS"),
                        rs.getString("AKTIV").equals("Y")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produkte;
    }
}
