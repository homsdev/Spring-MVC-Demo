package com.homsdev.app.service.impl;

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
	public void create(CartDTO cartDTO) {
		cartRepository.create(cartDTO);
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
