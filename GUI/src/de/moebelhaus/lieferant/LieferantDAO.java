package de.moebelhaus.lieferant;

import de.moebelhaus.DBConnection;
import de.moebelhaus.produkte.Produkt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class LieferantDAO {

    public static List<Lieferant> searchLieferanten(String suchtext) {

        List<Lieferant> lieferanten = new ArrayList<>();

        String sql = """
            SELECT LIEFERANTID, NAME, EMAIL, ADRESSE, TELEFON, KONTAKTPERSON
            FROM LIEFERANT
            WHERE LOWER(NAME) LIKE ?
            ORDER BY LIEFERANTID
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + suchtext.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lieferanten.add(new Lieferant(
                        rs.getInt("LIEFERANTID"),
                        rs.getString("NAME"),
                        rs.getString("EMAIL"),
                        rs.getString("ADRESSE"),
                        rs.getString("TELEFON"),
                        rs.getString("KONTAKTPERSON")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lieferanten;
    }
}
