package de.moebelhaus.filiale;

import de.moebelhaus.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilialeDAO {

    public static List<Filiale> searchFilialen(String suchtext) {

        List<Filiale> filialen = new ArrayList<>();

        String sql = """
            SELECT FILIALEID, NAME, ADRESSE, PLZ, ORT,
                   TELEFON, OEFFNUNGSZEITEN, MENGE_VERFUEGBAR
            FROM FILIALE
            WHERE LOWER(NAME) LIKE ?
               OR LOWER(ORT) LIKE ?
            ORDER BY FILIALEID
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + suchtext.toLowerCase() + "%");
            ps.setString(2, "%" + suchtext.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                filialen.add(new Filiale(
                        rs.getInt("FILIALEID"),
                        rs.getString("NAME"),
                        rs.getString("ADRESSE"),
                        rs.getString("PLZ"),
                        rs.getString("ORT"),
                        rs.getString("TELEFON"),
                        rs.getString("OEFFNUNGSZEITEN"),
                        rs.getInt("MENGE_VERFUEGBAR")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filialen;
    }
}
