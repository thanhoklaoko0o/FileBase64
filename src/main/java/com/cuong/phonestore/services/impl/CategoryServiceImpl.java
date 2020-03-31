package com.cuong.phonestore.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuong.phonestore.model.Category;
import com.cuong.phonestore.repository.CategoryRepository;
import com.cuong.phonestore.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public Category findByID(String id) {
		Optional<Category> optional = categoryRepository.findById(id);
		return optional.isPresent() ?  (Category) optional.get() : null;
	}

}
