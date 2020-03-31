package com.cuong.phonestore.services;

import java.util.List;

import com.cuong.phonestore.model.Category;

public interface CategoryService {

	List<Category> findAll();
	
	Category findByID(String id);
}
