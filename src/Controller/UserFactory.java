package Controller;

import Model.Admin;
import Model.Customer;
import Model.User;
import SQL.Dao;
import View.Display;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserFactory {
    private Dao sql = new Dao();

    public User createUser() {
        Input input = new Input();
        Display.chooseUserType();
        String choice = input.str();
        while(!inputMatch(choice)){
            Display.wrongInput();
            choice = input.str();
        }
            return chooseUser(choice);
    }

    public User login() throws SQLException {
        String providedPassword = new String();
        Input input = new Input();
        Display.loginName();
        String providedName = input.str();
        while(!nameExists(providedName)) {
            Display.nameDoesntExist();
            Display.loginName();
            providedName = input.str();
        }
        Display.loginPassword();
        providedPassword = input.str();
        while(!correctPassword(providedPassword, providedName)){
            Display.incorrectPassword();
            Display.loginPassword();
            providedPassword = input.str();
        }
        if(isAdmin(providedName)) {
            return chooseUser("admin");
        }
        return chooseUser("customer");
    }

    private boolean correctPassword(String providedPassword, String providedName) throws SQLException {
        String query = ("SELECT users.password\n" +
                "FROM users\n" +
                "WHERE users.name == " + "\"" + providedName + "\"");
        return sql.isPass(query, providedPassword);
    }

    private boolean isAdmin(String providedName) throws SQLException {
        String query = ("SELECT users.name, user_types.id\n" +
                "FROM user_types\n" +
                "JOIN users\n" +
                "ON users.user_type_id == user_types.id\n" +
                "WHERE users.name == \"" + providedName + "\"");
        return sql.checkAdminStatus(query);
    }

    private boolean nameExists(String providedName) throws SQLException {
        String query = ("SELECT users.name\n" +
                       "FROM users\n" +
                       "WHERE users.name == " + "\""+providedName+"\"");
        return sql.isName(query, providedName);
    }

    private User chooseUser(String choice) {
        switch (choice) {
            case "customer":
                return new Customer();
            case "admin":
                return new Admin();
        }
        return null;
    }

    public boolean inputMatch(String choice) {
        ArrayList<String> possibilities = new ArrayList<>();
        possibilities.add("admin");
        possibilities.add("customer");
        return possibilities.contains(choice);
    }
}