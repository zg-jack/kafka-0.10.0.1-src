package com.xuebusi;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * 测试生产者
 * Created by SYJ on 2017/12/5.
 */
public class XbsProducer {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer producer = new KafkaProducer(props);
        ProducerRecord<String, String> record = new ProducerRecord<>("zp", 0, "topic_zp_test_key_1", "topic_zp_test_value_1");

        for (int i = 0; i < 10; i++) {
            Future<RecordMetadata> future = producer.send(record);
            try {
                RecordMetadata metadata = future.get();
                System.out.println("\n=======消息发送成功=======" + metadata + "===========");

                //休眠1秒钟
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println("\n\n========消息发送失败=========\n\n");
                e.printStackTrace();
            }
        }
    }
}
