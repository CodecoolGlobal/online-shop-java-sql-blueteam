package Model;

import Controller.ProductController;

public class Product {
    int id;
    String name;
    float price;
    int amount;
    int is_available;
    int category_id;

    public Product(int id, String name, float price, int amount, int is_available, int category_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.is_available = is_available;
        this.category_id = category_id;

    }
    public Product(){

    }
        public void createProduct(String name, float price, int amount, int is_available, int category_id){
            ProductController.addProduct(name, price, amount, is_available, category_id);

        }
}
