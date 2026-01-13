package de.moebelhaus.lager;

import de.moebelhaus.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LagerDAO {

    public static List<Lager> searchLager(String suchtext) {
        List<Lager> lagerListe = new ArrayList<>();

        String sql = """
    SELECT L.LAGERID, L.BESTELLUNGID, L.MENGE_VERFUEGBAR
    FROM LAGER L
    LEFT JOIN PRODUKT_LAGER PL ON L.LAGERID = PL.LAGERID
    WHERE TO_CHAR(L.LAGERID) LIKE ?
       OR TO_CHAR(L.BESTELLUNGID) LIKE ?
    ORDER BY L.LAGERID
""";


        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + suchtext + "%");
            ps.setString(2, "%" + suchtext + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lagerListe.add(new Lager(rs.getInt("LAGERID"), rs.getInt("BESTELLUNGID"), rs.getInt("MENGE_VERFUEGBAR")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lagerListe;
    }
}
