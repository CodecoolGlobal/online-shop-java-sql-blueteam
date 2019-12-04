import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public  class FIleOperator {
    public static void copy(String copy, String paste) throws IOException {
        var source = Paths.get(copy);
        var dest = Paths.get(paste);

        try (var fis = Files.newInputStream(source);
            var fos = Files.newOutputStream(dest)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }

    public static void remove(String path) {
        File file = new File(path);
            file.delete();
    }

    public static void clearDB(String backup, String dbFile) throws IOException {
        remove(dbFile);
        copy(backup, dbFile);
    }
}
