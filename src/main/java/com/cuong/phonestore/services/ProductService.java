package com.cuong.phonestore.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.cuong.phonestore.model.Brand;
import com.cuong.phonestore.model.Category;
import com.cuong.phonestore.model.Product;

public interface ProductService {

	Product findById(String id);
	
	List<Product> findAll(Pageable pageable);

    List<Product> findAllByBrand(Pageable pageable, Brand brand);
    
    List<Product> findAllByCategory(Pageable pageable, Category category);

    List<Product> findTop8ByOrderByDateCreatedDesc();

    /*List<Product> findTop8ByOrderBySellCountDesc();

    List<Product> findTop8ByOrderBySellCountDescCacheRefresh();*/

    List<Product> getRelatedProducts(Brand brand, String id);

    List<Product> searchProducts(String keyword, Integer page, Integer size);
    
    void create(Product product);
    
    void edit(Product product, String oldImagePath);
    
    void update(Product product);
    
    void delete(Product product);
    
    boolean exitsById(String productId);
    
    Long countAll();
}
