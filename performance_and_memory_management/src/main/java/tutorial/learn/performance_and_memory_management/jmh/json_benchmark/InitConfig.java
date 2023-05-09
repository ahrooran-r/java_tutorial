package tutorial.learn.performance_and_memory_management.jmh.json_benchmark;

import com.dslplatform.json.DslJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.blackbird.BlackbirdModule;
import com.google.gson.*;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.EncodingMode;
import com.jsoniter.output.JsonStream;
import com.jsoniter.spi.DecodingMode;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import org.openjdk.jmh.annotations.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@State(Scope.Benchmark)
public class InitConfig {

    public static final String BASE_PATH = "./../performance_and_memory_management/src/main/resources/";

    public String asString;
    public byte[] asBytes;
    public BufferedReader asReader;

    public ObjectMapper jackson;
    public Gson gson;
    public JsonAdapter<List<Map<String, Object>>> moshi;
    public DslJson<Object> dsljson;

    @Setup(Level.Trial)
    public void setUp() throws IOException {
        Path path = Path.of(BASE_PATH, "large-file.json");
        asBytes = Files.readAllBytes(path);
        asString = Files.readString(path, StandardCharsets.UTF_8);
        asReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);

        // Jackson
        jackson = new ObjectMapper()
                .registerModule(new BlackbirdModule())
                .registerModule(new JavaTimeModule())
                // .registerModule(new AfterburnerModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // Gson
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsString()))
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (value, typeOfSrc, context) -> new JsonPrimitive(value.toString()))
                .registerTypeAdapter(OffsetDateTime.class, (JsonDeserializer<OffsetDateTime>) (json, type, jsonDeserializationContext) -> OffsetDateTime.parse(json.getAsString()))
                .registerTypeAdapter(OffsetDateTime.class, (JsonSerializer<OffsetDateTime>) (value, typeOfSrc, context) -> new JsonPrimitive(value.toString()))
                .create();

        // Jsoniter
        JsonIterator.setMode(DecodingMode.DYNAMIC_MODE_AND_MATCH_FIELD_WITH_HASH);
        JsonStream.setMode(EncodingMode.DYNAMIC_MODE);

        // moshi
        moshi = new Moshi.Builder().build()
                .adapter(Types.newParameterizedType(List.class, Map.class));

        //dsl
        dsljson = new DslJson<>(new DslJson.Settings<>().includeServiceLoader());
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        asBytes = null;
        asString = null;
        asReader = null;

        jackson = null;
        gson = null;
        moshi = null;
    }
}
