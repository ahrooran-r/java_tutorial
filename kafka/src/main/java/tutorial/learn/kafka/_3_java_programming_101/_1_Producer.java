package tutorial.learn.kafka._3_java_programming_101;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class _1_Producer {
    public static void main(String[] args) {

        // create producer properties -> search for producer configs in kafka docs
        // https://kafka.apache.org/documentation/#producerconfigs=
        Properties props = new Properties();

        // // Old way of setting configs
        // props.setProperty("bootstrap.servers", "localhost:9092");
        // props.setProperty("key.serializer", StringSerializer.class.getName());
        // props.setProperty("value.serializer", StringSerializer.class.getName());

        // New way of setting configs
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create producer -> KafkaProducer<Key, Value>
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {

            // send a Producer Record
            ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", "Hello World!");

            // now send the record
            producer.send(record);
            producer.flush();
        }
    }
}
