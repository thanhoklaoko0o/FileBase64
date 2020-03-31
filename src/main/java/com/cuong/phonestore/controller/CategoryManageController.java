package com.cuong.phonestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cuong.phonestore.model.Category;
import com.cuong.phonestore.services.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/category")
public class CategoryManageController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getListBrands(){
        return new ResponseEntity<List<Category>>(categoryService.findAll(), HttpStatus.OK);
	}

}
