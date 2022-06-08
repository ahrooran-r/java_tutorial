package tutorial.learn.guava._2_collections._1_maps;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.ImmutableClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

/**
 *
 */
public class _7_ClassToInstanceMapClass {

    public static void main(String[] args) {

        ClassToInstanceMap<Number> nums = MutableClassToInstanceMap.create();
        nums.putInstance(Integer.class, 20);
        nums.putInstance(Double.class, 0.2d);

        nums = ImmutableClassToInstanceMap.of();
    }
}
