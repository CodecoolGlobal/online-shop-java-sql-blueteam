package Controller;

import SQL.Dao;
import View.Display;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class UserController {
    private Dao sql;

    public UserController() {
        this.sql = new Dao();
    }

    public void menu() throws SQLException {
        Display.userChoices();
        Input input = new Input();
        int choice = input.in();
        while(!inputMatch(choice)){
            Display.wrongInput();
            choice = input.in();
        }
        menuChoices(choice);
    }

    private void menuChoices(int choice) throws SQLException {
        switch (choice) {
            case 1:
                showProducts();
                break;
            case 2:
                showProductsInDescOrder();
                break;
            case 3:
                showOrdersByClient();
                break;
        }
    }

    private boolean inputMatch(int choice) {
        ArrayList<Integer> possibilities = new ArrayList<>();
        possibilities.add(1);
        possibilities.add(2);
        possibilities.add(3);
        return possibilities.contains(choice);
    }

    private void showProducts() throws SQLException {
        String querry = "SELECT * FROM products;";
        sql.showData(querry);
    }

    private void showProductsInDescOrder() throws SQLException {
        String querry = ("SELECT * FROM products\n" +
                "INNER JOIN categories\n" +
                "ON products.category_id == categories.id\n" +
                "GROUP BY products.name;");
        sql.showData(querry);
    }

    private void showOrdersByClient() throws SQLException {
        String querry = ("");
        sql.showData(querry);
    }
}