package Model;

public class Product {

    int id;
    int basket_id;
    int order_at;
    String status;
    int pay_at;


    public void Product(int id, int basket_id, int order_at, String status, int pay_at){
        this.id = id;
        this.basket_id = basket_id;
        this.order_at = order_at;
        this.status = status;
        this.pay_at = pay_at;


    }
}
