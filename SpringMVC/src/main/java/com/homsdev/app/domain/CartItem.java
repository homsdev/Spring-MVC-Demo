package com.homsdev.app.domain;

import java.math.BigDecimal;

public class CartItem {
	private String ID;
	private Product product;
	private String cartID;
	private int quantity;
	private BigDecimal totalprice;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	
	public BigDecimal updatePrice() {
		BigDecimal price = this.product.getUnitPrice().multiply(new BigDecimal(this.quantity));
		return price;
	}
}
