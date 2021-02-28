package com.homsdev.app.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.homsdev.app.domain.Product;
import com.homsdev.app.domain.repository.ProductRepository;

@Repository
public class InMemoryProductRepository implements ProductRepository {

	// Bean created to communicate with DB
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	// communicates with an in-memory database to retrieve all the info from
	// products
	public List<Product> getAllProducts() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Product> result = jdbcTemplate.query("SELECT * FROM products", params, new ProductMapper());
		return result;
	}

	// Retrieves products which are into the same category
	public List<Product> getProductByCategory(String Category) {
		String SQL = "SELECT * FROM products WHERE category = :category";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("category", Category);
		List<Product> result = jdbcTemplate.query(SQL, params, new ProductMapper());
		return result;
	}

	// Communicates with a in-memory DB to update products
	public void updateStock(String productID, long noOfUnits) {
		String SQL = "UPDATE PRODUCTS SET UNITS_IN_STOCK= :unitsInStock WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitsInStock", noOfUnits);
		params.put("id", productID);
		jdbcTemplate.update(SQL, params);
	}

	// Filter products
	public List<Product> getProductByfilter(Map<String, List<String>> filterParams) {
		String SQL = "SELECT * FROM products WHERE category IN (:categories) AND manufacturer IN (:brands)";
		return jdbcTemplate.query(SQL, filterParams, new ProductMapper());
	}

	// Return info from a product which satisfies the ID
	public Product getProductByID(String productID) {
		String SQL = "SELECT * FROM products WHERE ID= :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", productID);
		return jdbcTemplate.queryForObject(SQL, params, new ProductMapper());
	}

	private static final class ProductMapper implements RowMapper<Product> {
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setProductID(rs.getString("ID"));
			product.setName(rs.getString("NAME"));
			product.setDescription(rs.getString("DESCRIPTION"));
			product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
			product.setManufacturer(rs.getString("MANUFACTURER"));
			product.setCategory(rs.getString("CATEGORY"));
			product.setCondition(rs.getString("CONDITION"));
			product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
			product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
			product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
			return product;
		}
	}

}
