package tutorial.learn.algorithms.memoization;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class _8_CanConstruct {

    private final HashMap<String, Boolean> cache = new HashMap<>();

    public static void main(String[] args) {
        String target1 = "abcdef";
        List<String> wordBank1 = Arrays.asList("ab", "abc", "cd", "def", "abcd");

        String target2 = "skateboard";
        List<String> wordBank2 = Arrays.asList("bo", "rd", "ate", "t", "ska", "sk", "boar");

        var obj = new _8_CanConstruct();

        long t1 = System.currentTimeMillis();
        boolean result = obj.simpleCanConstruct(target1, wordBank1);
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));

        t1 = System.currentTimeMillis();
        result = obj.dynamicCanConstruct(target1, wordBank1);
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));
    }

    private boolean simpleCanConstruct(String target, List<String> wordBank) {

        // base condition
        if (target.equals("")) return true;

        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String result = target.replace(word, "");

                // if result equals target then replacing has not happened -> no chars to replace
                // otherwise there is further room for processing
                if (!result.equals(target) && simpleCanConstruct(result, wordBank)) return true;
            }
        }
        return false;
    }

    private boolean dynamicCanConstruct(String target, List<String> wordBank) {
        // base condition
        if (target.equals("")) return true;
        if (cache.containsKey(target)) return cache.get(target);

        for (String word : wordBank) {

            if (target.indexOf(word) == 0) {
                String result = target.replace(word, "");
                if (!result.equals(target) && simpleCanConstruct(result, wordBank)) {
                    cache.put(target, true);
                    return true;
                }
            }
        }

        cache.put(target, false);
        return false;
    }

    /**
     * I replace the removed chars with flag -> hence in the end only hashes will remain.
     * For example: "###", "#" etc.
     * If final string has any other chars, then that is NOT SUITABLE
     **/
    private boolean checkForHash(String target, String flag) {
        List<String> uniqueChars = target.chars().distinct().mapToObj(c -> String.valueOf((char) c)).toList();
        return uniqueChars.size() == 1 && uniqueChars.contains(flag);
    }
}
