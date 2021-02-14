package com.homsdev.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping()
	public String welcome(Model model) {
		model.addAttribute("greeting", "Welcome to My First Spring MVC App");
		model.addAttribute("tagline","The one and only best Web App");
		return "welcome";
	}
}
