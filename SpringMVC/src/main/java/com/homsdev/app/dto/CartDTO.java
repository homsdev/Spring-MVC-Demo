package com.homsdev.app.dto;

import java.util.List;

public class CartDTO {
	private String ID;
	private List<CartItemDTO> cartItems;

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public List<CartItemDTO> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemDTO> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		return "CartDTO [ID=" + ID + ", cartItems=" + cartItems + "]";
	}

}
