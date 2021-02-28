package com.homsdev.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homsdev.app.domain.Product;
import com.homsdev.app.domain.repository.ProductRepository;
import com.homsdev.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	// uses a repository object to data access
	@Autowired
	ProductRepository productRepository;

	// Updates all products which have less than 1000 units in Stock
	public void updateAllStock() {

		List<Product> products = productRepository.getAllProducts();

		for (Product product : products) {
			if (product.getUnitsInStock() < 1000) {
				productRepository.updateStock(product.getProductID(), product.getUnitsInStock() + 1000);
			}
		}

		productRepository.updateStock(null, 0);
	}

	// Get all products from an in-memory DB
	public List<Product> getAllProducts() {
		List<Product> allProducts = productRepository.getAllProducts();
		return allProducts;
	}

	// Get products from the same specified category
	public List<Product> getProductsByCategory(String Category) {
		return productRepository.getProductByCategory(Category);
	}

	// Get products which satisfies the filter options
	public List<Product> getProductByFilter(Map<String, List<String>> filterParams) {
		return productRepository.getProductByfilter(filterParams);
	}

	// Get singular product info
	// market/product?id=****
	public Product getProductByID(String productID) {
		return productRepository.getProductByID(productID);
	}

}
