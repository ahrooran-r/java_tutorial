package _6_io;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class _3_Encoding {
    public static void main(String[] args) {

        printEncodingDetails("a");
        printEncodingDetails("€");
        printEncodingDetails("\u1F602");

    }

    private static void printEncodingDetails(String symbol) {
        System.out.println("\nSymbol: " + symbol);
        System.out.println("ASCII: " + Arrays.toString(symbol.getBytes(StandardCharsets.US_ASCII)));
        System.out.println("ISO-8859-1: " + Arrays.toString(symbol.getBytes(StandardCharsets.ISO_8859_1)));
        System.out.println("UTF-8: " + Arrays.toString(symbol.getBytes(StandardCharsets.UTF_8)));
        System.out.println("UTF-16 BE: " + Arrays.toString(symbol.getBytes(StandardCharsets.UTF_16BE)));
        System.out.println("UTF-16 LE: " + Arrays.toString(symbol.getBytes(StandardCharsets.UTF_16LE)));
    }
}
