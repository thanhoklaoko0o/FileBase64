package com.cuong.phonestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cuong.phonestore.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String>{

}
