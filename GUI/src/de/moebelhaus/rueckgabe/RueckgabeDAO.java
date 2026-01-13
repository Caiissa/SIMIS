package de.moebelhaus.rueckgabe;

import de.moebelhaus.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RueckgabeDAO {

    public static List<Rueckgabe> searchRueckgaben(String suchtext) {

        List<Rueckgabe> rueckgaben = new ArrayList<>();

        String sql = """
            SELECT REUCKGABEID, LAGERID, VERKAUFID, KUNDEID,
                   DATUM, GRUND, BETRAG_ERSTATTUNG
            FROM RUECKGABE
            WHERE CAST(REUCKGABEID AS CHAR) LIKE ?
               OR LOWER(GRUND) LIKE ?
            ORDER BY DATUM DESC
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + suchtext + "%");
            ps.setString(2, "%" + suchtext.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rueckgaben.add(new Rueckgabe(
                        rs.getInt("REUCKGABEID"),
                        rs.getBoolean("LAGERID"),
                        rs.getBoolean("VERKAUFID"),
                        rs.getInt("KUNDEID"),
                        rs.getDate("DATUM").toLocalDate(),
                        rs.getString("GRUND"),
                        rs.getDouble("BETRAG_ERSTATTUNG")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rueckgaben;
    }
}
