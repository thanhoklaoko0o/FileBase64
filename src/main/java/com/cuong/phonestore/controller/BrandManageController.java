package com.cuong.phonestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cuong.phonestore.model.Brand;
import com.cuong.phonestore.services.BrandService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/brand")
public class BrandManageController {

	@Autowired
	private BrandService brandService;
	
	/**
	 * get BrandList
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Brand>> getListBrands(){
        return new ResponseEntity<List<Brand>>(brandService.findAll(), HttpStatus.OK);
	}
}
