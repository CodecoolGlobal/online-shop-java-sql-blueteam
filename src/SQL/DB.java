package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static void connect() {
        String db = "online-shop-java-sql-blueteam/res/onlineshop.db";
        Connection cnn = null;
        try {
            String url = "jdbc:sqlite:" + db;
            cnn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (cnn != null) {
                    cnn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}