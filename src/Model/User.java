package Model;

import Controller.CustomerController;

public abstract class User {
    private CustomerController customerController;

    public User() {
        this.customerController = new CustomerController();
    }

    public void showMenu() {
        this.customerController.menu();
    }
}
