package tutorial.learn.kafka._3_java_programming_101;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;

/**
 * Assign and seek mostly used to replay a specific data or to fetch a specific message
 */
@Slf4j
public class _6_ConsumerAssignAndSeek {
    public static void main(String[] args) {

        Properties props = new Properties();

        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // We do not want to use group ID

        // 3 options -> https://kafka.apache.org/documentation/#consumerconfigs_auto.offset.reset
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // create consumer
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {

            // we do not subscribe to a topic -> we assign a topic and a partition
            TopicPartition topicPartition = new TopicPartition("first_topic", 0);
            Set<TopicPartition> singleTopicPartition = Collections.singleton(topicPartition);
            // Assign method takes in a series of partitions as a set or list etc.
            // -> so we enclose the single partition into a list
            consumer.assign(singleTopicPartition);

            // now seek
            // So we assign a topic and a partition to the consumer
            // Then we tell consumer to read from this specific offset
            // its like saying "select * from topic_partition where id >= 15 limit 5"
            long startFrom = 15L;
            consumer.seek(topicPartition, startFrom);

            // poll for new data
            long numberOfMessagesToRead = 5L;
            boolean keepOnReading = true;
            while (keepOnReading) {

                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));

                // iterate over received records
                for (ConsumerRecord<String, String> record : records) {

                    // if error is null -> message successfully sent
                    log.info("received new metadata" +
                            "\nKey: " + record.key() +
                            "\nValue: " + record.value() +
                            "\nTopic: " + record.topic() +
                            "\nOffset: " + record.offset() +
                            "\nTimestamp: " + record.timestamp()
                    );
                    ;

                    // break after reading 20 messages
                    if (--numberOfMessagesToRead <= 0) {
                        keepOnReading = false;
                        log.info("Exiting application\n");
                        break;
                    }
                }
            }
        }
    }
}
