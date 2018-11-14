package com.capgemini.loginregister.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.loginregister.model.UserDTO;
import com.capgemini.loginregister.repository.DynamoDbRepo;

@Service
public class Serviceimpl implements IService {
	@Autowired
	DynamoDbRepo repository;

	@Override
	public boolean isUser(String email, String password) {
		UserDTO user= repository.getUserFromDynamoDB(email);
		if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	public void save(UserDTO user) {
		repository.insertIntoDynamoDB(user);
	}

	@Override
	public boolean isExistingUser(String email) {
		boolean result= repository.isExists(email);
		if(result==true) {
			return true;
		}
		return false;
	}


}
