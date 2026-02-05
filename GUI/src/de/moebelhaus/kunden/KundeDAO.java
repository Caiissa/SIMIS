package de.moebelhaus.kunden;

import de.moebelhaus.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KundeDAO {

    public static List<Kunde> searchKunden(String suchtext) {

        List<Kunde> kunden = new ArrayList<>();

        String sql = """
            SELECT KUNDEID, VORNAME, NACHNAME, EMAIL
            FROM KUNDE
            WHERE LOWER(VORNAME) LIKE ?
               OR LOWER(NACHNAME) LIKE ?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String value = "%" + suchtext.toLowerCase() + "%";
            ps.setString(1, value);
            ps.setString(2, value);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                kunden.add(new Kunde(
                        rs.getInt("KUNDEID"),
                        rs.getString("VORNAME"),
                        rs.getString("NACHNAME"),
                        rs.getString("EMAIL")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kunden;
    }
}
