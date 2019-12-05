package Model;

import Controller.UserBehaviour;

public class Customer extends User implements UserBehaviour {

    public Customer() {
        super();
    }

    @Override
    public void print(){
        System.out.println("Customer print");
    }
}
