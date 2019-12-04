package Model;

public abstract class User {
    int id;
    String name;
    String password;
    int account_type;

    public User(int id, String name, String password, int account_type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.account_type = account_type;
    }
}
