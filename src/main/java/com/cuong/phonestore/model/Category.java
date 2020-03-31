package com.cuong.phonestore.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Entity
@Table(name = "category", schema = "targetSchemaName")
@JsonAutoDetect(getterVisibility=Visibility.NONE, isGetterVisibility=Visibility.NONE, 
fieldVisibility=Visibility.NONE)
public class Category {

	@Id
	private String categoryID;
	private String categoryName;
	
	@Transient
	Set<Product> products;

	@JsonProperty(value="categoryID")
	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	@JsonProperty(value="categoryName")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@JsonProperty(value="products")
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
	
}
