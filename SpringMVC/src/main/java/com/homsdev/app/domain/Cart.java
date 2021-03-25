package com.homsdev.app.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class Cart {
	private String ID;
	private List<CartItem> cartItems;
	private BigDecimal grandTotal;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}
	
	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public CartItem getProductById(String productID) {
		return cartItems.stream()
		.filter(product->product.getProduct().getProductID().equals(productID))
		.findAny()
		.orElse(null);
	}
	
	public void updateGrandTotal() {
		this.grandTotal=cartItems.stream().map(x->x.getTotalprice()).reduce(BigDecimal.ZERO,BigDecimal::add);
		this.setGrandTotal(grandTotal);
	}
	
	
	

}
