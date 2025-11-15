void main() {

    // old way
    int[] numbers1 = new int[5];
    numbers1[0] = 2;
    numbers1[1] = 30;
    numbers1[2] = 10;
    numbers1[3] = 100;

    // new way
    byte[] numbers2 = {1, 2, 3, 4};

    // creating multi-dimensional arrays
    byte[][] matrix = {{0, 0, 0}, {1, 1, 1}};

    // COPYING ARRAYS

    byte[] numbers2_clone = numbers2.clone();
    // since `numbers2` is 1-D, each element is copied
    System.out.println(numbers2 == numbers2_clone); // -> false

    byte[][] matrix_clone = matrix.clone();
    // since `matrix` is multi-D, only outer array is copied.
    // each inner arrays are shared between `matrix` and `matrix_clone`
    System.out.println(matrix == matrix_clone); // -> false
    System.out.println(matrix[0] == matrix_clone[0]); // -> true

    // deep cloning is not built into java
}