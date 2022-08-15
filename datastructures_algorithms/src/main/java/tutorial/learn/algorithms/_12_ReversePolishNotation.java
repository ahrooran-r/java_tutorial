package tutorial.learn.algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class _12_ReversePolishNotation {
    public static void main(String[] args) {

        String[] array1 = {"2", "1", "+", "3", "*"};
        String[] array2 = {"4", "13", "5", "/", "+"};
        int k = 3;

        var obj = new _12_ReversePolishNotation();
        int result = obj.reversePolishNotation(array1);
        System.out.println(result);

        result = obj.reversePolishNotation(array2);
        System.out.println(result);
    }

    private int reversePolishNotation(String[] input) {

        final List<String> codes = Arrays.asList("+", "-", "*", "/");

        Stack<String> stack = new Stack<>();

        for (String element : input) {

            // if element is either: + - * /
            if (codes.contains(element)) {

                int result = 0;
                // Note: first item I pop would be y and second item would be x
                // because in stack 2nd item would be above 1st item
                int y = Integer.parseInt(stack.pop());
                int x = Integer.parseInt(stack.pop());

                switch (element) {
                    case "+" -> result = x + y;
                    case "-" -> result = x - y;
                    case "*" -> result = x * y;
                    case "/" -> result = x / y;
                }

                stack.push(String.valueOf(result));

            } else {
                // else: just push the number in stack
                stack.push(element);
            }
        }

        return Integer.parseInt(stack.pop());
    }
}
