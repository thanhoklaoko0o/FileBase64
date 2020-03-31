package com.cuong.phonestore.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	private String productID;
	private String productName;
	private Integer quantity;
	private String origin;
	private Date openingForSale;
	private String imageUrl;
	private Long price;
	
	@Column(name = "date_created")
    @JsonIgnore
    private Date dateCreated;
	
	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@ManyToOne(targetEntity = Brand.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "brandID")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	private Brand brand;
	
	@ManyToOne(targetEntity = Category.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryID")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	private Category category;
	
	@Transient
	private boolean changedImageUrl;


	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getOpeningForSale() {
		return openingForSale;
	}

	public void setOpeningForSale(Date openingForSale) {
		this.openingForSale = openingForSale;
	}
	
	
	public boolean isChangedImageUrl() {
		return changedImageUrl;
	}

	public void setChangedImageUrl(boolean changedAvatar) {
		this.changedImageUrl = changedAvatar;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}

