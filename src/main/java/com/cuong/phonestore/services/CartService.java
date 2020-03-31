package com.cuong.phonestore.services;

import java.util.ArrayList;

import com.cuong.phonestore.model.Product;

public interface CartService {

	ArrayList<Product> addToCart(ArrayList<Product> cart, String productID, int quantity);
	
	ArrayList<Product> updateCart(ArrayList<Product> cart, String productID, int quantity);
	
	boolean removeCart(ArrayList<Product> cart);
	
	Long totalPrice(ArrayList<Product> cart);
	
	int getAmountCart(ArrayList<Product> cart);
}
