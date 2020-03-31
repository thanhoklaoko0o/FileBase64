package com.cuong.phonestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cuong.phonestore.services.ProductService;

@Controller
public class IndexcController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("productsBestSeller", productService.findTop8ByOrderByDateCreatedDesc());
		return "home";
	}
	
	@RequestMapping(value = "/", params = "st")
	public String search(Model model, @RequestParam("st") String keyword) {
		model.addAttribute("productsBestSeller", productService.searchProducts(keyword, 0, 10));
		return "home";
	}
}
