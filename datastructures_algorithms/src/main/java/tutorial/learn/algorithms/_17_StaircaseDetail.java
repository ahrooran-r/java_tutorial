package tutorial.learn.algorithms;

public class _17_StaircaseDetail {
    public static void main(String[] args) {
        staircase(4);
    }

    public static void staircase(int n) {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (col >= n - row - 1) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
