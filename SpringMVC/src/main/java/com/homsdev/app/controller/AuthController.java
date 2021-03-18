package com.homsdev.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.homsdev.app.domain.User;

@Controller
public class AuthController {

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
	
}
