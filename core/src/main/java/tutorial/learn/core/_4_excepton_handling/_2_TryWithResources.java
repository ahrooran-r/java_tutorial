package tutorial.learn.core._4_excepton_handling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class _2_TryWithResources {
    public static void main(String[] args) throws IOException {

        File file = new File("/Users/abhishekverma/desktop/hello.txt/");
        BufferedReader br = new BufferedReader(new FileReader(file));

        // New and improved try-with-resources statement in JDK 9
        try (br) {
            String st = br.readLine();
            System.out.println(st);
        }
    }

    // JDK 8 and before -> following 2 approaches are used

    static String readFirstLineFromFile(String path) throws IOException {

        // The try-with-resources statement is a try statement that declares one or more resources.
        // A resource is an object that must be closed after the program is finished with it.
        // The try-with-resources statement ensures that each resource is closed at the end of the statement.
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();

            // this will close both the BufferedReader (br) and the underlying FileReader object.
            // In Java, when you close a wrapper stream/reader (like BufferedReader),
            // it automatically closes the underlying stream/reader that it wraps.

            // This happens because:
            // 1. BufferedReader's close() method calls close() on its underlying Reader (FileReader in this case)
            // 2. The try-with-resources statement automatically calls close() when execution leaves the try block
        }
    }

    // The following example uses a finally block instead of a try-with-resources statement:
    static String readFirstLineFromFileWithFinallyBlock(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            if (br != null) br.close();
        }
    }

}
