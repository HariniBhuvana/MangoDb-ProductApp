package com.capgemini.productapp.exeception;

public class ProductNotFoundException extends Exception {
	
	public ProductNotFoundException() {		
	}
	
	public ProductNotFoundException(String message) {
		super(message);
	}
}
