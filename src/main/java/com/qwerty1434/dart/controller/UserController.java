package com.qwerty1434.dart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserController {
	@GetMapping("/SearchAll")
	public String NextPage() {
		System.out.println("We Must Go To Next Page");
		return "index";
	}
}
