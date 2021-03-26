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
import com.homsdev.app.service.impl.ProductServiceImpl;


@Repository
public class InMemoryCartRepository implements CartRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	

	@Override
	public void create(CartDTO cartDTO) {
		// 
		String SQL_CART= "INSERT INTO CART (ID) VALUES (:id)";
		String SQL_ITEM= "INSERT INTO CART_ITEM (ID,PRODUCT_ID,CART_ID,QUANTITY)"
				+"VALUES ("
				+":id"
				+":productID"
				+":cartID"
				+":quantity"
				+ ")";
		Map<String,Object> params= new HashMap<String,Object>();
		params.put("id", cartDTO.getID());
		jdbcTemplate.update(SQL_CART, params);
		
		cartDTO.getCartItems().stream()
		.forEach(cartItem->{
			Map<String,Object> itemParams= new HashMap<String, Object>();
			itemParams.put("id", cartItem.getID());
			itemParams.put("productID", cartItem.getProductID());
			itemParams.put("cartID", cartItem.getCartID());
			itemParams.put("quantity", cartDTO.getID());
			jdbcTemplate.update(SQL_ITEM, itemParams);
		});
		
	}

	@Override
	public Cart getCart(String ID) {
		// TODO Auto-generated method stub
		String SQL_GET_CART= "SELECT * FROM CART WHERE ID = :id ";
		String SQL_GET_ITEMS= "SELECT * FROM CART_ITEM WHERE CART_ID = :id";
		
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("id", ID);
		Cart cart=jdbcTemplate.queryForObject(SQL_GET_CART, params,new CartMapper());
		List<CartItem> items= jdbcTemplate.query(SQL_GET_ITEMS, params,new ItemsMapper());
		cart.setCartItems(items);
		return cart;
	}

	@Override
	public void update(String ID, CartDTO cartDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String ID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addItem(String ID, CartItemDTO item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeItem(String cartID, String productID) {
		// TODO Auto-generated method stub

	}
	
	private static class CartMapper implements RowMapper<Cart>{

		@Override
		public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cart cart= new Cart();
			cart.setID(rs.getString("ID"));
			return null;
		}
	}
	
	private static class ItemsMapper implements RowMapper<CartItem>{

		@Override
		public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductService productService = new ProductServiceImpl();
			Product product=productService.getProductByID(rs.getString("PRODUCT_ID"));
			CartItem item= new CartItem();
			item.setCartID(rs.getString("CART_ID"));
			item.setID(rs.getString("ID"));
			item.setProduct(product);
			item.setQuantity(rs.getInt("QUANTITY"));
			item.setTotalprice(item.updatePrice());
			return item;
		}
		
	}
}
