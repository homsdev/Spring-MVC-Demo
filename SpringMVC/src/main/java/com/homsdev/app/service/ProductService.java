package com.homsdev.app.service;

import java.util.List;

import com.homsdev.app.domain.Product;

public interface ProductService {
	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String Category);
	void updateAllStock();
}
