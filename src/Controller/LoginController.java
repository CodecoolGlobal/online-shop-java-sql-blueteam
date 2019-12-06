package Controller;

import SQL.Dao;
import java.sql.SQLException;

public class LoginController {
    private static Dao sql = new Dao();

    public static int login(String username, String password) throws SQLException {
        String querry = "SELECT user_type_id from users WHERE name='" + username + "' AND password='" + password + "';";
        return sql.loginData(querry);
    }
}
