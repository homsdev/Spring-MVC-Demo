package com.homsdev.app.dto;

public class CartItemDTO {
	private String ID;
	private String productID;
	private String cartID;
	private int quantity;

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getCartID() {
		return cartID;
	}

	public void setCartID(String cartID) {
		this.cartID = cartID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItemDTO [ID=" + ID + ", productID=" + productID + ", cartID=" + cartID + ", quantity=" + quantity
				+ "]";
	}

	
}
