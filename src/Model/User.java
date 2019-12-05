package Model;

import Controller.CustomerController;
import Controller.UserBehaviour;

import java.sql.SQLException;

public abstract class User implements UserBehaviour {
    private CustomerController customerController;

    public User() {
        this.customerController = new CustomerController();
    }

    public void showMenu() throws SQLException {
        this.customerController.menu();
    }

    public void print() {
        System.out.println("User print");
    }
}
