package de.moebelhaus.produkte;

import de.moebelhaus.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduktvarianteDAO {

    public static List<Produktvariante> getVariantenByProdukt(int produktId) {

        List<Produktvariante> varianten = new ArrayList<>();

        String sql = """
            SELECT pv.VARIANTEID,
                   f.BEZEICHNUNG AS FARBE,
                   g.BESCHREIBUNG AS GROESSE,
                   m.BEZEICHNUNG AS MATERIAL,
                   pv.PREIS,
                   pv.VERFUEGBAR
            FROM PRODUKTVARIANTE pv
            JOIN FARBE f ON pv.FARBENID = f.FARBENID
            JOIN GROE_E g ON pv.GROESSEID = g.GROESSEID
            JOIN MATERIAL m ON pv.MATERIALID = m.MATERIALID
            WHERE pv.PRODUKTID = ?
            ORDER BY pv.VARIANTEID
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, produktId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                varianten.add(new Produktvariante(
                        rs.getInt("VARIANTEID"),
                        rs.getString("FARBE"),
                        rs.getString("GROESSE"),
                        rs.getString("MATERIAL"),
                        rs.getDouble("PREIS"),
                        rs.getString("VERFUEGBAR").equals("Y")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return varianten;
    }
}
