package com.cuong.phonestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cuong.phonestore.model.Product;
import com.cuong.phonestore.services.CategoryService;
import com.cuong.phonestore.services.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private ProductService productService;
	private CategoryService categoryService;
	
	@Autowired
	public ProductController(ProductService productService, CategoryService categoryService) {
		super();
		this.productService = productService;
		this.categoryService = categoryService;
	}

	@RequestMapping(value="/{page}/{size}")
	public String products(@PathVariable("page") Integer page, @PathVariable("size") Integer pageSize, 
			@RequestParam(value = "cID", required = false) String categoryID, Model model) {
		if (page == null || pageSize == null) {
            throw new IllegalArgumentException("Page and size parameters are required");
        }
		model.addAttribute("categoryList", categoryService.findAll());
		if (categoryID != null && !categoryID.isEmpty()) {
           List<Product> productList= productService.findAllByCategory(PageRequest.of(page, pageSize), categoryService.findByID(categoryID));
            if (productList == null) {
                throw new IllegalArgumentException("Invalid category parameter");
            }
            model.addAttribute("productList", productList);
		} else {
			model.addAttribute("productList", productService.findAll(PageRequest.of(page, pageSize)));
		}
		return "products";
	}
	
	@RequestMapping(value="/detail/{productId}")
	public String productDetail(Model model, @PathVariable("productId") String productId) {
		model.addAttribute("productDetail", productService.findById(productId));
		return "productdetail";
	}

}
