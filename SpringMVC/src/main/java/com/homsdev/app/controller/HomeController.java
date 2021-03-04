package com.homsdev.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String welcome(Model model,RedirectAttributes redirectAttributes) {
		model.addAttribute("greeting", "Welcome to My First Spring MVC App");
		model.addAttribute("tagline","The one and only best Web App");
		redirectAttributes.addFlashAttribute("greeting", "Welcome to My First Spring MVC App");
		redirectAttributes.addFlashAttribute("tagline", "The one and only best Web App");
		return "redirect:/welcome/greeting";
	}
	
	@RequestMapping("/welcome/greeting")
	public String greetin(Model model) {
		return "welcome";
	}
}
