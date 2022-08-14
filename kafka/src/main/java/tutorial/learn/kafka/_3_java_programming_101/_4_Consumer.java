package tutorial.learn.kafka._3_java_programming_101;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

@Slf4j
public class _4_Consumer {
    public static void main(String[] args) {

        Properties props = new Properties();

        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "my_fourth_app");

        // 3 options -> https://kafka.apache.org/documentation/#consumerconfigs_auto.offset.reset
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // create consumer
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {

            // subscribe to topic(s) -> a consumer can subscribe to any # of topics
            Set<String> singleTopic = Collections.singleton("first_topic");
            consumer.subscribe(singleTopic);

            // poll for new data
            while (true) {

                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));

                // iterate over received records
                Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
                while (iterator.hasNext()) {

                    ConsumerRecord<String, String> record = iterator.next();

                    // if error is null -> message successfully sent
                    log.info("received new metadata" +
                            "\nKey: " + record.key() +
                            "\nValue: " + record.value() +
                            "\nTopic: " + record.topic() +
                            "\nOffset: " + record.offset() +
                            "\nTimestamp: " + record.timestamp()
                    );
                }

                // outer while loop continuously poll for new data
            }
        }
    }
}
