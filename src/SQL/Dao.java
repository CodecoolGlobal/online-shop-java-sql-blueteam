package SQL;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class Dao {
    private SQL sql = new SQL();

    public boolean isName(String SQLquery, String providedName) throws SQLException {
        try {
            return providedName.equals(sql.selectQuery(SQLquery).getString(1));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPass(String SQLquery, String providedPass) throws SQLException{
        try {
            return providedPass.equals((sql.selectQuery(SQLquery).getString(1)));
        } catch (Exception e) {
            return false;
        }
    }

    public void showData(String SQLquerry) throws SQLException {
        ResultSet rs = sql.selectQuery(SQLquerry);
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
    }

    public boolean checkAdminStatus(String SQLquery) throws SQLException {
        Integer integer = 0;
        ResultSet rs = sql.selectQuery(SQLquery);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                integer = rs.getInt(1);
            }
        }
        sql.closeQuery();
        return integer == 1;
    }
}
