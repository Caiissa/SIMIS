package de.moebelhaus.rabatt;

import de.moebelhaus.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RabattDAO {

    public static List<Rabatt> searchRabatte(String suchtext) {

        List<Rabatt> rabatte = new ArrayList<>();

        String sql = """
            SELECT AKTIONID, RABATT_PROZENT, START_DATUM, END_DATUM, BEZEICHNUG
            FROM RABATT
            WHERE CAST(AKTIONID AS CHAR) LIKE ?
               OR LOWER(BEZEICHNUG) LIKE ?
            ORDER BY START_DATUM ASC
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + suchtext + "%");
            ps.setString(2, "%" + suchtext.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rabatte.add(new Rabatt(
                        rs.getInt("AKTIONID"),
                        rs.getDouble("RABATT_PROZENT"),
                        rs.getString("START_DATUM"),
                        rs.getString("END_DATUM"),
                        rs.getString("BEZEICHNUG")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rabatte;
    }
}
