package com.homsdev.app.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.homsdev.app.domain.Cart;
import com.homsdev.app.domain.repository.CartRepository;
import com.homsdev.app.dto.CartDTO;
import com.homsdev.app.dto.CartItemDTO;

import com.homsdev.app.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Override
	public String create(CartDTO cartDTO) {
		cartDTO.setID(UUID.randomUUID().toString());
		List<CartItemDTO> itemsInCart= cartDTO.getCartItems();
		for(CartItemDTO item : itemsInCart) {
			item.setID(UUID.randomUUID().toString());
		}
		cartRepository.create(cartDTO);
		return cartDTO.getID();
	}

	@Override
	public Cart getCart(String ID) {
		return cartRepository.getCart(ID);
	}

	@Override
	public void update(String ID, CartDTO cartDTO) {

		cartRepository.update(ID, cartDTO);

	}

	@Override
	public void delete(String ID) {
		// TODO Auto-generated method stub
		cartRepository.delete(ID);
	}

	@Override
	public void addItem(String ID, CartItemDTO item) {
		// TODO Auto-generated method stub
		cartRepository.addItem(ID, item);
	}

	@Override
	public void removeItem(String cartID, String productID) {
		// TODO Auto-generated method stub
		cartRepository.removeItem(cartID, productID);
	}

}
