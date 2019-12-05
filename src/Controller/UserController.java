package Controller;

import SQL.Dao;
import View.Display;
import java.util.ArrayList;

public abstract class UserController {
    private Dao sql;
    private Input input = new Input();

    public UserController() {
        this.sql = new Dao();
    }

    public void menu() {
        Display.userChoices();
        Input input = new Input();
        int choice = input.integer();
        while(!inputMatch(choice)){
            Display.wrongInput();
            choice = input.integer();
        }
        menuChoices(choice);
        Display.pressXToGoBack();
        backToMenu(input.string());
    }

    private void backToMenu(String str) {
        while(!pressedX(str)) {
            Display.wrongInput();
            backToMenu(input.string());
        }
        menu();
    }

    private boolean pressedX(String expected) {
        return expected.equals("x");
    }

    private void menuChoices(int choice) {
        switch (choice) {
            case 1:
                showProducts();
                break;
            case 2:
                showProductsInDescOrder();
                break;
            case 3:
                showOrderedProductsByClient();
                break;
            case 4:
                showOrdersByClient();
                break;
            case 5:
                showProductTypesAmount();
                break;
            case 6:
                showTotalCostPerClient();
                break;
            case 7:
                showOrderStatusForAllClients();
                break;
            case 8:
                removeCategory();
                break;
            case 9:
                addProduct();
                break;
            case 10:
                removeProduct();
                break;
        }
    }

    private void removeProduct() {
        sql.removeWhat();
    }

    private void addProduct() {
        sql.addProduct();
    }

    private boolean inputMatch(int choice) {
        ArrayList<Integer> possibilities = new ArrayList<>();
        for(Integer i = 1; i < 11; i++) {
            possibilities.add(i);
        }
        return possibilities.contains(choice);
    }

    private void removeCategory() { sql.removeCategory(); }

    private void showProductsInDescOrder() {
        sql.showProductsInDescOrder();
    }

    private void showProducts() {
        sql.showProducts();
    }

    private void showOrderedProductsByClient() {
        sql.showOrderedProductsByClient();
    }

    private void showOrdersByClient() {
        sql.showOrdersByClient();
    }

    private void showProductTypesAmount() {
        sql.showProductTypesAmount();
    }

    private void showTotalCostPerClient() {
        sql.showTotalCostPerClient();
    }

    private void showOrderStatusForAllClients() {
        sql.showOrderStatusForAllClients();
    }
}