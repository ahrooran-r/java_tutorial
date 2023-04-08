package tutorial.learn.performance_and_memory_management.jmh.json_benchmark;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openjdk.jmh.infra.Blackhole;

import java.io.BufferedReader;
import java.nio.file.Path;
import java.util.*;

public class JsonParse {

    private static final Path path = Path.of(ReadFile.BASE_PATH, "large-file.json");

    private JsonParse() {
    }

    @SneakyThrows
    public static void jackson(Blackhole blackhole) {
        byte[] content = ReadFile.asBytes(path);
        JsonNode array = new ObjectMapper().readTree(content);
        array.forEach(category -> {
            String[] categoryField = category.get("cat").asText().split("~");
            String countryCode = categoryField[0];
            String gicsL2 = categoryField[1];

            JsonNode statList = category.get("statistics");
            Map<String, JsonNode> ratioStatsMap = new HashMap<>(statList.size());
            List<String> ratioList = new ArrayList<>(statList.size());
            statList.forEach(ratioStat -> {
                ratioStatsMap.put(ratioStat.get("type").asText(), ratioStat);
                ratioList.add(ratioStat.get("type").asText());
            });

            JsonNode symbolRatios = category.get("ratios");
            symbolRatios.fieldNames().forEachRemaining(symbolKey -> {
                List<String> allRatioList = new LinkedList<>(ratioList);
                String[] field = symbolKey.split("~");
                String exchange = field[0];
                String symbol = field[1];
                JsonNode ratios = symbolRatios.get(symbolKey);

                ratios.fieldNames().forEachRemaining(key -> {
                    String segment = getSegment(key);
                    blackhole.consume(segment);
                    allRatioList.remove(key);
                });

                allRatioList.forEach(key -> {
                    String segment = getSegment(key);
                    blackhole.consume(segment);
                });

                blackhole.consume(exchange);
                blackhole.consume(symbol);
                blackhole.consume(countryCode);
                blackhole.consume(gicsL2);
                blackhole.consume(ratioStatsMap);
            });
        });
    }

    @SneakyThrows
    public static void orgJson(Blackhole blackhole) {
        String content = ReadFile.asString(path);
        JSONArray array = new JSONArray(content);
        array.forEach(catObj -> {
            JSONObject category = (JSONObject) catObj;
            String[] categoryField = category.getString("cat").split("~");
            String countryCode = categoryField[0];
            String gicsL2 = categoryField[1];

            JSONArray statList = category.getJSONArray("statistics");
            JSONObject ratioStatsMap = new JSONObject(statList.length());
            List<String> ratioList = new ArrayList<>(statList.length());
            statList.forEach(ratObj -> {
                JSONObject ratioStat = (JSONObject) ratObj;
                ratioStatsMap.put(ratioStat.getString("type"), ratioStat);
                ratioList.add(ratioStat.getString("type"));
            });

            JSONObject symbolRatios = category.getJSONObject("ratios");
            symbolRatios.keySet().forEach(symbolKey -> {
                List<String> allRatioList = new LinkedList<>(ratioList);
                String[] field = symbolKey.split("~");
                String exchange = field[0];
                String symbol = field[1];
                JSONObject ratios = symbolRatios.getJSONObject(symbolKey);

                ratios.keySet().forEach(key -> {
                    String segment = getSegment(key);
                    blackhole.consume(segment);
                    allRatioList.remove(key);
                });

                allRatioList.forEach(key -> {
                    String segment = getSegment(key);
                    blackhole.consume(segment);
                });

                blackhole.consume(exchange);
                blackhole.consume(symbol);
                blackhole.consume(countryCode);
                blackhole.consume(gicsL2);
                blackhole.consume(ratioStatsMap);
            });
        });
    }

