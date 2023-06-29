package com.museum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {
	
	@GetMapping(value = "/reservation")
	public String reservation() {
		return "/reservation/itemReservation";
	}
}
