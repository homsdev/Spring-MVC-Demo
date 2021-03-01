package com.homsdev.app.service;

import java.util.List;
import java.util.Map;

import com.homsdev.app.domain.Product;

public interface ProductService {
	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String Category);
	List<Product> getProductByFilter(Map<String,List<String>>filterParams);
	List<Product> getProductByPrice(Map<String,List<String>>filterPrice);
	Product getProductByID(String productID);
	void addProduct(Product product);
	void updateAllStock();
}
