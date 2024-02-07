package com.nighthawk.spring_portfolio.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aichatbot")
public class AIChatbotController {

	@GetMapping("")
	public String greeting() {
		return "Hello From Chatbot AI.";
	}
	
	@GetMapping("/test")
	public String test() {
		return "Test.";
	}
}
