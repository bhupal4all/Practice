package com.ranga.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsRestController {

	@RequestMapping("/hello")
	public String sayHello() {
		return "Welcome to Rest Services";
	}
}
