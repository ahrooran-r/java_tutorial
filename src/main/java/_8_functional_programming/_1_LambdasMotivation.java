package _8_functional_programming;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class _1_LambdasMotivation {
    public static void main(String[] args) {

        // suppose a normal object generation is as follows
        Set<String> set1 = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });

        // this can be simplified as following
        Set<String> set2 = new TreeSet<>((String s1, String s2) -> {
            return s1.length() - s2.length();
        });

        // above can be further simplified
        Set<String> set3 = new TreeSet<>((s1, s2) -> {
            return s1.length() - s2.length();
        });

        // above can be further simplified
        Set<String> set4 = new TreeSet<>((s1, s2) -> s1.length() - s2.length());
    }
}
