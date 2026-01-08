package de.moebelhaus.produkte;

import de.moebelhaus.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduktDAO {

    public static List<Produkt> getAlleProdukte() {

        List<Produkt> produkte = new ArrayList<>();

        String sql = """
            SELECT PRODUKTID, NAME, BASISPREIS
            FROM PRODUKT
            WHERE AKTIV = 'Y'
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                produkte.add(
                        new Produkt(
                                rs.getInt("PRODUKTID"),
                                rs.getString("NAME"),
                                rs.getDouble("BASISPREIS")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produkte;
    }
}
