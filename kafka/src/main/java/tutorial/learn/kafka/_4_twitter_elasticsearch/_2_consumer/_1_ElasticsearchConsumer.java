package tutorial.learn.kafka._4_twitter_elasticsearch._2_consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Slf4j
public class _1_ElasticsearchConsumer {

    public static void main(String[] args) {
        new _1_ElasticsearchConsumer().run();
    }

    public KafkaConsumer<String, String> createConsumer() {

        Properties props = new Properties();

        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "my_eighth_app");

        // 3 options -> https://kafka.apache.org/documentation/#consumerconfigs_auto.offset.reset
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // DISABLE autocommit of offsets
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        // create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        return consumer;
    }

    public void run() {
        try (
                KafkaConsumer<String, String> consumer = createConsumer();
                RestHighLevelClient restHighLevelClient = createClient()
        ) {

            // subscribe to topic
            consumer.subscribe(Collections.singletonList("twitter_tweets"));

            while (true) {

                // 1. retrieve records as a BATCH from kafka
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));
                log.info("Received record count: {}", records.count());

                // 2. iterate over received records
                for (ConsumerRecord<String, String> record : records) {

                    // 2.1 convert record to json
                    String jsonString = new ObjectMapper().writeValueAsString(record);

                    // 3. ACHIEVING IDEMPOTENCY -> Resilient to duplicate messages
                    // TWO ways of having a unique id so duplicate messages can be identifies

                    // 3.1 Having a generic Kafka ID
                    String genericKafkaID = record.topic() + "_" + record.partition() + "_" + record.offset();

                    // 3.2 We could use twitter specific id -> the record.value() holds the tweet
                    // We extract the twitter id from that json
                    String twitterID = extractIdFromJson(record.value());

                    // 4. send to elastic search and get a response
                    IndexResponse response = sendToElasticSearch(restHighLevelClient, jsonString, twitterID);
                    log.info("Response ID: {}", response.getId());
                }

                // after finishing a batch -> MANUALLY commit
                consumer.commitSync();
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * send record to elastic search via client
     *
     * @param id makes consumer (which is the elastic search) idempotent
     */
    public IndexResponse sendToElasticSearch(RestHighLevelClient client, String jsonString, String id)
            throws IOException {

        // 1 create a request
        IndexRequest indexRequest = new IndexRequest("twitter", "tweets", id);

        // 2 add the json string -> we are going to put this string in elastic search
        indexRequest.source(jsonString, XContentType.JSON);

        // 3 now send this request and get a response from server
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        return response;
    }

    public RestHighLevelClient createClient() {
        String host = "kafka-course-9448285319.ap-southeast-2.bonsaisearch.net";
        String username = "oxh2z6g329";
        String password = "5pmvvpfgbt";

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

        RestClientBuilder builder = RestClient

                .builder(new HttpHost(host, 443, "https"))

                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                        return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

        RestHighLevelClient highLevelClient = new RestHighLevelClient(builder);

        return highLevelClient;
    }

    public String extractIdFromJson(String tweetJson) throws JsonProcessingException {
        JsonNode parent = new ObjectMapper().readTree(tweetJson);

        // the key for twitter ID is "id_str"
        String content = parent.path("id_str").asText();
        return content;
    }
}
