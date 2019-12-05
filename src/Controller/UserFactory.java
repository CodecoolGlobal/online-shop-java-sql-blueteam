package Controller;

import Model.Admin;
import Model.Customer;
import Model.User;
import SQL.Dao;
import View.Display;

public class UserFactory {
    private Dao sql = new Dao();

    public User login() {
        Input input = new Input();
        Display.loginName();
        String providedName = input.string();
        while(!nameExists(providedName)) {
            Display.nameDoesntExist();
            Display.loginName();
            providedName = input.string();
        }
        Display.loginPassword();
        String providedPassword = input.string();
        while(!correctPassword(providedPassword, providedName)){
            Display.incorrectPassword();
            Display.loginPassword();
            providedPassword = input.string();
        }
        if(isAdmin(providedName)) {
            return chooseUser("admin");
        }
        return chooseUser("customer");
    }

    private boolean correctPassword(String providedPassword, String providedName) {
        return sql.correctPassword(providedPassword, providedName);
    }

    private boolean nameExists(String providedName) {
        return sql.nameExists(providedName);
    }

    private boolean isAdmin(String providedName) {
        return sql.isAdmin(providedName);
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
}