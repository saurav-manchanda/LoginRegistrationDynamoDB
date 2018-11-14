package com.capgemini.loginregister.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;


@Configuration
public class DynamoDBConfig {
	
	@Value("${amazon.accessKey}")
	private String awsAccessKey;
	
	@Value("${amazon.secret-key}")
	private String awsSecretKey;
	
	@Value("${amazon.region}")
	private String awsRegion;
	
	@Value("${amazon.dynamoEndPoint}")
	private String awsDynamoDbEndPoint;
	
	@Autowired
	private AmazonDynamoDB dynamoDB;
	
	@Bean
	public DynamoDBMapper mapper() {
		return new DynamoDBMapper(dynamoDB);
	}
	
	@Bean
	public AmazonDynamoDB amazonDyanamoDBConfig() {
		return AmazonDynamoDBAsyncClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoDbEndPoint, awsRegion))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
				.build();
				
	}

}
