package _9_modules.collections;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class _4_Array_Deque {

    // faster than LinkedList as queue -> prefer to use it

    public static void main(String[] args) {

        final String[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        // creating empty array deque, can specify initial capacity
        Deque<String> alphabet = new ArrayDeque<>(30);

        // DEQUE AS QUEUE (FIFO)

        // add element to last
        alphabet.add("Banana");

        // add all elements of another list to the last
        alphabet.addAll(Arrays.asList(ALPHABET));
        // here new list is added before "Banana" in old list as index=0

        // view elements
        alphabet.toString(); // -> [Banana, A, B, C, D, ...]

        // removes and returns head of deque
        alphabet.remove();
        alphabet.remove("L");

        // DEQUE AS STACK (LIFO)

        // push on top of stack
        alphabet.push("1");

        // pop on top from stack
        alphabet.pop();
    }

}
