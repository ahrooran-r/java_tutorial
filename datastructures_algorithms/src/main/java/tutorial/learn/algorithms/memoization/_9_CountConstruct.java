package tutorial.learn.algorithms.memoization;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class _9_CountConstruct {

    private final HashMap<String, Integer> cache = new HashMap<>();

    public static void main(String[] args) {

        String target1 = "abcdef";
        List<String> wordBank1 = Arrays.asList("ab", "abc", "cd", "def", "abcd");

        String target2 = "skateboard";
        List<String> wordBank2 = Arrays.asList("bo", "rd", "ate", "t", "ska", "sk", "boar");

        String target3 = "purple";
        List<String> wordBank3 = Arrays.asList("purp", "p", "ur", "le", "purpl");

        var obj = new _9_CountConstruct();

        long t1 = System.currentTimeMillis();
        int result = obj.simpleCountConstruct(target3, wordBank3);
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));

        t1 = System.currentTimeMillis();
        result = obj.dynamicCountConstruct(target3, wordBank3);
        System.out.println(result);
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1));

    }

    private int simpleCountConstruct(String target, List<String> wordBank) {

        // base condition -> if only hashes are left return 1 because the string is constructable
        if (target.equals("")) return 1;

        int totalCount = 0;
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String result = target.replace(word, "");
                totalCount += simpleCountConstruct(result, wordBank);
            }
        }

        return totalCount;
    }

    private int dynamicCountConstruct(String target, List<String> wordBank) {
        // base condition
        if (target.equals("")) return 0;
        if (cache.containsKey(target)) return cache.get(target);

        int totalCount = 0;
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String result = target.replace(word, "");
                totalCount += simpleCountConstruct(result, wordBank);
            }
        }

        cache.put(target, totalCount);
        return totalCount;
    }
}
