package Controller;

import Model.User;
import SQL.Dao;
import View.Display;

import java.sql.SQLException;

public class LoginController {
    private static Dao sql = new Dao();
    public static int login(String username, String password) throws SQLException{
            String querry = "SELECT user_type_id from users WHERE name='"+username+"' and password='"+password+"';";
            return sql.loginData(querry);


        }
    }

