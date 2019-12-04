import java.io.IOException;

public class Main {
    private static String BACKUP = "res/backup.db";
    private static String DBFILE = "res/onlineshop.db";
    public static void main(String[] args) throws IOException {
        FIleOperator.clearDB(BACKUP,DBFILE);
        ConnectDB.connect(DBFILE);
    }
}
