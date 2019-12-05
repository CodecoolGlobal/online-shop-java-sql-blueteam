package Controller;

import Model.Admin;
import Model.Customer;
import Model.User;
import View.Display;

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