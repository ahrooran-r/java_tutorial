package tutorial.learn.collections.agrona;

import org.agrona.collections.Int2ObjectCache;
import org.agrona.collections.IntLruCache;

public class _8_Caches {
    public static void main(String[] args) {

        /*
         * Collection	        Notes
         * Int2ObjectCache	    Cache with primitive int lookup to an object.
         *                      Tuned for very small data structures stored within CPU cache lines.
         *                      Typical sizes are 2 to 16 entries. Underlying storage is an array.
         * IntLruCache	        Fixed size cache which evicts the least used entry when running out of space.
         */

        Int2ObjectCache<String> smallCollection = new Int2ObjectCache<>(16, 16, System.out::println);
        IntLruCache<String> intLruCache = new IntLruCache<>(16, x -> "Create object for x", System.out::println);
    }
}
