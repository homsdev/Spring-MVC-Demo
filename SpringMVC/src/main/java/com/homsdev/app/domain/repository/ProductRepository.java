package com.homsdev.app.domain.repository;

import java.util.List;
import java.util.Map;

import com.homsdev.app.domain.Product;

// Defines the expected behavior of product repository
public interface ProductRepository {

	List<Product> getAllProducts();
	List<Product> getProductByCategory(String Category);
	List<Product> getProductByfilter(Map<String,List<String>>filterParams);
	List<Product> getProductsByPrice(Map<String,List<String>>filterPrice);
	Product getProductByID(String productID);
	void updateStock(String productID, long noOfUnits);
}
