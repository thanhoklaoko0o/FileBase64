package com.cuong.phonestore.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuong.phonestore.model.Product;
import com.cuong.phonestore.repository.ProductRepository;
import com.cuong.phonestore.services.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public ArrayList<Product> addToCart(ArrayList<Product> cart, String productID, int quantity) {
		quantity = quantity < 0 ? quantity * -1 : quantity;
		boolean isExisted = false;
		Product product = productRepository.findByProductID(productID);
		if (product != null && product.getQuantity() > 0) {
            for (Product cartItem : cart) {
                if (cartItem.getProductID().equals(productID)) {
                	quantity = (cartItem.getQuantity() + quantity) >= product.getQuantity()?product.getQuantity():cartItem.getQuantity() + quantity;
                	cartItem.setQuantity(quantity);
                    isExisted = true;
                }
            }
            if (!isExisted) {
            	quantity = quantity > product.getQuantity()?product.getQuantity():quantity;
            	product.setQuantity(quantity);
                cart.add(product);
            }
		}
        return cart;
	}

	@Override
	public ArrayList<Product> updateCart(ArrayList<Product> cart, String productID, int quantity) {
		Product product = productRepository.findByProductID(productID);
		if(product != null) {
			if (quantity <= 0) {
	                for (int i = 0; i < cart.size(); i++) {
	                    if (productID.equals(cart.get(i).getProductID())) {
	                        cart.remove(cart.get(i));
	                    }
	                } 
	        } else {
	            for (int i = 0; i < cart.size(); i++) {
	                if (cart.get(i).getProductID().equals(productID)) {
	                	quantity = quantity >= product.getQuantity()?product.getQuantity(): quantity;
	                    cart.get(i).setQuantity(quantity);
	                }
	            }
	        }
		}
        return cart;
	}

	@Override
	public boolean removeCart(ArrayList<Product> cart) {
		boolean daXoa = false;
        if (cart != null) {
            cart.removeAll(cart);
            daXoa = !daXoa;
        }
        return daXoa;
	}

	@Override
	public Long totalPrice(ArrayList<Product> cart) {
		Long totalPrice = 0L;
		for(Product cartItem: cart) {
			totalPrice += cartItem.getPrice() * cartItem.getQuantity();
		}
		return totalPrice;
	}
	
	@Override
	public int getAmountCart(ArrayList<Product> cart) {
		int amount = 0;
		for(Product cartItem: cart) {
			amount += cartItem.getQuantity();
		}
		return amount;
	}

}
