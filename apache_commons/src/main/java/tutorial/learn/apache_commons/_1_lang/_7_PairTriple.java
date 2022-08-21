package tutorial.learn.apache_commons._1_lang;


import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

public class _7_PairTriple {
    public static void main(String[] args) {

        Pair<String, Integer> age = Pair.of("age", 29); // returns ImmutablePair
        MutablePair<String, Integer> ageMutable = MutablePair.of("age", 29);

        Triple<String, String, String> three = Triple.of("left", "middle", "right");

    }
}
