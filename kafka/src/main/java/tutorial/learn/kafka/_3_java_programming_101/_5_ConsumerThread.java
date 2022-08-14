package tutorial.learn.kafka._3_java_programming_101;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class _5_ConsumerThread {
    public static void main(String[] args) {

        Properties props = new Properties();

        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "my_sixth_app");

        // 3 options -> https://kafka.apache.org/documentation/#consumerconfigs_auto.offset.reset
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // create consumer
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {

            // subscribe to topic(s) -> a consumer can subscribe to any # of topics
            consumer.subscribe(Collections.singleton("first_topic"));

        }

        CountDownLatch latch = new CountDownLatch(1);
    }

    /**
     * We are going to run (say) 10 consumers at the same time.
     * <p>
     * I need to shutdown consumers when they are not receiving messages
     */
    static class Consumer implements Runnable {

        private final CountDownLatch latch;

        private final KafkaConsumer<String, String> consumer;

        public Consumer(CountDownLatch latch, Properties props) {
            this.latch = latch;
            this.consumer = new KafkaConsumer<>(props);
        }

        @Override
        public void run() {

            try {

                // poll for new data
                while (true) {

                    // the consumer will block until a message is fetched when using consumer#poll()
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));

                    Iterator<ConsumerRecord<String, String>> iterator = records.iterator();

                    while (iterator.hasNext()) {

                        ConsumerRecord<String, String> record = iterator.next();

                        log.info("received new metadata" +
                                "\nKey: " + record.key() +
                                "\nValue: " + record.value() +
                                "\nTopic: " + record.topic() +
                                "\nOffset: " + record.offset() +
                                "\nTimestamp: " + record.timestamp()
                        );
                    }
                }

            } catch (WakeupException wakeupException) {
                log.info("Received shutdown signal");

            } finally {
                consumer.close();
                latch.countDown();
            }
        }

        @SneakyThrows
        public void shutdown() {

            // To prevent continuous poll, a wakeup() method is there
            // It'll raise a WakeupException
            // https://stackoverflow.com/a/37748336/10582056
            consumer.wakeup();
        }
    }
}
