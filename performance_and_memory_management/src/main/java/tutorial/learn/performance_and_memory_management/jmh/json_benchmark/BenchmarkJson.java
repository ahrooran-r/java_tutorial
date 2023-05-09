package tutorial.learn.performance_and_memory_management.jmh.json_benchmark;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * NOTE: By default all {@link Benchmark} annotated methods will run.
 * Hence, it is better to comment other classes in the module to isolate and test.
 * <p>
 * run with <pre>{@code --add-opens java.base/java.lang=ALL-UNNAMED}</pre>
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(value = 6)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
public class BenchmarkJson {
    public static void main(String[] args) throws IOException {
        Main.main(args);

        // InitConfig initConfig = new InitConfig();
        // initConfig.setUp();
        // Blackhole blackhole = new Blackhole("Today's password is swordfish. I understand instantiating Blackholes directly is dangerous.");

        // Algo.dsl(initConfig.asBytes, blackhole, initConfig.dsljson);
    }

    @Benchmark
    public void gson(InitConfig initConfig, Blackhole blackhole) {
        Unstructured.gson(initConfig.asString, blackhole, initConfig.gson);
    }

    @Benchmark
    public void jackson(InitConfig initConfig, Blackhole blackhole) {
        Unstructured.jackson(initConfig.asBytes, blackhole, initConfig.jackson);
    }

    @Benchmark
    public void jsoniter(InitConfig initConfig, Blackhole blackhole) {
        Unstructured.jsoniter(initConfig.asBytes, blackhole);
    }

    @Benchmark
    public void orgJson(InitConfig initConfig, Blackhole blackhole) {
        Unstructured.orgJson(initConfig.asString, blackhole);
    }

    @Benchmark
    public void moshi(InitConfig initConfig, Blackhole blackhole) {
        Unstructured.moshi(initConfig.asString, blackhole, initConfig.moshi);
    }

    @Benchmark
    public void dsl(InitConfig initConfig, Blackhole blackhole) {
        Unstructured.dsl(initConfig.asBytes, blackhole, initConfig.dsljson);
    }

    // Benchmark               Mode  Cnt   Score   Error  Units
    // BenchmarkJson.dsl      thrpt  120  14.006 ± 0.160  ops/s
    // BenchmarkJson.gson     thrpt  120  10.945 ± 0.186  ops/s
    // BenchmarkJson.jackson  thrpt  120   9.673 ± 0.041  ops/s
}


