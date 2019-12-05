package Controller;

import SQL.SQL;

public class ProductController {

    public static void addProduct(String name, float price, int amount, int is_available, int category_id){
        SQL sql = new SQL();
        String insert = "INSERT into products(name,price,amount,is_available,category_id) VALUES( '"+name+"',"+price+","+ amount+", "+is_available+","+category_id+");";
        sql.doQuery(insert);

    }
}
