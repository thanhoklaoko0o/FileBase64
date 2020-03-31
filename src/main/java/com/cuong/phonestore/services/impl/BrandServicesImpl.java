package com.cuong.phonestore.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuong.phonestore.model.Brand;
import com.cuong.phonestore.repository.BrandRepository;
import com.cuong.phonestore.services.BrandService;

@Service
public class BrandServicesImpl implements BrandService {
	
	private BrandRepository brandReposity;
	
	@Autowired
    public BrandServicesImpl(BrandRepository brandRepository) {
        this.brandReposity = brandRepository;
    }

	@Override
	public List<Brand> findAll() {
		return (List<Brand>) brandReposity.findAll();
	}

	@Override
	public Brand findByID(String id) {
		Optional<Brand> optional = brandReposity.findById(id);
		return optional.isPresent() ?  (Brand) optional.get() : null;
	}
	
}
