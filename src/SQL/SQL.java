package SQL;

import java.sql.*;

public class SQL {
    Connection connection;
    Statement statement;

    public SQL(){

    }

    public ResultSet selectQuery (String sql){
        ResultSet resultSet = null;
        try {
            connection = this.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public Connection connect(){
        String url = "jdbc:sqlite:res/onlineshop .db";
        // onlineshop .db
        // jdbc:sqlite:/home/kariston/Dokumenty/oop/Shop/online-shop-java-sql-blueteam/res/onlineshop .db
        Connection result = null;
        try {
            result = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void doQuery(String sql){
        try {
            connection = connect();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeQuery(){
        try {
            this.statement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
