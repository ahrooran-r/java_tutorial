package tutorial.learn.performance_and_memory_management.jmh.json_benchmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.EncodingMode;
import com.jsoniter.output.JsonStream;
import com.jsoniter.spi.DecodingMode;
import org.openjdk.jmh.annotations.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@State(Scope.Benchmark)
public class ReadFile {

    public static final String BASE_PATH = "./../performance_and_memory_management/src/main/resources/";

    public String asString;

    public byte[] asBytes;

    public BufferedReader asReader;

    public ObjectMapper objectMapper;

    public Gson gson;

    @Setup(Level.Trial)
    public void setUp() throws IOException {
        Path path = Path.of(BASE_PATH, "large-file.json");
        asBytes = Files.readAllBytes(path);
        asString = Files.readString(path, StandardCharsets.UTF_8);
        asReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);

        objectMapper = new ObjectMapper();
        gson = new Gson();

        JsonIterator.setMode(DecodingMode.DYNAMIC_MODE_AND_MATCH_FIELD_WITH_HASH);
        JsonStream.setMode(EncodingMode.DYNAMIC_MODE);
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        asBytes = null;
        asString = null;
        asReader = null;
    }
}
