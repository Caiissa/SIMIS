import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:oracle:thin:rs03-db-inf-min.ad.fh-bielefeld.de:1521:xe";
    private static final String USER = "MbelhausSIMIS@ORCL";
    private static final String PASSWORD = "simis_Sql67";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
