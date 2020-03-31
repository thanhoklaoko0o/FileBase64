package com.cuong.phonestore.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cuong.phonestore.model.Product;
import com.cuong.phonestore.services.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="", method=RequestMethod.GET)
	public String index(Model model, HttpSession session) {
		ArrayList<Product> cart = new ArrayList<>();
		if(session.getAttribute("cart") != null) {
			cart = (ArrayList<Product>) session.getAttribute("cart");
		}
		model.addAttribute("cart", cart);
		model.addAttribute("total", cartService.totalPrice(cart));
		return "cart";
	}
	
	@RequestMapping(value="/add/{productId}/{quantity}", method=RequestMethod.GET)
	@ResponseBody
	public int addToCart(HttpSession session, @PathVariable("productId") String productId, 
			@PathVariable("quantity") int quantity) {
		@SuppressWarnings("unchecked")
		ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
		if(cart == null) {
			cart = new ArrayList<>();
		}
		cart = cartService.addToCart(cart, productId, quantity);
		session.setAttribute("cart", cart);
		session.setAttribute("amount", cartService.getAmountCart(cart));
		return cartService.getAmountCart(cart);
	}
	
	
	//return total
	@RequestMapping(value="/update/{productId}/{quantity}", method=RequestMethod.GET)
	@ResponseBody
	public Long updateCart(HttpSession session, @PathVariable("productId") String productId, 
			@PathVariable("quantity") int quantity) {
		@SuppressWarnings("unchecked")
		ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
		if(cart == null) {
			return 0L;
		} else {
			cart = cartService.updateCart(cart, productId, quantity);
		}
		session.setAttribute("cart", cart);
		session.setAttribute("amount", cartService.getAmountCart(cart));
		return cartService.totalPrice(cart);
	}
}
