package _6_io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class _2_Scanner_Class {
    public static void main(String[] args) {

        // initializing scanner class
        Scanner scanner = new Scanner(System.in);

        scanner.nextByte();
        scanner.nextShort();
        scanner.nextInt();
        scanner.nextLong();

        scanner.nextFloat();
        scanner.nextDouble();

        scanner.next().charAt(0);
        scanner.hasNextLine();

        scanner.hasNextByte();
        scanner.hasNextShort();
        scanner.hasNextInt();
        scanner.hasNextLong();

        scanner.hasNextFloat();
        scanner.hasNextDouble();

        scanner.hasNext();
        scanner.hasNextLine();


        // scanner can be replaced by following code
        StringBuffer sb = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            sb.append(br.readLine()).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
    }
}
