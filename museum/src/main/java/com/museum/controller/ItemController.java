package com.museum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
	
	@GetMapping(value = "/item/list")
	public String itemList() {
		return "/item/itemList";
	}
}
