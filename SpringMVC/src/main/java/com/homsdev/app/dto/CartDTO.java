package com.homsdev.app.dto;

import java.util.List;

public class CartDTO {
	private String ID;
	private List<CartItemDTO> cartItems;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public List<CartItemDTO> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemDTO> cartItems) {
		this.cartItems = cartItems;
	}

}
