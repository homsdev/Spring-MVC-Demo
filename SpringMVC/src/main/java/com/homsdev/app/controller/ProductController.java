package com.homsdev.app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.homsdev.app.domain.repository.ProductRepository;
import com.homsdev.app.service.ProductService;

@Controller
public class ProductController {

//	it is not a good practice to connect two layers directly that´s why 
//	the use of an interface if we want to change the implementation we only need to adjust
//	the persistent layer
	@Autowired//Dependency injection from @Repository annotation
	ProductRepository productRepository;
	
	@Autowired//Service Object to handle business operations
	ProductService productService;
	
	@RequestMapping("/products")
	public String list(Model model) {
		model.addAttribute("products", productRepository.getAllProducts());
		return "products";
	}
	
	@RequestMapping("/update/stock")
	public String updateStock() {
		productService.updateAllStock();
		return "redirect:/products";
	}
}
