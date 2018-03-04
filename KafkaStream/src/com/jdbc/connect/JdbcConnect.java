package com.jdbc.connect;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.utils.Utils;
import org.apache.kafka.connect.cli.ConnectDistributed;
import org.apache.kafka.connect.cli.ConnectStandalone;
import org.apache.kafka.connect.json.JsonDeserializer;
import org.apache.kafka.connect.json.JsonSerializer;
import org.apache.kafka.connect.runtime.ConnectorConfig;
import org.apache.kafka.connect.util.ConnectorUtils;

import io.confluent.connect.jdbc.JdbcSourceConnector;
import io.confluent.connect.jdbc.source.JdbcSourceConnectorConfig;
import io.confluent.connect.jdbc.source.JdbcSourceTask;

public class JdbcConnect {

	 private final static String BOOTSTRAP_SERVERS =

	            "localhost:9092,localhost:9093,localhost:9094";
	
	public static void main(String s[]) throws Exception
	{
		String k[]=new String [] {"D:\\Learning\\Library\\Apache\\Kafka\\config\\connect-standalone.properties","D:\\Learning\\Projects\\git\\KafkaStream\\src\\com\\jdbc\\connect\\jdbc.properties"};
		 ConnectStandalone.main(k);
		 Thread.currentThread().sleep(100000000);
	}
	
	public static Properties workerConfig()
	{
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaJDBCSource");
		props.put(ConnectorConfig.CONNECTOR_CLASS_CONFIG, "io.confluent.connect.jdbc.JdbcSourceConnector");
		props.put(ConnectorConfig.KEY_CONVERTER_CLASS_CONFIG, JsonSerializer.class.getName());
		props.put(ConnectorConfig.VALUE_CONVERTER_CLASS_CONFIG, JsonDeserializer.class.getName());
		props.put(ConnectorConfig.TASKS_MAX_CONFIG, 2);
		return props;
	}
	
	public static Properties connectorConfig()
	{
		Properties props = new Properties();
		props.put(JdbcSourceConnectorConfig.CONNECTION_URL_CONFIG, "jdbc:mysql://localhost:3306/Test");
		props.put(JdbcSourceConnectorConfig.CONNECTION_USER_CONFIG, "root");
		props.put(JdbcSourceConnectorConfig.CONNECTION_PASSWORD_CONFIG, "sesame");
		props.put(JdbcSourceConnectorConfig.TABLE_WHITELIST_CONFIG, "clntpf");
		props.put(JdbcSourceConnectorConfig.MODE_CONFIG,JdbcSourceConnectorConfig.MODE_TIMESTAMP );
		props.put(JdbcSourceConnectorConfig.TIMESTAMP_COLUMN_NAME_CONFIG, "DATIME");
		props.put(JdbcSourceConnectorConfig.VALIDATE_NON_NULL_CONFIG, "false");
		props.put(JdbcSourceConnectorConfig.TOPIC_PREFIX_CONFIG, "jdbc-mysql-");
		props.put(JdbcSourceConnectorConfig.MODE_INCREMENTING,JdbcSourceConnectorConfig.MODE_TIMESTAMP_INCREMENTING);
		return props;
	}
}