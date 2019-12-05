package SQL;

import Controller.Input;
import View.Display;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Dao {
    private SQL sql = new SQL();

    private boolean isName(String SQLquery, String providedName) {
        try {
            if (sql.selectQuery(SQLquery).getString(1) != null) {
                return providedName.equals(sql.selectQuery(SQLquery).getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean nameExists(String providedName) {
        String query = ("SELECT users.name\n" +
                "FROM users\n" +
                "WHERE users.name == " + "\""+providedName+"\"");
        return this.isName(query, providedName);
    }

    private boolean isPass(String SQLquery, String providedPass) {
        try {
            if (sql.selectQuery(SQLquery).getString(1) != null) {
                return providedPass.equals((sql.selectQuery(SQLquery).getString(1)));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean correctPassword(String providedPassword, String providedName) {
        String query = ("SELECT users.password\n" +
                "FROM users\n" +
                "WHERE users.name == " + "\"" + providedName + "\"");
        return this.isPass(query, providedPassword);
    }

    private void remove(String tableName, String id){
        SQL sql = new SQL();
        PreparedStatement ps = null;
        String orderToSQL = ("DELETE FROM tableName = ? WHERE id = ? ");
        try {
            ps = sql.connect().prepareStatement(orderToSQL);
            ps.setString(1, tableName);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeCategory() {
        Input input = new Input();
        Display.whichTable();
        String tableName = input.string();
        Display.whichId();
        String id = input.string();
        this.remove(tableName, id);
    }

    private void showData(String SQLquery) {
        try {
            ResultSet rs = sql.selectQuery(SQLquery);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            for (int i = 1; i <= columnsNumber; i++) {
                System.out.print(String.format("%12s | ", rsmd.getColumnName(i)));
            }
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    System.out.print(String.format("%12s | ", rs.getString(i)));
                }
                System.out.println();
            }
            sql.closeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showProducts() {
        String query = "SELECT products.name AS product, products.price AS price, products.amount AS amount, categories.name AS category FROM products\n" +
                "JOIN categories ON products.category_id == categories.id";
        this.showData(query);
    }

    public boolean checkAdminStatus(String SQLquery) {
        Integer adminStatus = 0;
        try {
            ResultSet rs = sql.selectQuery(SQLquery);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    adminStatus = rs.getInt(1);
                }
            }
            sql.closeQuery();
            return adminStatus == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void showProductsInDescOrder() {
        String query = ("SELECT products.name AS product, products.amount, categories.name AS category FROM products\n" +
                "INNER JOIN categories\n" +
                "ON products.category_id == categories.id\n" +
                "GROUP BY products.name\n" +
                "ORDER BY products.amount DESC;");
        this.showData(query);
    }

    public void showOrderedProductsByClient() {
        String query = ("SELECT SUM(order_products.amount) AS products, users.name AS user_name FROM users\n" +
                "JOIN orders ON users.id == orders.user_id\n" +
                "JOIN order_products ON order_products.order_id == orders.id");
        this.showData(query);
    }

    public void showTotalCostPerClient() {
        String query = ("SELECT users.name AS user_name, (COUNT(DISTINCT orders.id) * products.price * order_products.amount) AS total_cost\n" +
                "FROM users\n" +
                "JOIN orders\n" +
                "ON orders.user_id == users.id\n" +
                "JOIN order_products\n" +
                "ON order_products.order_id == orders.id\n" +
                "JOIN products\n" +
                "ON products.id == order_products.product_id\n" +
                "GROUP BY users.id");
        this.showData(query);
    }

    public void showProductTypesAmount() {
        String query = ("SELECT COUNT(products.id) AS types, categories.name AS category\n" +
                "FROM products\n" +
                "JOIN categories\n" +
                "ON products.category_id == categories.id\n" +
                "GROUP BY categories.id");
        this.showData(query);
    }

    public void showOrdersByClient() {
        String query = ("SELECT COUNT(orders.id) AS orders, users.name AS user_name\n" +
                "FROM orders\n" +
                "JOIN users\n" +
                "ON orders.user_id == users.id\n" +
                "GROUP BY users.name;");
        this.showData(query);
    }

    public void showOrderStatusForAllClients() {
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
        this.showData(query);
    }

    public boolean isAdmin(String providedName) {
        String query = ("SELECT users.name, user_types.id\n" +
                "FROM user_types\n" +
                "JOIN users\n" +
                "ON users.user_type_id == user_types.id\n" +
                "WHERE users.name == \"" + providedName + "\"");
        return this.checkAdminStatus(query);
    }

    public void addProduct() {
        Input input = new Input();
        this.showProductsInDescOrder();
        Display.whatToAdd();
        String newName = input.string();
        Float price = Float.parseFloat(input.string());
        Integer amount = Integer.parseInt(input.string());
        Integer is_available = Integer.parseInt(input.string());
        Integer category_id = Integer.parseInt(input.string());
        String query = ("INSERT INTO products(name, price, amount, is_available, category_id)\n"+
                        " VALUES (" +
                        "\""+newName+"\"" + ", " +
                        price + ", " +
                        amount + ", " +
                        is_available + ", " +
                        category_id + ");");
        sql.closeQuery();
        add(query);
    }

    private void add(String SQLquery) {
        try {
            sql.doQuery(SQLquery);
            sql.closeQuery();
            Display.addSuccesful();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeWhat() {
        Input input = new Input();
        String theWhat = input.string();
        removeProduct(theWhat);
    }

    public void removeProduct(String id){
        Connector connector = new Connector();
        PreparedStatement ps = null;
        String orderToSQL = ("DELETE FROM Categories WHERE id = ? ");
        try {
            ps = connector.connect().prepareStatement(orderToSQL);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}