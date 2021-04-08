package com.homsdev.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.homsdev.app.domain.Cart;
import com.homsdev.app.dto.CartDTO;
import com.homsdev.app.exception.RecordNotFoundException;
import com.homsdev.app.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartRESTController {

	@Autowired
	private CartService cartService;

	@RequestMapping("/")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createNewCart(@RequestBody CartDTO newCart) {
		cartService.create(newCart);
	}

	@RequestMapping("/{cartID}")
	public Cart getCart(@PathVariable("cartID") String cartID) {
		try {
			return cartService.getCart(cartID);
		}catch(Exception e) {
			throw new RecordNotFoundException("Cannot find cart with ID: "+cartID);
		}
	}

	@PutMapping("/{cartID}")
	@ResponseStatus(value = HttpStatus.OK)
	public void updateCart(@PathVariable("cartID") String cartID, @RequestBody CartDTO cartUpdated) {
		try {
			cartUpdated.setID(cartID);
			cartService.update(cartID, cartUpdated);			
		}catch(Exception e){
			throw new RecordNotFoundException("Cannot find cart with ID: "+cartID);
		}
	}

	@DeleteMapping("/{cartID}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteCart(@PathVariable("cartID") String cartID) {
		try {
			cartService.delete(cartID);					
		}catch(Exception e){
			throw new RecordNotFoundException("Cannot delete cart with ID: "+cartID);
		}
		
	}

}
