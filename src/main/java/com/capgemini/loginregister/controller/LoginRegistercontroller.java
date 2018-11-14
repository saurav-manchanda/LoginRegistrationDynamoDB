package com.capgemini.loginregister.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.loginregister.model.ResponseDTO;
import com.capgemini.loginregister.model.UserDTO;
import com.capgemini.loginregister.service.IService;


@RestController
public class LoginRegistercontroller {
	
	@Autowired
	IService service;

	public static final Logger logger= LoggerFactory.getLogger(LoginRegistercontroller.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO> login(@RequestParam String email,@RequestParam String password){
		boolean result=service.isUser(email,password);
		if(result==true)
		return new ResponseEntity(new ResponseDTO("successfully login ",200),HttpStatus.OK);
		return new ResponseEntity(new ResponseDTO("wrong credentials",404),HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO> register(@RequestBody UserDTO userDto){
		boolean isExistingUser=service.isExistingUser(userDto.getEmail());
		if(isExistingUser==true) {
			return new ResponseEntity(new ResponseDTO("user already registered ",404),HttpStatus.CONFLICT);
		}
		service.save(userDto);
		return new ResponseEntity(new ResponseDTO("successfully registered ",200),HttpStatus.OK);
	}
}
