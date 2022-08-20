package tutorial.learn.apache_commons.lang;

import org.apache.commons.lang3.ArrayUtils;

public class _2_ArrayUtils {
    public static void main(String[] args) {

        int[] intArray = new int[]{1, 2, 3};

        // Copies the given array and adds the given element at the end of the new array.
        // Can be used at the end of the array
        ArrayUtils.add(intArray, 4);
        ArrayUtils.addAll(intArray, 4, 6, 7);
        ArrayUtils.addFirst(intArray, 4);

        // Clones an array returning a typecast result and handling null.
        ArrayUtils.clone(intArray);

        // Finds the indices of the given value in the array.
        ArrayUtils.indexesOf(intArray, 2);

        ArrayUtils.isEmpty(intArray);
        ArrayUtils.isNotEmpty(intArray);

        // can give a comparator as well
        ArrayUtils.isSorted(intArray);

        // Shifts the order of the given boolean array.
        // offset - The number of positions to rotate the elements.
        ArrayUtils.shift(intArray, 2);

        // Swaps two elements in the given int array.
        // offset1 – the index of the first element to swap 
        // offset2 – the index of the second element to swap
        ArrayUtils.swap(intArray, 2, 1);

        // Randomly permutes the elements of the specified array
        // Can give a random generator
        ArrayUtils.shuffle(intArray);

        // Produces a new int array containing the elements between the start and end indices.
        ArrayUtils.subarray(intArray, 0, 2);

        // converts object arrays to primitive arrays and vice versa
        ArrayUtils.toPrimitive(new Long[]{1L, 2L, 3L}, -1L);
        ArrayUtils.toObject(intArray);

        // Create a type-safe generic array.
        ArrayUtils.toArray(1, 2, 3);

        ArrayUtils.toMap(new String[][]{
                {"RED", "#FF0000"},
                {"GREEN", "#00FF00"},
                {"BLUE", "#0000FF"}});
    }
}
