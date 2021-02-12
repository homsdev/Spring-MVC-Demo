package com.homsdev.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.homsdev.app.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/customers")
	public String customerList(Model model) {
		
		model.addAttribute("customers", customerService.getAllCustomers());
		return "customers";
	}
	
	
}
