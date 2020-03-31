package com.cuong.phonestore.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "brand", schema = "targetSchemaName")
@JsonAutoDetect(getterVisibility=Visibility.NONE, isGetterVisibility=Visibility.NONE, 
fieldVisibility=Visibility.NONE)
public class Brand {
	
	@Id
	private String brandID;
	private String brandName;
	private String logoUrl;
	
	@Transient
	Set<Product> products;
	
	@Transient
	private boolean changedLogo;
	
	@JsonProperty(value="brandID")
	public String getIdBrand() {
		return brandID;
	}
	public void setIdBrand(String BrandID) {
		this.brandID = BrandID;
	}
	
	@JsonProperty(value="brandName")
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	@JsonProperty(value="logo")
	public String getLogo() {
		return logoUrl;
	}
	public void setLogo(String logo) {
		this.logoUrl = logo;
	}
	
	
	@JsonProperty(value="products")
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	@JsonProperty(value="changedLogo")
	public boolean isChangedLogo() {
		return changedLogo;
	}
	public void setChangedLogo(boolean changedLogo) {
		this.changedLogo = changedLogo;
	}
}
