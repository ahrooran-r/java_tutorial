package tutorial.learn.performance_and_memory_management.jmh.json_benchmark;

import com.fasterxml.jackson.databind.ObjectMapper;
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

        // ReadFile readFile = new ReadFile();
        // readFile.setUp();
        //
        // JsonParse.gson(readFile.asReader, new Blackhole("Today's password is swordfish. I understand instantiating Blackholes directly is dangerous."));
    }

    @Benchmark
    public void gson(ReadFile readFile, Blackhole blackhole) {
        JsonParse.gson(readFile.asString, blackhole, readFile.gson);
    }

    @Benchmark
    public void jackson(ReadFile readFile, Blackhole blackhole) {
        JsonParse.jackson(readFile.asString, blackhole, readFile.objectMapper);
    }

    @Benchmark
    public void jsoniter(ReadFile readFile, Blackhole blackhole) {
        JsonParse.jsoniter(readFile.asBytes, blackhole);
    }

    @Benchmark
    public void orgJson(ReadFile readFile, Blackhole blackhole) {
        JsonParse.orgJson(readFile.asString, blackhole);
    }
}


