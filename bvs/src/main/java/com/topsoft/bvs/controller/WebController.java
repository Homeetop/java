package com.topsoft.bvs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
	@GetMapping("/home")
	public String showhome() {
		return "index";
	}
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}

}
