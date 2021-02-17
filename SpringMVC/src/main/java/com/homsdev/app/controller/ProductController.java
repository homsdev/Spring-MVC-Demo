package com.homsdev.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.homsdev.app.service.ProductService;

@Controller
@RequestMapping("market")
public class ProductController {

//	it is not a good practice to connect two layers directly that´s why 
//	the use of an interface if we want to change the implementation we only need to adjust
//	the persistent layer

	@Autowired // Service Object to handle business operations
	ProductService productService;

	@RequestMapping("/products")
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping("/update/stock")
	public String updateStock() {
		productService.updateAllStock();
		return "redirect:/market/products";
	}
	
	//URI Template
	@RequestMapping("/products/{category}")
	public String getProductsByCategory(Model model,@PathVariable("category")String productCategory) {
		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		return "products";
	}
	
	@RequestMapping("/products/filter/{params}")
	public String getProductsByFilter(Model model,@MatrixVariable(pathVar = "params") Map<String,List<String>>filterParams) {
		model.addAttribute("products", productService.getProductByFilter(filterParams));
		return "products";
	}
}
