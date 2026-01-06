package de.moebelhaus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduktDAO {

    public static List<Produkt> getAlleProdukte() {

        List<Produkt> produkte = new ArrayList<>();

        String sql = "SELECT KUNDEID, VORNAME, NACHNAME FROM KUNDE";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                produkte.add(
                        new Produkt(
                                rs.getInt("KUNDEID"),
                                rs.getString("VORNAME"),
                                rs.getString("NACHNAME")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produkte;
    }
}
