package com.qwerty1434.dart.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qwerty1434.dart.dao.User;
import com.qwerty1434.dart.dto.UserDto;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/User")
public class UserController {
	@Autowired
	private User dao;
	
	@GetMapping("/SearchAll")
	public ResponseEntity<?> searchAll() throws SQLException{
		List<UserDto> result = dao.searchAll();
		System.out.println(result.toString());

		if(result != null) {
			return new ResponseEntity<List<UserDto>>(result,HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("FAIL",HttpStatus.NO_CONTENT);
		}
	}

}
