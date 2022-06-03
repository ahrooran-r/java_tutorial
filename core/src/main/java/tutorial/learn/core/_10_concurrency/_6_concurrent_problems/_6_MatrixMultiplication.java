package tutorial.learn.core._10_concurrency._6_concurrent_problems;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _6_MatrixMultiplication {
    public static void main(String[] args) {

        final int multiplier = 1;
        final int bound = 1;

        int[][] matA = new int[2 * multiplier][3 * multiplier];
        int[][] matB = new int[3 * multiplier][4 * multiplier];
        int[][] out = new int[matA.length][matB[0].length];

        fillMat(matA, matB, bound);
        printMat(matA);
        printMat(matB);

        multiplySerial(matA, matB, out);
        printMat(out);

        out = new int[matA.length][matB[0].length];
        multiplyParallel(matA, matB, out);
        printMat(out);
    }

    private static void multiplySerial(int[][] matA, int[][] matB, int[][] out) {

        // checking whether matrices are compatible
        assert (matA[0].length == matB.length);
        assert (out.length == matA.length && out[0].length == matB[0].length);

        int rowA, colA, colB;
        rowA = matA.length;
        colA = matA[0].length;
        colB = matB[0].length;

        // just for the info
        // rowB = colA;
        // rowOut = rowA;
        // colOut = colB;

        for (int rA = 0; rA < rowA; rA++) {
            for (int cB = 0; cB < colB; cB++) {

                for (int k = 0; k < colA; k++) {
                    out[rA][cB] += matA[rA][k] * matB[k][cB];
                }
            }
        }
    }

    @SneakyThrows
    private static void multiplyParallel(int[][] matA, int[][] matB, int[][] out) {

        // checking whether matrices are compatible
        assert (matA[0].length == matB.length);
        assert (out.length == matA.length && out[0].length == matB[0].length);

        int rowA, colA, colB;
        rowA = matA.length;
        colA = matA[0].length;
        colB = matB[0].length;

        ExecutorService service = Executors.newFixedThreadPool(rowA);
        for (int rA = 0; rA < rowA; rA++) {

            // so what i'm doing here?
            // Since rowA items are not affected by the multi-threading concepts,
            // I'm going to create `rowA` amount of threads and
            // run each inner for loop in that separate thread

            // I can also do this for the second layer of for loop,
            // but this is enough for proof of concept!!!

            int finalRA = rA;
            service.execute(() -> {
                // if you take a look at multiplySerial(), code
                // the inner loops are placed inside an executor service
                for (int cB = 0; cB < colB; cB++) {
                    for (int k = 0; k < colA; k++) {
                        out[finalRA][cB] += matA[finalRA][k] * matB[k][cB];
                    }
                }
            });
        }

        service.shutdown();
        while (!service.isTerminated()) {
            System.out.println("Service is still running...");
            Thread.sleep(1000);
        }
    }

    private static void fillMat(int[][] matA, int[][] matB, int bound) {
        final Random random = new Random();

        for (int rA = 0; rA < matA.length; rA++) {
            for (int cA = 0; cA < matA[0].length; cA++) {
                matA[rA][cA] = random.nextInt(bound) + 1;
            }
        }

        for (int rB = 0; rB < matB.length; rB++) {
            for (int cB = 0; cB < matB[0].length; cB++) {
                matB[rB][cB] = random.nextInt(bound) + 1;
            }
        }
    }

    private static void printMat(int[][] mat) {
        for (int[] row : mat) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
