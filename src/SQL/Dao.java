package SQL;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Dao {
    private SQL sql = new SQL();

    public int idCreator (String tableName){
        ResultSet resultSet = sql.selectQuery("SELECT * from " + tableName);
        try {
            int lastId = 0;
            while (resultSet.next()){
                String tableId = resultSet.getString("id");
                if (Integer.parseInt(tableId) >= lastId){
                    lastId = Integer.parseInt(tableId) + 1;
                }
            }
            if (lastId == 0){
                lastId = 1;
            }
            return lastId;
        } catch(Exception e) {
            System.out.println(e);
        } finally {
            sql.closeQuery();
        }
            return 0;
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
            for(int i = 1 ; i <= columnsNumber; i++){
                System.out.print(String.format("%12s | ", rs.getString(i)));
            }
            System.out.println();
        }
        sql.closeQuery();
    }
}
