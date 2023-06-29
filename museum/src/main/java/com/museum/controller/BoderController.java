package com.museum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoderController {
	
	@GetMapping(value ="/borders/list")
	public String Border() {
		return "/border/list";
	}
}
