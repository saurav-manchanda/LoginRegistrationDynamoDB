package com.capgemini.loginregister.model;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
@DynamoDBTable(tableName = "user")
public class UserDTO implements Serializable {

	public UserDTO() {
		super();
	}
	public UserDTO(String email, String userName, String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String email;
	private String password;
	@DynamoDBHashKey(attributeName = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@DynamoDBAttribute(attributeName="username")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@DynamoDBAttribute(attributeName = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
