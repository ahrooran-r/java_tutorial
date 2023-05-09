package tutorial.learn.performance_and_memory_management.jmh.json_benchmark;

import com.dslplatform.json.DslJson;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.squareup.moshi.JsonAdapter;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openjdk.jmh.infra.Blackhole;

import java.io.StringReader;
import java.util.*;

public class Unstructured {

    private Unstructured() {
    }

    @SneakyThrows
    public static void jackson(byte[] content, Blackhole blackhole, ObjectMapper objectMapper) {
        JsonNode array = objectMapper.readTree(content);
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
    public static void orgJson(String content, Blackhole blackhole) {
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

    /**
     * <a href="https://www.sitepoint.com/php-style-json-parsing-in-java-with-jsoniter/">tutorial</a>
     */
    @SneakyThrows
    public static void jsoniter(byte[] content, Blackhole blackhole) {
        Any array = JsonIterator.deserialize(content);
        array.forEach(category -> {
            String[] categoryField = category.toString("cat").split("~");
            String countryCode = categoryField[0];
            String gicsL2 = categoryField[1];

            Any statList = category.get("statistics");
            Map<String, Any> ratioStatsMap = new HashMap<>(statList.size());
            List<String> ratioList = new ArrayList<>(statList.size());
            statList.forEach(ratioStat -> {
                ratioStatsMap.put(ratioStat.toString("type"), ratioStat);
                ratioList.add(ratioStat.toString("type"));
            });

            Map<String, Any> symbolRatios = category.get("ratios").asMap();
            symbolRatios.keySet().forEach(symbolKey -> {
                List<String> allRatioList = new LinkedList<>(ratioList);
                String[] field = symbolKey.split("~");
                String exchange = field[0];
                String symbol = field[1];
                Map<String, Any> ratios = symbolRatios.get(symbolKey).asMap();

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
    public static void gson(String content, Blackhole blackhole, Gson gson) {
        JsonArray array = gson.fromJson(new JsonReader(new StringReader(content)), JsonArray.class);
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

    @SneakyThrows
    public static void moshi(String content, Blackhole blackhole, JsonAdapter<List<Map<String, Object>>> moshi) {
        List<Map<String, Object>> list = moshi.fromJson(content);
        assert list != null;
        list.forEach(category -> {
            String[] categoryField = String.valueOf(category.get("cat")).split("~");
            String countryCode = categoryField[0];
            String gicsL2 = categoryField[1];

            List<?> statList = (List<?>) category.get("statistics");
            Map<String, Object> ratioStatsMap = new HashMap<>();
            List<String> ratioList = new ArrayList<>(statList.size());
            statList.forEach(ratObj -> {
                Map<String, String> ratioStat = (Map<String, String>) ratObj;
                ratioStatsMap.put(ratioStat.get("type"), ratioStat);
                ratioList.add(ratioStat.get("type"));
            });

            Map<String, Object> symbolRatios = (Map<String, Object>) category.get("ratios");
            symbolRatios.keySet().forEach(symbolKey -> {
                List<String> allRatioList = new LinkedList<>(ratioList);
                String[] field = symbolKey.split("~");
                String exchange = field[0];
                String symbol = field[1];
                Map<String, String> ratios = (Map<String, String>) symbolRatios.get(symbolKey);

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
    public static void dsl(byte[] content, Blackhole blackhole, DslJson<Object> dslJson) {
        List<Map> list = dslJson.deserializeList(Map.class, content, content.length);
        assert list != null;
        list.forEach(category -> {
            String[] categoryField = String.valueOf(category.get("cat")).split("~");
            String countryCode = categoryField[0];
            String gicsL2 = categoryField[1];

            List<?> statList = (List<?>) category.get("statistics");
            Map<String, Object> ratioStatsMap = new HashMap<>();
            List<String> ratioList = new ArrayList<>(statList.size());
            statList.forEach(ratObj -> {
                Map<String, String> ratioStat = (Map<String, String>) ratObj;
                ratioStatsMap.put(ratioStat.get("type"), ratioStat);
                ratioList.add(ratioStat.get("type"));
            });

            Map<String, Object> symbolRatios = (Map<String, Object>) category.get("ratios");
            symbolRatios.keySet().forEach(symbolKey -> {
                List<String> allRatioList = new LinkedList<>(ratioList);
                String[] field = symbolKey.split("~");
                String exchange = field[0];
                String symbol = field[1];
                Map<String, String> ratios = (Map<String, String>) symbolRatios.get(symbolKey);

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
