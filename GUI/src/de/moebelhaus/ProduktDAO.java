package de.moebelhaus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduktDAO {

    public static List<Produkt> getAlleProdukte() {

        List<Produkt> produkte = new ArrayList<>();

        String sql = "SELECT id, name, bestand FROM produkte";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                produkte.add(
                        new Produkt(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("bestand")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produkte;
    }
}
