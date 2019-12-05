package Controller;

import SQL.Dao;
import View.Display;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class UserController {
    private Dao sql;
    private Input input = new Input();

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
        Display.pressXToGoBack();
        backToMenu(input.str());
    }

    private void backToMenu(String str) throws SQLException {
        while(!pressedX(str)) {
            Display.wrongInput();
            backToMenu(input.str());
        }
        menu();
    }

    private boolean pressedX(String expected) {
        return expected.equals("x");
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
        }
    }

    private boolean inputMatch(int choice) {
        ArrayList<Integer> possibilities = new ArrayList<>();
        possibilities.add(1);
        possibilities.add(2);
        possibilities.add(3);
        possibilities.add(4);
        possibilities.add(5);
        possibilities.add(6);
        possibilities.add(7);
        return possibilities.contains(choice);
    }

    private void showProducts() throws SQLException {
        String query = "SELECT products.name AS product, products.price AS price, products.amount AS amount, categories.name AS category FROM products\n" +
                "JOIN categories ON products.category_id == categories.id";
        sql.showData(query);
    }

    private void showProductsInDescOrder() throws SQLException {
        String query = ("SELECT products.name AS product, products.amount, categories.name AS category FROM products\n" +
                "INNER JOIN categories\n" +
                "ON products.category_id == categories.id\n" +
                "GROUP BY products.name\n" +
                "ORDER BY products.amount DESC;");
        sql.showData(query);
    }

    private void showOrderedProductsByClient() throws SQLException {
        String query = ("SELECT SUM(order_products.amount) AS products, users.name AS user_name FROM users\n" +
                "JOIN orders ON users.id == orders.user_id\n" +
                "JOIN order_products ON order_products.order_id == orders.id");
        sql.showData(query);
    }

    private void showOrdersByClient() throws SQLException {
        String query = ("SELECT COUNT(orders.id) AS orders, users.name AS user_name\n" +
                "FROM orders\n" +
                "JOIN users\n" +
                "ON orders.user_id == users.id\n" +
                "GROUP BY users.name;");
        sql.showData(query);
    }

    private void showProductTypesAmount() throws SQLException {
        String query = ("SELECT COUNT(products.id) AS types, categories.name AS category\n" +
                "FROM products\n" +
                "JOIN categories\n" +
                "ON products.category_id == categories.id\n" +
                "GROUP BY categories.id");
        sql.showData(query);
    }

    private void showTotalCostPerClient() throws SQLException {
        String query = ("SELECT users.name AS user_name, (COUNT(DISTINCT orders.id) * products.price * order_products.amount) AS total_cost\n" +
                "FROM users\n" +
                "JOIN orders\n" +
                "ON orders.user_id == users.id\n" +
                "JOIN order_products\n" +
                "ON order_products.order_id == orders.id\n" +
                "JOIN products\n" +
                "ON products.id == order_products.product_id\n" +
                "GROUP BY users.id");
        sql.showData(query);
    }

    private void showOrderStatusForAllClients() throws SQLException {
        String query = ("SELECT users.name AS user_name,\n" +
                "orders.order_at AS ordered,\n" +
                "products.amount AS products,\n" +
                "(COUNT(DISTINCT orders.id) * products.price * order_products.amount) AS total_cost,\n" +
                "orders.status AS status\n" +
                "FROM users\n" +
                "JOIN orders\n" +
                "ON orders.user_id == users.id\n" +
                "JOIN order_products\n" +
                "ON order_products.order_id == orders.id\n" +
                "JOIN products\n" +
                "ON products.id == order_products.product_id\n" +
                "GROUP BY users.id");
        sql.showData(query);
    }
}