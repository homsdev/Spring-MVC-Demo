package com.homsdev.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.homsdev.app.service.ProductService;

@Controller
@RequestMapping("market")
public class ProductController {

//	it is not a good practice to connect two layers directly that´s why 
//	the use of an interface if we want to change the implementation we only need to adjust
//	the persistent layer

	@Autowired // Service Object to handle business operations
	ProductService productService;

	// Retrieves all available products in DB
	@RequestMapping("/products")
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	// Updates products which have less than 1000 units in stock
	@RequestMapping("/update/stock")
	public String updateStock() {
		productService.updateAllStock();
		return "redirect:/market/products";
	}

	// Retrieves info from products in the same category
	@RequestMapping("/products/{category}") // URI Template
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		return "products";
	}

	// Retrieves info from products that satisfy the filter specification
	@RequestMapping("/products/filter/{params}") // Dynamic filter
	public String getProductsByFilter(Model model,
			@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams) {
		model.addAttribute("products", productService.getProductByFilter(filterParams));
		return "products";
	}

	//Filter products by category, price and brand
	@RequestMapping("/products/{category}/{price}")
	public String filterProducts(Model model, @MatrixVariable(pathVar = "price") Map<String, List<String>> filterPrice,
			@RequestParam("brand") String brand, @PathVariable("category") String productCategory) {
		List<String> brandParameter = new ArrayList<String>();
		brandParameter.add(brand);
		List<String> category = new ArrayList<String>();
		category.add(productCategory);

		filterPrice.put("brand", brandParameter);
		filterPrice.put("category", category);

		model.addAttribute("products", productService.getProductByPrice(filterPrice));
		return "products";
	}

	// Retrieves info from a singular product
	@RequestMapping("/product")
	public String getProductByID(Model model, @RequestParam("id") String productID) {
		model.addAttribute("product", productService.getProductByID(productID));
		return "product";
	}
}
