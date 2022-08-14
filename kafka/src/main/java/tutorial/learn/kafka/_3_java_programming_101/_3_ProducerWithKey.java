package tutorial.learn.kafka._3_java_programming_101;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j
public class _3_ProducerWithKey {
    public static void main(String[] args) {

        Properties props = new Properties();

        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {

            // send 10 records
            for (int i = 0; i < 10; i++) {

                String topic = "first_topic";
                String key = "id_" + i;
                String message = "HelloWorld! " + i;

                ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);

                producer.send(record, (recordMetadata, e) -> {
                    if (null == e) {
                        log.info("received new metadata" +
                                "\nTopic: " + recordMetadata.topic() +
                                "\nOffset: " + recordMetadata.offset() +
                                "\nTimestamp: " + recordMetadata.timestamp() +
                                "\nKey: " + key
                        );
                    } else {
                        log.error("Error while producing: ", e);
                    }
                });

                producer.flush();
            }
        }
    }
}
