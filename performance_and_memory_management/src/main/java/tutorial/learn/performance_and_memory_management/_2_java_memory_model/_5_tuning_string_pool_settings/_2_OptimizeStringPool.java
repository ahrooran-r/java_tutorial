package tutorial.learn.performance_and_memory_management._2_java_memory_model._5_tuning_string_pool_settings;

import org.openjdk.jmh.infra.Blackhole;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

public class _2_OptimizeStringPool {

    // -XX:+PrintStringTableStatistics
    public static void main(String[] args) throws InterruptedException {

        Blackhole blackhole = new Blackhole("Today's password is swordfish. I understand instantiating Blackholes directly is dangerous.");

        LocalDateTime start = LocalDateTime.now();
        int size = 20_000_000;

        List<String> collector = IntStream
                .range(0, size)
                .mapToObj(i -> UUID.randomUUID().toString().intern())
                .toList();

        blackhole.consume(collector);
        System.out.println("Time taken: " + Duration.between(start, LocalDateTime.now()).toSeconds());

        // this is because JVM needs to access some lock to get this data
        // since lock is not immediately available after computation, we have to wait sometime
        // otherwise just an error message will be printed
        Thread.sleep(Duration.ofSeconds(10).toMillis());

        // Time taken: 128.356
        // SymbolTable statistics:
        // Number of buckets       :     20011 =    160088 bytes, avg   8.000
        // Number of entries       :     22026 =    528624 bytes, avg  24.000
        // Number of literals      :     22026 =    893168 bytes, avg  40.551
        // Total footprint         :           =   1581880 bytes
        // Average bucket size     :     1.101
        // Variance of bucket size :     1.107
        // Std. dev. of bucket size:     1.052
        // Maximum bucket size     :         7
        // StringTable statistics:
        // Number of buckets       :     60013 =    480104 bytes, avg   8.000
        // Number of entries       :  10003188 = 240076512 bytes, avg  24.000
        // Number of literals      :  10003188 = 1120233928 bytes, avg 111.988
        // Total footprint         :           = 1360790544 bytes -> 1360.791 megabytes
        // Average bucket size     :   166.684
        // Variance of bucket size :   167.317
        // Std. dev. of bucket size:    12.935
        // Maximum bucket size     :       221

        // Above are the statistics generated
        // You can see Average bucket size -> 0.230
        // and Maximum bucket size -> 14

        // We want to start with much bigger hashmap
        // -XX:StringTableSize=120121

        // After optimization

        // Time taken: 25.305
        // SymbolTable statistics:
        // Number of buckets       :     20011 =    160088 bytes, avg   8.000
        // Number of entries       :     22026 =    528624 bytes, avg  24.000
        // Number of literals      :     22026 =    893168 bytes, avg  40.551
        // Total footprint         :           =   1581880 bytes
        // Average bucket size     :     1.101
        // Variance of bucket size :     1.107
        // Std. dev. of bucket size:     1.052
        // Maximum bucket size     :         7
        // StringTable statistics:
        // Number of buckets       :  12012941 =  96103528 bytes, avg   8.000
        // Number of entries       :  10003188 = 240076512 bytes, avg  24.000
        // Number of literals      :  10003188 = 1120233928 bytes, avg 111.988
        // Total footprint         :           = 1456413968 bytes -> 1456.414 megabytes -> size is definitely increased
        // Average bucket size     :     0.833 -> but look at the performance. avg bucket size is tremendously reduced
        // Variance of bucket size :     0.833
        // Std. dev. of bucket size:     0.913
        // Maximum bucket size     :         9
    }
}
