package com.cuong.phonestore.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cuong.phonestore.model.Brand;
import com.cuong.phonestore.model.Category;
import com.cuong.phonestore.model.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, String>{
	
	Product findByProductID(String id);

	List<Product> findAllByBrand(Pageable pageable, Brand brand);
	
	List<Product> findAllByCategory(Pageable pageable, Category cateory);

    List<Product> findTop8ByOrderByDateCreatedDesc();

    /*List<Product> findTop8ByOrderBySellCountDesc();*/

   /* List<Product> findTop8ByBrandAndIdIsNotOrderBySellCountDesc(Brand brand, String id);

    List<Product> findAllByProductCategoryIsNotOrderBySellCountDesc(Brand brand, Pageable pageable);*/

    List<Product> findAllByProductNameContaining(String name, Pageable pageable);
}
