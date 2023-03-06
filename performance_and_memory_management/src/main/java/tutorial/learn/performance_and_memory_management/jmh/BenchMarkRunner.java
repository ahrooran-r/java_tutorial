package tutorial.learn.performance_and_memory_management.jmh;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Trial:
 * The JMH benchmark is run for a number of trials. Trials are also called as forks.
 * <p>
 * Warmup:
 * For each fork, a number of iterations are configured as warmups. This is to get the JVM to warmup the code we are measuring. This is important to avoid fluctuations or variations in the runtime once we start the actual iterations.
 * <p>
 * Iteration:
 * This is the actual benchmark code execution/iteration. The performance numbers from this will be output as the JMH benchmark result.
 * <p>
 * Each warmup iteration and measurement iteration is executed for a certain time.
 */
public class BenchMarkRunner {

    public static void main(String[] args) throws IOException {

        // add the main class that starts the benchmarking process
        Main.main(args);
    }

    /**
     * Bbenchmark method can only take parameters of type State, Control or Blackhole.
     * We cannot pass arbitrary parameters. We can pass arbitrary data using State annotation.
     * <p>
     * A State is a class-level annotation.
     * We can pass any class annotated with @State as an argument to the benchmark method.
     */
    @Benchmark
    public void basicBenchmark() {
        // This is how we initiate a benchmark
    }

    /**
     * JMH supports some possible benchmarks: Throughput, AverageTime, SampleTime, and SingleShotTime
     * <p>
     * Throughput:(default mode) To measure the throughout of a piece of code. This is used to measure the number of times a method is executed in a certain time. Use this when the method takes only a few milliseconds.
     * <p>
     * AverageTime: This is to get the average time the method takes to execute.
     * <p>
     * SampleTime: Sampled time for each operation. Shows p50, p90, p99, min and max times.
     * <p>
     * SingleShotTime:  This measures the time for a single operation. Use this when you want to account for the cold start time also.
     * <p>
     * All: Measures all of the above.
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void benchmarkMode() {
    }

    /**
     * value parameter: controls how many times the benchmark will be executed
     * <p>
     * Warmup parameter: controls how many times a benchmark will dry run before results are collected
     */
    @Benchmark
    @Fork(value = 5, warmups = 2)
    public void fork() {
        //  instructs JMH to run two warm-up forks and discard results before moving onto real timed benchmarking.

        // We here have 5 forks and among them 2 entire forks will be warmups.
    }

    @Benchmark
    @Warmup(iterations = 3, time = 200, timeUnit = TimeUnit.MILLISECONDS)
    public void warmup() {
        // specify 3 iterations each to be run for 200 millisecond.
        // The default time unit is seconds.
        // The default iterations is 20.
    }

    /**
     * Set the default measurement parameters for the benchmark.
     * It allows to specify the number of iterations and the time for which each is to be executed.
     */
    @Benchmark
    @Measurement(iterations = 3, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public void measurement() {
    }

    /*
     * Useful links:
     * 1. https://hg.openjdk.java.net/code-tools/jmh/file/2be2df7dbaf8/jmh-samples/src/main/java/org/openjdk/jmh/samples
     * 2. https://javadevcentral.com/jmh-benchmark-with-examples
     * 3. https://blog.avenuecode.com/java-microbenchmarks-with-jmh-part-2
     * 4. https://www.baeldung.com/java-microbenchmark-harness
     */
}
