package Controller;

import Model.User;

import java.sql.SQLException;

public class MainController {

    public void run() throws SQLException {
        User user = new UserFactory().loginUser();
        user.showMenu();
    }
}
