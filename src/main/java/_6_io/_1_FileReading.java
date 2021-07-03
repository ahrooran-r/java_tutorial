package _6_io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class _1_FileReading {
    public static void main(String[] args) throws IOException {

        StringBuilder text = new StringBuilder();

        printFile_m3("C:\\Users\\DELL\\Documents\\Tutorials\\Java\\src\\io\\io.txt", text);
    }

    // Method - 1 (old fashioned)
    public static void printFile_m1(String fileName, StringBuilder sb) throws IOException {

        // does the actual reading of file -> reads bytes from file
        FileInputStream file_in = new FileInputStream(fileName);

        // a file helper that enforces encoding scheme -> translates bytes into characters
        InputStreamReader file_encode = new InputStreamReader(file_in, StandardCharsets.UTF_8);

        // file reading is a costly process -> BufferedReader helps reduce the time taken to read by buffering
        BufferedReader file_buffer = new BufferedReader(file_encode);

        try {
            String line;
            while ((line = file_buffer.readLine()) != null) {
                sb.append(line).append("\n");
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file_buffer.close();
            file_encode.close();
            file_in.close();
        }
    }

    // Method - 2 (use try with resources and object chaining) -> Most preferable method for reading files
    public static void printFile_m2(String fileName, StringBuilder sb) {
        try (BufferedReader file_buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String line;
            while ((line = file_buffer.readLine()) != null) {
                sb.append(line).append("\n");
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method -3 (Using raw file reader) -> not recommended => no control over encoding hence this is platform dependant
    public static void printFile_m3(String fileName, StringBuilder sb) {
        try (FileReader file = new FileReader(fileName)) {
            int i;
            while ((i = file.read()) != -1) {
                sb.append((char) i);
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
