package tutorial.learn.performance_and_memory_management.jmh;

import lombok.SneakyThrows;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ComplexExample {

    public static void main(String[] args) throws IOException {

        // add the main class that starts the benchmarking process
        Main.main(args);
    }

    @Benchmark
    @Fork(value = 2)
    @Measurement(iterations = 3, time = 2)
    @Warmup(iterations = 2, time = 200, timeUnit = TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void benchmark(PasswordState passwordState, Blackhole blackhole) {

        String password = passwordState.password;

        // iterations has no useful meaning here
        // Just to show the usage
        for (int i = 0; i < passwordState.iterations; i++) {
            blackhole.consume(pkbdf2CustomHashFunction(password));
        }
    }

    /**
     * This is the method we are going to benchmark. Usually this method resides in some other class.
     */
    @SneakyThrows
    private byte[] pkbdf2CustomHashFunction(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return hash;
    }

    /**
     * Sometimes you'll want to initialize some variables that your benchmark code needs,
     * but which you do not want to be part of the code your benchmark measures.
     * Such variables are called state variables. State variables are declared in special state classes,
     * and an instance of that state class can then be provided as parameter to the benchmark method.
     * <ol>
     *     <li>The class must be declared public;</li>
     *     <li>If the class is a nested class, it must be declared static (e.g. public static class);</li>
     *     <li>The class must have a public no-arg constructor (no parameters to the constructor).</li>
     * </ol>
     * State Scope:
     * <p>
     *      A state object can be reused across multiple calls to your benchmark method.
     *      JMH provides different scopes that the state object can be reused in. These are:
     * </p>
     * <ol>
     *     <li>Thread: each thread running the benchmark will create its own instance of the state object.</li>
     *     <li>Group: each thread group running the benchmark will create its own instance of the state object.</li>
     *     <li>Benchmark: all threads running the benchmark share the same state object.</li>
     * </ol>
     * <p>
     */
    @State(Scope.Benchmark)
    public static class PasswordState {

        @Param({"100", "200", "300", "500", "1000"})
        public int iterations;

        String password;

        /**
         * Setup and Teardown:
         * <p>
         * State objects can provide methods that are used by JMH either during the setup of the state object,
         * before it's passed as argument to a benchmark, or after the benchmark is run. To specify such methods,
         * annotate them with the @Setup and the @Teardown annotations.
         * <p>
         * The @Setup and @Teardown arguments
         * <ol>
         *     <li>Trial: the method is called once for each full run of the benchmark, that is, a full fork including all warmup and benchmark iterations.</li>
         *     <li>Iteration: the method is called once for each iteration of the benchmark.</li>
         *     <li>Invocation: the method is called once for each call to the benchmark method.</li>
         * </ol>
         */
        @Setup(Level.Iteration)
        public void setUp() {
            // create new password for each trial of benchmark executions
            password = UUID.randomUUID().toString();
        }

        @TearDown(Level.Iteration)
        public void tearDown() {
            password = null;
        }
    }

}
