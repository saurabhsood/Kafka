package com.consumer;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.connect.json.JsonDeserializer;

import kafka.server.KafkaConfig;

public class JDBCConsumer {
	 private final static String BOOTSTRAP_SERVERS =

	            "localhost:9092,localhost:9093,localhost:9094";
	public static void main(String[] args) {
		 final Properties props = new Properties();

	      props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,

	                                  BOOTSTRAP_SERVERS);

	      props.put(ConsumerConfig.GROUP_ID_CONFIG,

	                                  "KafkaJDBCConsumer");

	      props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,

	              JsonDeserializer.class.getName());

	      props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,

	    		  JsonDeserializer.class.getName());


	    KafkaConsumer<String, Long> kafkaConsumer = new KafkaConsumer<>(props);
	    kafkaConsumer.subscribe(Collections.singletonList("jdbc-mysql-clntpf"));
	    while (true) {
	      ConsumerRecords<String, Long> records = kafkaConsumer.poll(2);
	      for (ConsumerRecord<String, Long> record : records) {
	        System.out.printf("offset = %d, key = %s, value = %s", record.offset(),record.key(), record.value());
	        System.out.println();
	      }
	    }
}
}
