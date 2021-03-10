package com.homsdev.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String welcome(Model model,RedirectAttributes redirectAttributes) {
		model.addAttribute("greeting", "Welcome to eStore");
		model.addAttribute("tagline","Created with JAVA Spring MVC");
		redirectAttributes.addFlashAttribute("greeting", "Welcome to eStore");
		redirectAttributes.addFlashAttribute("tagline", "Created with JAVA Spring MVC");
		return "redirect:/welcome/greeting";
	}
	
	@RequestMapping("/welcome/greeting")
	public String greetin(Model model) {
		return "welcome";
	}
}
