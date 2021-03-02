package com.homsdev.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.homsdev.app.domain.Customer;
import com.homsdev.app.service.CustomerService;

@Controller
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping("/list")
	public String customerList(Model model) {
		model.addAttribute("customers", customerService.getAllCustomers());
		return "customers";
	}

	// Sends the form to add a new customer to client
	@RequestMapping("/add")
	public String getNewCustomerForm(Model model) {
		Customer newCustomer = new Customer();
		model.addAttribute("newCustomer", newCustomer);
		return "formCustomer";
	}

	//Collect info from the form and send it to DB
	@PostMapping("/add")
	public String addNewCustomer(@ModelAttribute("newCustomer") Customer newCustomer) {
		customerService.addNewCustomer(newCustomer);
		return "redirect:/customer/list";
	}

}
