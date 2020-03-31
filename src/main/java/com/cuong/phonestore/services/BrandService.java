package com.cuong.phonestore.services;

import java.util.List;

import com.cuong.phonestore.model.Brand;

public interface BrandService {

	List<Brand> findAll();
	
	Brand findByID(String id);
}
