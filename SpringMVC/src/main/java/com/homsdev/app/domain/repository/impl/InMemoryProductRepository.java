package com.homsdev.app.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.homsdev.app.domain.Product;
import com.homsdev.app.domain.repository.ProductRepository;
import com.homsdev.app.exception.ProductNotFoundException;

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

	// Filter products by category, price and brand
	public List<Product> getProductsByPrice(Map<String, List<String>> filterPrice) {
		String SQL = "SELECT * FROM products WHERE manufacturer IN (:brand) AND CATEGORY = :category AND UNIT_PRICE BETWEEN :low AND :high";
		return jdbcTemplate.query(SQL, filterPrice, new ProductMapper());
	}

	// Return info from a product which satisfies the ID
	public Product getProductByID(String productID) {
		String SQL = "SELECT * FROM products WHERE ID= :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", productID);
		try{
			return jdbcTemplate.queryForObject(SQL, params, new ProductMapper());
		}catch(DataAccessException e) {
			throw new ProductNotFoundException(productID);
		}
		
	}

	//Add new product to DB
	public void addProduct(Product product) {
		String SQL = "INSERT INTO PRODUCTS (ID, "
                + "NAME,"
                + "DESCRIPTION,"
                + "UNIT_PRICE,"
                + "MANUFACTURER,"
                + "CATEGORY,"
                + "CONDITION,"
                + "UNITS_IN_STOCK,"
                + "UNITS_IN_ORDER,"
                + "DISCONTINUED) "
                + "VALUES (:id, :name, :desc, :price,:manufacturer, :category, :condition, :inStock,:inOrder, :discontinued)";
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("id", product.getProductID());
		params.put("name", product.getName());
		params.put("desc", product.getDescription());
		params.put("price", product.getUnitPrice());
		params.put("manufacturer",product.getManufacturer());
		params.put("category", product.getCategory());
		params.put("condition", product.getCondition());
		params.put("inStock",product.getUnitsInStock());
		params.put("inOrder",product.getUnitsInOrder());
		params.put("discontinued", product.isDiscontinued());
		jdbcTemplate.update(SQL, params);
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
