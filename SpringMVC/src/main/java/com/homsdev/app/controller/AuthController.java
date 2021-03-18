package com.homsdev.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.homsdev.app.domain.User;
import com.homsdev.app.service.UserService;

@Controller
public class AuthController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/login")
	public String getLoginForm() {
		return "login";
	}
	
	@RequestMapping("/signup")
	public String getSignupForm(Model model)
	{
		User newUser= new User();
		model.addAttribute("newUser",newUser);
		return"signup";
	}
	
	@PostMapping("/signup")
	public String registerNewUser(@ModelAttribute("newUser") User newUser) {
		userService.registerUser(newUser);
		return "redirect:/login";
	}
	
}
