package tutorial.learn.algorithms.tabulation;

import java.util.Arrays;
import java.util.List;

public class _8_CanConstruct {

    public static void main(String[] args) {
        String target1 = "abcdef";
        List<String> wordBank1 = Arrays.asList("ab", "abc", "cd", "def", "abcd");

        String target2 = "skateboard";
        List<String> wordBank2 = Arrays.asList("bo", "rd", "ate", "t", "ska", "sk", "boar");

        var obj = new _8_CanConstruct();

        long t1 = System.currentTimeMillis();
        boolean result = obj.canConstruct(target1, wordBank1);
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));
    }

    /**
     * In dynamic programming
     * <p>
     * If I want to program based on addition -> I need to put base case as 0th index
     * <p>
     * If I want to program based on subtraction -> I need to put base case as <b>LAST</b> index
     * <p>
     * In this question, I have to check whether target is valid by checking resultant string after
     * subtracting every word in word-bank
     */
    private boolean canConstruct(String target, List<String> wordBank) {

        // initialize table with false values
        boolean[] table = new boolean[target.length() + 1];
        Arrays.fill(table, false);

        // My purpose is to make last cell -> True
        // Then this target is achievable with words from word bank

        // Here index = 0 means empty string
        table[0] = true;

        for (int i = 0; i < table.length; i++) {
            if (table[i] == true) {
                for (String word : wordBank) {
                    if (target.startsWith(word, i)) table[i + word.length()] = true;
                }
            }
        }
        return table[target.length()];
    }
}
