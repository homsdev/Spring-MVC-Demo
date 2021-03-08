package com.homsdev.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.homsdev.app.domain.Product;
import com.homsdev.app.exception.NoProductsFoundUnderCategoryException;
import com.homsdev.app.exception.ProductNotFoundException;
import com.homsdev.app.service.ProductService;

@Controller
@RequestMapping("market")
public class ProductController {

//	it is not a good practice to connect two layers directly that´s why 
//	the use of an interface if we want to change the implementation we only need to adjust
//	the persistent layer

	@Autowired // Service Object to handle business operations
	ProductService productService;

	//Returns a error message when a product is not found
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req,ProductNotFoundException exception) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("alert",exception.getProductId());
		mav.addObject("url",req.getRequestURL()+"?"+req.getQueryString());
		mav.setViewName("errorPage");
		return mav;
	}
	
	// Whitelisting fields binding
	@InitBinder
	public void inicialiseBinder(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields("productID", "name", "unitPrice", "description", "manufacturer", "category",
				"unitsInStock", "condition");
	}

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

		List<Product> products = productService.getProductsByCategory(productCategory);
		if (products == null || products.isEmpty()) {
			throw new NoProductsFoundUnderCategoryException();
		}
		model.addAttribute("products", products);
		return "products";
	}

	// Retrieves info from products that satisfy the filter specification
	@RequestMapping("/products/filter/{params}") // Matrix Variables
	public String getProductsByFilter(Model model,
			@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams) {
		model.addAttribute("products", productService.getProductByFilter(filterParams));
		return "products";
	}

	// Filter products by category, price and brand
	@RequestMapping("/products/{category}/{price}")//URI Template,matrix variables and req params
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

	// Send form to add new product info
	@RequestMapping("/products/add")
	public String getAddNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("newProduct", product);
		return "formProduct";
	}

	// Send info collected in the form to DB
	@PostMapping("/products/add")
	public String addNewProduct(@ModelAttribute("newProduct") Product newProduct, BindingResult result) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind dissalllowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		productService.addProduct(newProduct);
		return "redirect:/market/products";
	}
	
	@RequestMapping("/products/invalidPromoCode")
	public String invalidPromoCode(Model model) {
		model.addAttribute("alert","Offer Code Incorrect");
		return"errorPage";
	}
}
