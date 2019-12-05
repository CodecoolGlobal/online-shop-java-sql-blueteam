package Controller;

import Model.User;

public class MainController {

    public void run() {
        User user = new UserFactory().login();
        user.showMenu();
    }
}
