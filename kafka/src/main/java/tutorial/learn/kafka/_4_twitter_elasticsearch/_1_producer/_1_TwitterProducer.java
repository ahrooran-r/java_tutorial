package tutorial.learn.kafka._4_twitter_elasticsearch._1_producer;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class _1_TwitterProducer {

    public static void main(String[] args) {
        new _1_TwitterProducer().run();
    }

    public KafkaProducer<String, String> createKafkaProducer() {

        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        // create safe producer
        props.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        props.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        props.setProperty(ProducerConfig.RETRIES_CONFIG, String.valueOf(Integer.MAX_VALUE));
        props.setProperty(ProducerConfig.RETRIES_CONFIG, String.valueOf(Integer.MAX_VALUE));
        // because kafka v2.0 > v1.1 -> we can keep this as 5 otherwise keep it as 1
        props.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");


        // create high throughput producer -> at the expense of little bit latency and cpu cycles
        props.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        props.setProperty(ProducerConfig.LINGER_MS_CONFIG, "20");
        props.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, String.valueOf(32 * 1024)); // 32kB batch size

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        return producer;
    }

    @SneakyThrows
    public void run() {

        // create twitter client

        // Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream
        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<>(100000);
        // create the client
        Client twitterClient = createTwitterClient(msgQueue);

        // establish connection -> now msgQueue would automatically be filling messages
        // This is done internally on its own thread
        twitterClient.connect();

        // create kafka producer
        try (KafkaProducer<String, String> producer = createKafkaProducer()) {

            // we manually create another thread and take from the queue
            // then send to kafka
            Thread sendToKafka = new Thread(() -> {

                // https://github.com/twitter/hbc

                while (!twitterClient.isDone()) {

                    String messageFromTwitter = null;

                    // BlockingQueue will return `null`
                    // if message is not there in the queue after the timeout when polling
                    try {
                        messageFromTwitter = msgQueue.poll(5, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        twitterClient.stop();
                    }

                    // if message is not null -> pass it into kafka
                    if (null != messageFromTwitter) {

                        ProducerRecord<String, String> record = new ProducerRecord<>(
                                "first_topic",
                                messageFromTwitter
                        );
                        producer.send(record);

                    }
                }
            });

            sendToKafka.start();

            sendToKafka.join();
        }
    }

    public Client createTwitterClient(BlockingQueue<String> msgQueue) {

        // Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth)
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();

        List<String> terms = Lists.newArrayList("sri", "lanka", "ranil");
        hosebirdEndpoint.trackTerms(terms);

        // These secrets should be read from a config file
        Authentication hosebirdAuth = new OAuth1(
                "consumerKey",
                "consumerSecret",
                "token",
                "secret");

        ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01")
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue));

        Client hosebirdClient = builder.build();

        return hosebirdClient;
    }
}
