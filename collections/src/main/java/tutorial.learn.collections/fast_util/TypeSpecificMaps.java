package tutorial.learn.collections.fast_util;

import it.unimi.dsi.fastutil.doubles.*;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;

/**
 * Due to the lack of generics, FastUtils generates a large number of type-specific classes.
 * And unfortunately, this leads to a huge jar file (22MB)
 * <p>
 * However, luckily for us, FastUtils includes a find-deps.sh script which allows generation of smaller,
 * more focused jars comprising only the classes we want to use in our application.
 */
public class TypeSpecificMaps {
    public static void main(String[] args) {

        Double2DoubleMap d2dMap = new Double2DoubleOpenHashMap(10_000); // default
        d2dMap.put(2.1, 4.67);
        d2dMap.put(2.1, Double.NaN);

        /*
         * Standard JVM collections that implement the Iterable interface usually create a
         * fresh temporary iterator object at each iteration step. With huge collections,
         * this can create a garbage collection issue.
         * Fastutil provides an alternative that greatly mitigates this:
         */
        for (Double2DoubleMap.Entry e : Double2DoubleMaps.fastIterable(d2dMap)) {
            // e will be reused on each iteration, so it will be only one object
        }

        // similarly we can get iterator as well
        ObjectIterator<Double2DoubleMap.Entry> iterator = Double2DoubleMaps.fastIterator(d2dMap);

        // Fastutil also provides the fastForeach method.
        // This will take a Consumer functional interface and perform a lambda-expression for each loop
        Double2DoubleMaps.fastForEach(d2dMap, e -> {
            // e is also reused across iterations
        });


        // --------------------------------------------------------------------------------------------

        // different implementations of map
        d2dMap = new Double2DoubleLinkedOpenHashMap(10_000); // sorted
        d2dMap = new Double2DoubleArrayMap(10_000); // no need to use this
        d2dMap = new Double2DoubleRBTreeMap();
        d2dMap = new Double2DoubleAVLTreeMap();

        Double2ObjectMap<String> d2oMap = new Double2ObjectOpenHashMap<>(100);
        d2oMap.put(2.35, "StringObject");

        Object2DoubleMap<String> o2dMap = new Object2DoubleOpenHashMap<>();
        o2dMap.put("StringObject", 4.12);
    }
}