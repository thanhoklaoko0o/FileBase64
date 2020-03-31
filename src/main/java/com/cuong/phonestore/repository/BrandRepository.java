package com.cuong.phonestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuong.phonestore.model.Brand;

@Repository
public interface BrandRepository extends CrudRepository<Brand, String> {

}
