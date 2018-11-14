package com.capgemini.loginregister.service;

import com.capgemini.loginregister.model.UserDTO;

public interface IService {

	boolean isUser(String email, String password);

	void save(UserDTO userDto);

	boolean isExistingUser(String email);

}
