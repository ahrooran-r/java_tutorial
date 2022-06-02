package _9_modules;

import java.util.Arrays;

public class _1_Arrays_Module {
    public static void main(String[] args) {

        Byte[] byteArray = {85, 13, 10, 101, 10, 43, 120, 34, 126, 112, 69, 31, 30, 102, 32, 0, 10, 81, 9, 113, 26, 63};
        String[] stringArray = {"A", "B", "c", "D", "e", "x", "Y", "z"};

        // Methods in array class

        Arrays.toString((new Byte[][]{{1, 2}, {2, 3}, {3, 4}})); // -> [[Ljava.lang.Byte;@3b6eb2ec, [Ljava.lang.Byte;@1e643faf, [Ljava.lang.Byte;@6e8dacdf]
        // This method returns a string representation of the “deep contents” of the specified Arrays.
        Arrays.deepToString(new Byte[][]{{1, 2}, {2, 3}, {3, 4}}); // -> [[1, 2], [2, 3], [3, 4]]

        // returns a fixed-size list backed by the specified Arrays
        Arrays.asList(byteArray);

        // Sorts the specified array of objects into ascending order, according to the natural ordering of its elements.
        Arrays.sort(byteArray.clone()); // -> [0, 9, 10, 10, 10, 13, 26, ... ]
        // Sorts the specified array using parallel sort.
        Arrays.parallelSort(byteArray.clone());

        // Searches the specified array for the specified object using the binary search algorithm.
        // The array must be sorted into ascending order
        Arrays.binarySearch(byteArray, (byte) 32);
        // This method searches a range of the specified array for the specified object using the binary search algorithm.
        Arrays.binarySearch(byteArray, 1, 7, (byte) 10); // -> 3

        // This method copies the specified array, with user defined new length.
        Arrays.copyOf(byteArray, byteArray.length + 100); // -> [0, 9, 10, 10, 10, ... , null, null, ...]
        // This method copies the specified range of the specified array into a new Arrays.
        Arrays.copyOfRange(byteArray, 0, 10); // -> [0, 9, 10, 10, 10, 13, 26, 30, 31, 32]

        // This method checks if both the arrays are equal or not
        Arrays.equals(new Byte[][]{{1, 2}, {2, 3}, {3, 4}}, new Byte[][]{{1, 2}, {3, 4}, {5, 6}}); // -> false
        // This method returns true if the two specified arrays are deeply equal to one another.
        Arrays.deepEquals(new Byte[][]{{1, 2}, {2, 3}, {3, 4}}, new Byte[][]{{1, 2}, {3, 4}, {5, 6}}); // -> false

        // Compares two arrays lexicographically.
        Arrays.compare(new Byte[]{1, 2, 5, 4}, new Byte[]{1, 2, 3}); // -> 2

        // This method finds and returns the index of the first unmatched element between the two specified arrays.
        Arrays.mismatch(new Byte[][]{{1, 2}, {2, 3}, {3, 4}}, new Byte[][]{{1, 2}, {3, 4}, {5, 6}}); // -> 0

        // This method assigns this fillValue to each index of this Arrays
        Arrays.fill(new Byte[10], (byte) 20);

        // This method set all the elements of this array in parallel, using the provided generator function.
        // Arrays.setAll(someArray, someGeneratorFunction);
    }
}
