package tutorial.learn.algorithms;

import java.util.HashMap;
import java.util.Map;

/*
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * */
public class _14_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        int result = lengthOfLongestSubstring("pwwkew");
        System.out.println(result);
    }

    /**
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/solutions/3630543/using-hashmap-and-hashset-java-solution-detailed-explanation/
     *
     * @param s 'abc'abcbb
     * @return 3
     */
    public static int lengthOfLongestSubstring(String s) {

        if (s.length() > 5 * Math.pow(10, 4)) throw new IllegalArgumentException("constraint violation");
        if (s.length() <= 1) return s.length();

        else {
            Map<Character, Integer> charPostions = new HashMap<>();

            int start = 0;
            int longest = 0;

            for (int end = 0; end < s.length(); end++) {
                char charAt = s.charAt(end);
                if (charPostions.containsKey(charAt)) {
                    // this means character is repeating
                    start = Math.max(start, charPostions.get(charAt) + 1);
                }

                charPostions.put(charAt, end);
                longest = Math.max(longest, end - start + 1);
            }

            return longest;
        }
    }
}
