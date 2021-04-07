package com.homsdev.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.homsdev.app.domain.Cart;
import com.homsdev.app.dto.CartDTO;
import com.homsdev.app.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartRESTController {

	@Autowired
	private CartService cartService;

	@RequestMapping("/")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createNewCart(@RequestBody CartDTO newCart) {
		System.out.println(newCart.toString());
		cartService.create(newCart);
	}

	@RequestMapping("/{cartID}")
	public Cart getCart(@PathVariable("cartID") String cartID) {
		return cartService.getCart(cartID);
	}

	@PutMapping("/{cartID}")
	@ResponseStatus(value = HttpStatus.OK)
	public void updateCart(@PathVariable("cartID") String cartID, @RequestBody CartDTO cartUpdated) {
		cartUpdated.setID(cartID);
		cartService.update(cartID, cartUpdated);
	}
}
