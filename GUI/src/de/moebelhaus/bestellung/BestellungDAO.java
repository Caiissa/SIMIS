package de.moebelhaus.bestellung;

import de.moebelhaus.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BestellungDAO {

    public static List<Bestellung> searchBestellungen(String suchtext) {

        List<Bestellung> bestellungen = new ArrayList<>();

        String sql = """
            SELECT BESTELLUNGID, FILIALEID, DATUM_BESTELLUNG, MENGE, STATUS
            FROM BESTELLUNG
            WHERE CAST(BESTELLUNGID AS CHAR) LIKE ?
               OR LOWER(STATUS) LIKE ?
            ORDER BY DATUM_BESTELLUNG DESC
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + suchtext + "%");
            ps.setString(2, "%" + suchtext.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bestellungen.add(new Bestellung(
                        rs.getInt("BESTELLUNGID"),
                        rs.getInt("FILIALEID"),
                        rs.getDate("DATUM_BESTELLUNG").toLocalDate(),
                        rs.getInt("MENGE"),
                        rs.getString("STATUS")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bestellungen;
    }
}

