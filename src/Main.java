import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String DBFILE = "res/onlineshop.db";
        String BACKUP = "res/backup.db";
        FIleOperator.clearDB(BACKUP, DBFILE);
        ConnectDB.connect(DBFILE);
    }
}
