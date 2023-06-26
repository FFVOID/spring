package com.shopmax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	//get방식으로 들어온 정보를 매칭해 실행
	@GetMapping(value = "/")
	public String main() {
		return "main";
	}
}
