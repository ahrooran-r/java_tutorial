package tutorial.learn.kafka._3_java_programming_101;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j
public class _2_ProducerWithCallback {
    public static void main(String[] args) {

        Properties props = new Properties();

        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {

            // send a Producer Record
            ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", "Hello World!");

            // now send the record
            producer.send(record, new Callback() {

                // A callback executes everytime a successful message is sent or exception is thrown
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {

                    if (null == e) {
                        // if error is null -> message successfully sent
                        log.info("received new metadata" +
                                "\nTopic: " + recordMetadata.topic() +
                                "\nOffset: " + recordMetadata.offset() +
                                "\nTimestamp: " + recordMetadata.timestamp()
                        );
                    } else {
                        // else error occurred
                        log.error("Error while producing: ", e);
                    }
                }
            });
            producer.flush();
        }
    }
}
