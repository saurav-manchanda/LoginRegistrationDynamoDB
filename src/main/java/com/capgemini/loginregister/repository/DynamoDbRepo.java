package com.capgemini.loginregister.repository;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.capgemini.loginregister.model.UserDTO;

@Repository
public class DynamoDbRepo {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDbRepo.class);
	
	@Autowired
	private DynamoDBMapper mapper;
	
	public void insertIntoDynamoDB(UserDTO user) {
		mapper.save(user);
	}
	
	
	public void updateStudentDetails(UserDTO user) {
		try {
			mapper.save(user, buildDynamoDBSaveExpression(user));;
		}catch(ConditionalCheckFailedException exception){
		LOGGER.error("invalid data- "+ exception.getMessage());
		}
	}

	public void deleteStudentDetails(UserDTO user) {
		mapper.delete(user);
	}
	
	public boolean isExists(String email) {
		UserDTO user=mapper.load(UserDTO.class, email);
		if(user!=null) {
			return true;
		}
		return false;
	}
	
	private DynamoDBSaveExpression buildDynamoDBSaveExpression(UserDTO user) {
		DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
		Map<String, ExpectedAttributeValue> expected = new HashMap<>();
		expected.put("id", new ExpectedAttributeValue(new AttributeValue())
				.withComparisonOperator(ComparisonOperator.EQ));
		saveExpression.setExpected(expected);
		return saveExpression;
	}
	public UserDTO getUserFromDynamoDB(String hashkey) {
		UserDTO user=mapper.load(UserDTO.class, hashkey);
		return  user;
	}

}
