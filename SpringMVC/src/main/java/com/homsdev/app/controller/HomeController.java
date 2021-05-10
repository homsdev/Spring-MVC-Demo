package com.homsdev.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String welcome(Model model,RedirectAttributes redirectAttributes) {
		model.addAttribute("greeting", "Welcome to Sprinkled");
		model.addAttribute("tagline","Java Spring MVC Web application developed by HomS");
		redirectAttributes.addFlashAttribute("greeting", "Welcome to Sprinkled");
		redirectAttributes.addFlashAttribute("tagline", "JAVA MVC Spring application developed by HomS");
		return "redirect:/welcome/greeting";
	}
	
	@RequestMapping("/welcome/greeting")
	public String greetin(Model model) {
		return "welcome";
	}
}