    @SneakyThrows
    public static void jsoniter(Blackhole blackhole) {
        // String content = ReadFile.asString(path);
        // JSONArray array = new JSONArray(content);
        // array.forEach(catObj -> {
        //     JSONObject category = (JSONObject) catObj;
        //     String[] categoryField = category.getString("cat").split("~");
        //     String countryCode = categoryField[0];
        //     String gicsL2 = categoryField[1];
        //
        //     JSONArray statList = category.getJSONArray("statistics");
        //     JSONObject ratioStatsMap = new JSONObject(statList.length());
        //     List<String> ratioList = new ArrayList<>(statList.length());
        //     statList.forEach(ratObj -> {
        //         JSONObject ratioStat = (JSONObject) ratObj;
        //         ratioStatsMap.put(ratioStat.getString("type"), ratioStat);
        //         ratioList.add(ratioStat.getString("type"));
        //     });
        //
        //     JSONObject symbolRatios = category.getJSONObject("ratios");
        //     symbolRatios.keySet().forEach(symbolKey -> {
        //         List<String> allRatioList = new LinkedList<>(ratioList);
        //         String[] field = symbolKey.split("~");
        //         String exchange = field[0];
        //         String symbol = field[1];
        //         JSONObject ratios = symbolRatios.getJSONObject(symbolKey);
        //
        //         ratios.keySet().forEach(key -> {
        //             String segment = getSegment(key);
        //             blackhole.consume(segment);
        //             allRatioList.remove(key);
        //         });
        //
        //         allRatioList.forEach(key -> {
        //             String segment = getSegment(key);
        //             blackhole.consume(segment);
        //         });
        //
        //         blackhole.consume(exchange);
        //         blackhole.consume(symbol);
        //         blackhole.consume(countryCode);
        //         blackhole.consume(gicsL2);
        //         blackhole.consume(ratioStatsMap);
        //     });
        // });
    }

    @SneakyThrows
    public static void gson(Blackhole blackhole) {
        BufferedReader content = ReadFile.asReader(path);
        JsonArray array = new Gson().fromJson(content, JsonArray.class);
        array.forEach(catObj -> {
            JsonObject category = catObj.getAsJsonObject();
            String[] categoryField = category.get("cat").getAsString().split("~");
            String countryCode = categoryField[0];
            String gicsL2 = categoryField[1];

            JsonArray statList = category.getAsJsonArray("statistics");
            JsonObject ratioStatsMap = new JsonObject();
            List<String> ratioList = new ArrayList<>(statList.size());
            statList.forEach(ratObj -> {
                JsonObject ratioStat = (JsonObject) ratObj;
                ratioStatsMap.add(ratioStat.get("type").getAsString(), ratioStat);
                ratioList.add(ratioStat.get("type").getAsString());
            });

            JsonObject symbolRatios = category.getAsJsonObject("ratios");
            symbolRatios.keySet().forEach(symbolKey -> {
                List<String> allRatioList = new LinkedList<>(ratioList);
                String[] field = symbolKey.split("~");
                String exchange = field[0];
                String symbol = field[1];
                JsonObject ratios = symbolRatios.getAsJsonObject(symbolKey);

                ratios.keySet().forEach(key -> {
                    String segment = getSegment(key);
                    blackhole.consume(segment);
                    allRatioList.remove(key);
                });

                allRatioList.forEach(key -> {
                    String segment = getSegment(key);
                    blackhole.consume(segment);
                });

                blackhole.consume(exchange);
                blackhole.consume(symbol);
                blackhole.consume(countryCode);
                blackhole.consume(gicsL2);
                blackhole.consume(ratioStatsMap);
            });
        });
    }

    private static String getSegment(String ratio) {
        return switch (ratio) {
            case "PER", "PBR", "DY", "NETPRFMGN", "ROE", "ROI" -> "SEG_1";
            case "TDBTOTEQ", "LTDBTOEQ", "EVALTOFCF" -> "SEG_2";
            case "CR", "QR", "NETINTCOV" -> "SEG_3";
            case "AT", "IT", "RT" -> "SEG_4";
            case "RG", "EG", "DG" -> "SEG_5";
            default -> "SEG_6";
        };
    }

}
