package Controller;

import Model.Admin;
import Model.Customer;
import Model.User;
import View.Display;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserFactory {

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

    public User loginUser() throws SQLException {
        Input input = new Input();
        Display.writeUserName();
        String userName = input.text();
        Display.writeUserPass();
        String userPass = input.text();

        int type_of_account = LoginController.login(userName,userPass);
        switch (type_of_account) {
            case 1:
                return new Customer();
            case 2:
                return new Admin();
            default:
                return new Customer();
        }

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