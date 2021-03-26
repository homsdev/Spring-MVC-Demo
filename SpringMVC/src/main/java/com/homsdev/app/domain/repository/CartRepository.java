package com.homsdev.app.domain.repository;

import com.homsdev.app.domain.Cart;
import com.homsdev.app.dto.CartDTO;
import com.homsdev.app.dto.CartItemDTO;

public interface CartRepository {
	void create(CartDTO cartDTO);

	Cart getCart(String ID);

	void update(String ID, CartDTO cartDTO);

	void delete(String ID);

	void addItem(String ID, CartItemDTO item);

	void removeItem(String cartID, String productID);
}
