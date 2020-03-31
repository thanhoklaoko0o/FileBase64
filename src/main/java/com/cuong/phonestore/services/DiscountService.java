package com.cuong.phonestore.services;

import java.security.Principal;

import com.cuong.phonestore.model.Cart;


public interface DiscountService {
	 Cart applyDiscount(Principal principal, String code);
}
