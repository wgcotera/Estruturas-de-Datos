package Tree.HuffmanTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Util {

    public static String geTextFromFile(String path) throws IOException {
            return Files.readString(Path.of(path));
    }

    public static void createAndWriteFile(String content, String path) throws IOException {
        if (!Files.exists(Path.of(path))) {
            Files.createFile(Path.of(path));
        }
        Files.writeString(Path.of(path), content);
    }
}
