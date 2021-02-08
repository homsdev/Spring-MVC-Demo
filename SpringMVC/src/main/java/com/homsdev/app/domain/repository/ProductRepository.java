package com.homsdev.app.domain.repository;

import java.util.List;

import com.homsdev.app.domain.Product;

// Defines the expected behavior of product repository
public interface ProductRepository {
	
	List<Product> getAllProducts();
	void updateStock(String productID,long noOfUnits);
}
