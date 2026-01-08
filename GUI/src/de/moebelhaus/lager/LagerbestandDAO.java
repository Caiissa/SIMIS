package de.moebelhaus.lager;

import de.moebelhaus.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LagerbestandDAO {

    /**
     * Liefert den Gesamtbestand je Produktvariante (über alle Filialen summiert),
     * optional gefiltert über Suchtext (Produktname/Farbe/Material/VarianteID).
     */
    public static List<LagerbestandRow> searchBestand(String suchtext) {

        List<LagerbestandRow> rows = new ArrayList<>();

        String sql = """
            SELECT
                pv.VARIANTEID,
                p.NAME AS PRODUKTNAME,
                f.BEZEICHNUNG AS FARBE,
                g.BESCHREIBUNG AS GROESSE,
                m.BEZEICHNUNG AS MATERIAL,
                NVL(SUM(l.BESTAND), 0) AS BESTAND_TOTAL
            FROM PRODUKTVARIANTE pv
            JOIN PRODUKT p ON pv.PRODUKTID = p.PRODUKTID
            JOIN FARBE f ON pv.FARBENID = f.FARBENID
            JOIN GROE_E g ON pv.GROESSEID = g.GROESSEID
            JOIN MATERIAL m ON pv.MATERIALID = m.MATERIALID
            LEFT JOIN LAGER l ON l.VARIANTEID = pv.VARIANTEID
            WHERE
                LOWER(p.NAME) LIKE ?
                OR LOWER(f.BEZEICHNUNG) LIKE ?
                OR LOWER(m.BEZEICHNUNG) LIKE ?
                OR TO_CHAR(pv.VARIANTEID) LIKE ?
            GROUP BY
                pv.VARIANTEID, p.NAME, f.BEZEICHNUNG, g.BESCHREIBUNG, m.BEZEICHNUNG
            ORDER BY p.NAME, pv.VARIANTEID
        """;

        String q = suchtext == null ? "" : suchtext.trim().toLowerCase();
        String like = "%" + q + "%";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            ps.setString(4, like);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rows.add(new LagerbestandRow(
                            rs.getInt("VARIANTEID"),
                            rs.getString("PRODUKTNAME"),
                            rs.getString("FARBE"),
                            rs.getString("GROESSE"),
                            rs.getString("MATERIAL"),
                            rs.getInt("BESTAND_TOTAL")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows;
    }

    /**
     * Liefert Bestände einer Variante je Filiale.
     *
     * ACHTUNG: Falls Ihre Tabelle/Feldnamen abweichen, passen Sie diese Query an:
     * - LAGER.BESTAND könnte z.B. MENGE heißen
     * - FILIALE.NAME könnte z.B. BEZEICHNUNG heißen
     */
    public static List<FilialBestandRow> getBestandJeFiliale(int varianteId) {

        List<FilialBestandRow> rows = new ArrayList<>();

        String sql = """
            SELECT
                fi.FILIALEID,
                fi.NAME AS FILIALNAME,
                NVL(l.BESTAND, 0) AS BESTAND
            FROM LAGER l
            JOIN FILIALE fi ON fi.FILIALEID = l.FILIALEID
            WHERE l.VARIANTEID = ?
            ORDER BY fi.FILIALEID
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, varianteId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rows.add(new FilialBestandRow(
                            rs.getInt("FILIALEID"),
                            rs.getString("FILIALNAME"),
                            rs.getInt("BESTAND")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows;
    }
}
