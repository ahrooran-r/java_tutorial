package _11_guava._2_collections._1_maps;

import com.google.common.collect.MapMaker;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ConcurrentMap;

/**
 * makes it easy to create thread-safe maps
 * <p>
 * <a href="https://www.baeldung.com/guava-mapmaker">tutorial</a>
 */
public class _2_MapMakerClass {

    public static void main(String[] args) {

        // returned map does not allow null values for both the key and the value.
        ConcurrentMap<User, Session> sessionCache = new MapMaker().makeMap();

        // have initial capacity
        ConcurrentMap<User, Profile> profileCache = new MapMaker().initialCapacity(100).makeMap();

        // set concurrency level -> default = 4
        sessionCache = new MapMaker().concurrencyLevel(10).makeMap();


        // Use WEAK KEYS when applicable
        // The maps we created above use strong references for both the keys and values.
        // So, the entries stay in the map even if the keys and the values are garbage-collected.
        // We should use weak references instead.

        // A sessionCache entry is invalid after the key (the user object) is garbage-collected.
        // So, let's use weak references for the keys:
        sessionCache = new MapMaker().weakKeys().makeMap();

        profileCache = new MapMaker().weakValues().makeMap();
    }


    @AllArgsConstructor
    @Data
    static class User {
        private long id;
        private String name;
    }

    @AllArgsConstructor
    @Data
    static class Profile {
        private long id;
        private String type;
    }

    @AllArgsConstructor
    @Data
    static class Session {
        private long id;
    }
}
