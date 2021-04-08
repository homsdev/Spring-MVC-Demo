package com.homsdev.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "Cart not found or does not exist")
public class CartNotFoundException extends Exception{
	private static final long serialVersionUID = -6141558721726211989L;
}
