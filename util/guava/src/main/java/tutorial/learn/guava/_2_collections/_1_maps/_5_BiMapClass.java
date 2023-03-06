package tutorial.learn.guava._2_collections._1_maps;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumHashBiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;

import java.util.HashMap;
import java.util.Map;

/**
 * A BiMap (or “bidirectional map”) is a special kind of a map that maintains an inverse view of the map
 * while ensuring that no duplicate values are present and a value can always be used safely to get the key back.
 * <p>
 * <a href="https://www.baeldung.com/guava-bimap">tutorial</a>
 */
public class _5_BiMapClass {
    public static void main(String[] args) {

        // creating various biMaps

        // 1. create from scratch
        BiMap<String, String> b1 = HashBiMap.create();

        // 2. create from already existing map
        Map<String, String> m = new HashMap<>();
        BiMap<String, String> b2 = HashBiMap.create(m);

        // 3. creating from Enum
        BiMap<MyEnum, String> operationStringBiMap = EnumHashBiMap.create(MyEnum.class);

        // 4. create immutable biMap
        BiMap<String, String> capitalCountryBiMap
                = new ImmutableBiMap.Builder<String, String>()
                .put("New Delhi", "India")
                .build();


        // CRUD
        b1.put("Washington, D.C.", "USA");

        String keyFromBiMap = capitalCountryBiMap.get("New Delhi"); // returns "India"
        // returns the inverse view
        String inverseKeyFromBiMap = capitalCountryBiMap.inverse().get("India"); // returns "New Delhi"

        // If we wish to override the value already present in BiMap, we can make use of the forcePut method
        // If there is already existing value, it will be ignored if we don't use #forcePut()
        b1.inverse().forcePut("USA", "New York");

    }

    enum MyEnum {

    }
}
