package Model;

import Controller.UserBehaviour;

public class Admin extends User implements UserBehaviour {

    @Override
    public void print() {
        System.out.println("Admin print");
    }
}
