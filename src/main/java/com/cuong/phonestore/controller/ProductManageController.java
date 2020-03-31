package com.cuong.phonestore.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cuong.phonestore.model.Brand;
import com.cuong.phonestore.model.Product;
import com.cuong.phonestore.services.BrandService;
import com.cuong.phonestore.services.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/product")
public class ProductManageController {
	
	private ProductService productService;
	private BrandService brandService;
	

	@Autowired
	public ProductManageController(ProductService productService, BrandService brandService) {
		super();
		this.productService = productService;
		this.brandService = brandService;
	}


	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{page}/{size}", method = RequestMethod.GET)
	public ResponseEntity getAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
	                                 @RequestParam(value = "sort", required = false) String sort,
	                                 @RequestParam(value = "brand", required = false) String brandID) {

	        if (page == null || size == null) {
	            throw new IllegalArgumentException("Page and size parameters are required");
	        }
	        PageRequest pageRequest;
	        if (sort != null && !isBlank(sort)) {
	            Sort sortRequest = getSort(sort);
	            if (sortRequest == null) {
	                throw new IllegalArgumentException("Invalid sort parameter");
	            }
	            pageRequest = PageRequest.of(page, size, sortRequest);
	        } else {
	            pageRequest = PageRequest.of(page, size);
	        }

	        if (brandID != null && !isBlank(brandID)) {
	            Brand brand = brandService.findByID(brandID);
	            if (brand == null) {
	                throw new IllegalArgumentException("Invalid brandID parameter");
	            }
	            List returnList = productService.findAllByBrand(pageRequest, brand);
	            return new ResponseEntity<List>(returnList, HttpStatus.OK);
	        }

	        List returnList = productService.findAll(pageRequest);
	        return new ResponseEntity<>(returnList, HttpStatus.OK);
	    }


	    @SuppressWarnings("rawtypes")
		@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	    public ResponseEntity getById(@PathVariable("productId") String productId) {
	        Product product = productService.findById(productId);
	        if (product == null) {
	            return new ResponseEntity(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Product>(product, HttpStatus.OK);
	    }

	   /* @RequestMapping(value = "/product/related", method = RequestMethod.GET, params = "id")
	    public ResponseEntity getByRelated(@RequestParam("id") Long id) {
	        ProductDisplay productDisplay = productDisplayService.findById(id);
	        if (productDisplay == null) {
	            return new ResponseEntity(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<List>(productDisplayService.getRelatedProducts(productDisplay.getProductCategory(), id), HttpStatus.OK);
	    }*/

	    /*@RequestMapping(value = "/product/recent", method = RequestMethod.GET)
	    public ResponseEntity getByNewlyAdded() {
	        List returnList = productDisplayService.findTop8ByOrderByDateCreatedDesc();
	        return new ResponseEntity<List>(returnList, HttpStatus.OK);
	    }*/

	   /* @RequestMapping(value = "/product/mostselling", method = RequestMethod.GET)
	    public ResponseEntity getByMostSelling() {
	        List returnList = productDisplayService.findTop8ByOrderBySellCountDesc();
	        return new ResponseEntity<List>(returnList, HttpStatus.OK);
	    }*/


	    //TODO rebuild the logic
	   /* @RequestMapping(value = "/product/interested", method = RequestMethod.GET)
	    public ResponseEntity getByInterested() {
	        List returnList = productDisplayService.findTop8ByOrderBySellCountDesc();
	        return new ResponseEntity<List>(returnList, HttpStatus.OK);
	    }*/


	   /* @RequestMapping(value = "/product/search", method = RequestMethod.GET, params = {"page", "size", "keyword"})
	    public ResponseEntity searchProduct(@RequestParam("page") Integer page,
	                                        @RequestParam("size") Integer size,
	                                        @RequestParam("keyword") String keyword) {
	        List returnList = productDisplayService.searchProducts(keyword, page, size);
	        return new ResponseEntity<List>(returnList, HttpStatus.OK);
	    }*/

	    private boolean isBlank(String param) {
	        return param.isEmpty() || param.trim().equals("");
	    }


	    //A better way to do this is storing sorting options in the database
	    //and sending those options to the client. Later then the client
	    //sends the parameter based upon that.
	    private Sort getSort(String sort) {
	        switch (sort) {
	            case "lowest":
	                return new Sort(Sort.Direction.ASC, "price");
	            case "highest":
	                return new Sort(Sort.Direction.DESC, "price");
	            case "name":
	                return new Sort(Sort.Direction.ASC, "name");
	            default:
	                return null;
	        }
	    }
	    
	    
		@RequestMapping(value = "", method = RequestMethod.POST)
		@ResponseStatus(HttpStatus.OK)
		public ResponseEntity<Product> createProduct(@RequestBody Product product){
			String productID = String.valueOf(new Date().getTime());
			product.setProductID(productID);
			System.out.println("Creating product " + product.getProductName());
			//check isBrandExist
			if(productService.exitsById(product.getProductID())) {
				return new ResponseEntity<Product>(product, HttpStatus.BAD_REQUEST);
			}
			productService.create(product);
			return new ResponseEntity<Product>(product, HttpStatus.CREATED);
		}
		
		/**
		 * Update product
		 * @param product
		 * @return
		 */
		@RequestMapping(value = "", method = RequestMethod.PUT)
		@ResponseStatus(HttpStatus.OK)
		public ResponseEntity<Product> updateProduct(@RequestBody Product product){
			Product oldProduct = productService.findById(product.getProductID());
			if(oldProduct == null) {
				System.out.println("Product with id " + product.getProductID() + " not found");
				return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
			} else if(product.isChangedImageUrl()) {
				productService.edit(product, oldProduct.getImageUrl());
			} else {
				productService.update(product);
			}
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}

		/**
		 * Delete product by id
		 * @param id
		 * @return
		 */
		@RequestMapping(value = "/{productID}", method = RequestMethod.DELETE)
		@ResponseStatus(HttpStatus.OK)
		public ResponseEntity<Void> deleteProduct(@PathVariable("productID") String productID) {
			System.out.println("Fetching & Deleting product with id " + productID);
			Product product = productService.findById(productID);
			if (product == null) {
				System.out.println("Unable to delete. Product with id " + productID + " not found");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			productService.delete(product);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		/**
		 * count all product
		 * @return
		 */
		@RequestMapping(value = "/countAll", method = RequestMethod.GET)
		public ResponseEntity<Long> countAll() {
			return new ResponseEntity<Long>(productService.countAll(), HttpStatus.OK);
		}
}
