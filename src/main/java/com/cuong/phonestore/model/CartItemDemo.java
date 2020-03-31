package com.cuong.phonestore.model;

public class CartItemDemo {

	private String productID;
    private String productName;
    private int quantity;
    private String origion;
    private String thumb;
    private Long price;
    private String color;
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getOrigion() {
		return origion;
	}
	public void setOrigion(String origion) {
		this.origion = origion;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public CartItemDemo(String productID, String productName, int quantity, String origion, String thumb, Long price,
			String color) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.quantity = quantity;
		this.origion = origion;
		this.thumb = thumb;
		this.price = price;
		this.color = color;
	}
	public CartItemDemo() {
		super();
	}
    
}
