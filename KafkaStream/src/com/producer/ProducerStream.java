package com.producer;

import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerStream {

	private final static String BOOTSTRAP_SERVERS ="localhost:9092,localhost:9093,localhost:9094";

	private  static final LinkedBlockingQueue<String> lbs = new LinkedBlockingQueue();
	static Producer<String, String> producer  = createProducer();

	public static void run() {
try
{
	String key = lbs.poll();
			while (key !=null) {
				
				producer.send(new ProducerRecord<String, String>("streams-test", key, key));
				System.out.println("Sent:" + key);
				key = lbs.poll();
			}
			System.out.println("lbs ==  null Exit");
		} catch (Exception e) {
			e.printStackTrace();}

	

	}
	
	public static void add(String s)
	{

			lbs.add(s);
			run();
	
	}

	private static Producer<String, String> createProducer() {
	        Properties props = new Properties();
	        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
	        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
	        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
	        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
	        return new KafkaProducer<>(props);
	}

	
}
