package de.moebelhaus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:oracle:thin:@rs03-db-inf-min.ad.fh-bielefeld.de:1521/ORCL"; // anpassen
    private static final String USER = "MbelhausSIMIS@ORCL";
    private static final String PASSWORD = "simis_Sql67";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
