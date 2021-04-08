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

import com.homsdev.app.domain.Cart;
import com.homsdev.app.domain.CartItem;
import com.homsdev.app.domain.Product;
import com.homsdev.app.domain.repository.CartRepository;
import com.homsdev.app.dto.CartDTO;
import com.homsdev.app.dto.CartItemDTO;

import com.homsdev.app.service.ProductService;

@Repository
public class InMemoryCartRepository implements CartRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private ProductService productService;

	@Override
	public void create(CartDTO cartDTO) {
		String SQL_CART = "INSERT INTO CART (ID) VALUES (:id)";
		String SQL_ITEM = "INSERT INTO CART_ITEM (ID,PRODUCT_ID,CART_ID,QUANTITY)" + "VALUES (" + ":id ,"
				+ ":productID ," + ":cartID ," + ":quantity" + ")";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", cartDTO.getID());
		jdbcTemplate.update(SQL_CART, params);

		cartDTO.getCartItems().stream().forEach(cartItem -> {
			Map<String, Object> itemParams = new HashMap<String, Object>();
			itemParams.put("id", cartItem.getID());
			itemParams.put("productID", cartItem.getProductID());
			itemParams.put("cartID", cartDTO.getID());
			itemParams.put("quantity", cartItem.getQuantity());
			jdbcTemplate.update(SQL_ITEM, itemParams);
		});

	}

	@Override
	public Cart getCart(String ID) {
		String SQL_GET_CART = "SELECT * FROM CART WHERE ID = :id ";
		String SQL_GET_ITEMS = "SELECT * FROM CART_ITEM WHERE CART_ID = :id";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", ID);

		Cart cart = jdbcTemplate.queryForObject(SQL_GET_CART, params, new CartMapper());
		List<CartItem> items = jdbcTemplate.query(SQL_GET_ITEMS, params, new ItemsMapper(productService));
		cart.setCartItems(items);
		cart.updateGrandTotal();
		return cart;

	}

	@Override
	public void update(String ID, CartDTO cartDTO) {
		String SQL_UPDATE_CART = "UPDATE CART_ITEM SET " + "QUANTITY = :quantity ," + "PRODUCT_ID = :productID "
				+ "WHERE ID = :id AND CART_ID = :cartID";
		String SQL_GET_CART = "SELECT * FROM CART WHERE ID = :id";
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("id", ID);
		Cart cartExist = jdbcTemplate.queryForObject(SQL_GET_CART, searchParams, new CartMapper());
		System.out.println(cartDTO);
		if (cartExist != null) {
			cartDTO.getCartItems().forEach(item -> {

				Map<String, Object> updateParams = new HashMap<String, Object>();
				updateParams.put("quantity", item.getQuantity());
				updateParams.put("productID", item.getProductID());
				updateParams.put("id", item.getID());
				updateParams.put("cartID", ID);
				jdbcTemplate.update(SQL_UPDATE_CART, updateParams);
			});
		}
	}

	@Override
	public void delete(String ID) {
		String SQL_DELETE_ITEMS = "DELETE FROM CART_ITEM WHERE CART_ID = :id";
		String SQL_DELETE_CART = "DELETE FROM CART WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", ID);
		jdbcTemplate.update(SQL_DELETE_ITEMS, params);
		jdbcTemplate.update(SQL_DELETE_CART, params);
	}

	@Override
	public void addItem(String ID, CartItemDTO item) {
		String SQL_SEARCH_ITEM = "SELECT * FROM CART_ITEM WHERE CART_ID = :cartID AND PRODUCT_ID= :productID";
		Map<String, Object> searchParams = new HashMap<>();
		searchParams.put("cartId", ID);
		searchParams.put("productID", item.getProductID());

		CartItem itemExists = jdbcTemplate.queryForObject(SQL_SEARCH_ITEM, searchParams,
				new ItemsMapper(productService));
		if (itemExists != null) {
			String SQL_UPDATE_QUANTITY = "UPDATE CART_ITEM SET QUANTITY = :quantity WHERE CART_ID = :cartID AND PRODUCT_ID = :productID";
			itemExists.setQuantity(itemExists.getQuantity() + 1);
			Map<String, Object> updateParams = new HashMap<String, Object>();
			updateParams.put("quantity", itemExists.getQuantity());
			updateParams.put("cartID", ID);
			updateParams.put("productID", itemExists.getProduct().getProductID());
			jdbcTemplate.update(SQL_UPDATE_QUANTITY, updateParams);
		} else {
			String SQL_INSERT_ITEM = "INSERT INTO CART_ITEM (ID,PRODUCT_ID,CART_ID,QUANTITY)" + "VALUES (" + ":id ,"
					+ ":productID ," + ":cartID ," + ":quantity ," + ")";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", item.getID());
			params.put("productID", item.getProductID());
			params.put("cartID", item.getCartID());
			params.put("quantity", item.getQuantity());
			jdbcTemplate.update(SQL_INSERT_ITEM, params);
		}
	}

	@Override
	public void removeItem(String cartID, String productID) {
		String SQL_DELETE_ITEM = "DELETE FROM CART_ITEM WHERE CART_ID = :cartID AND PRODUCT_ID = :productID";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cartID", cartID);
		params.put("productID", productID);
		jdbcTemplate.update(SQL_DELETE_ITEM, params);
	}

	private static class CartMapper implements RowMapper<Cart> {

		@Override
		public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cart cart = new Cart();
			cart.setID(rs.getString("ID"));
			return cart;
		}
	}

	private static class ItemsMapper implements RowMapper<CartItem> {

		private ProductService productService;

		public ItemsMapper(ProductService productService) {
			this.productService = productService;
		}

		@Override
		public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = productService.getProductByID(rs.getString("PRODUCT_ID"));
			CartItem item = new CartItem();
			item.setCartID(rs.getString("CART_ID"));
			item.setID(rs.getString("ID"));
			item.setProduct(product);
			item.setQuantity(rs.getInt("QUANTITY"));
			item.setTotalprice(item.updatePrice());
			return item;
		}

	}
}
