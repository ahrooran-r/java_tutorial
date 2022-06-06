package tutorial.learn.guava._3_concurrency;

import com.google.common.util.concurrent.AtomicLongMap;

/**
 * In concurrent scenarios, standard HashMap might not really work well, as it's simply not concurrent.
 * In this particular scenario, AtomicLongMap bails you out by storing Long values in a thread-safe way.
 */
public class _2_AtomicLongMap {
    public static void main(String[] args) {

        AtomicLongMap<String> courses = AtomicLongMap.create();
        courses.put("Guava", 20L);

        long noOfStudents = 56;

        // retrieve data
        courses.get("Guava");

        // do operation and then return final value
        long totalNotesRequired = courses.accumulateAndGet(
                "Guava",
                noOfStudents,
                // I can replace `*` with any other operation -> Or come up with my own implementation
                (previousValueAlreadyInMap, newValue) -> (previousValueAlreadyInMap * newValue));

        // getAndAccumalate() -> similar to above but return old value

        // accumulateAndGet AND updateAndGet are almost same
        long onUpdate = courses.updateAndGet(
                "Guava",
                (x) -> (x / 2));

    }
}
