package com.museum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	@GetMapping(value = "/members/login")
	public String login() {
		return "/member/memLoginForm";
	}
}
