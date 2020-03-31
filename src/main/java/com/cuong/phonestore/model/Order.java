package com.cuong.phonestore.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orders")//cannot name 'order' because it is a reserved word
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "user")
public class Order {

    @Id
    @Column(name = "orderID")
    private String orderID;

    @ManyToOne
    @JoinColumn(name = "username")
    //    @JsonIgnoreProperties("orderList") //Prevents infinite recursion
    @JsonIgnore
    private User user;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="onepayRequestID")
    private OnepayRequest onepayRequest;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="transactionID")
    private OnepayResponse onepayResponse;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetailsList;

    @OneToOne
    @JoinColumn(name = "discounID")
    private Discount orderDiscount;

    @Column(name = "recipient_name")
    /*@NotBlank*/
    private String recipientName;
    
    @Column(name = "ship_name")
    /*@NotBlank*/
    private String shipName;

    @Column(name = "ship_address")
   /* @NotBlank*/
    private String shipAddress;
    
    @Column(name = "zip")
    /*@NotBlank*/
    private String zip;

   /* @Pattern(regexp = "^[a-zA-Z\\s]+$")*/
    @Size(min = 3, max = 40)
    @Column(name = "city")
    private String city;
    
    /*@Pattern(regexp = "^[a-zA-Z\\s]+$")*/
    @Size(min = 3, max = 40)
    @Column(name = "district")
    private String district;

   /* @Pattern(regexp = "^[a-zA-Z\\s]+$")*/
    @Size(min = 3, max = 40)
    @Column(name = "village")
    private String village;

    @Column(name = "phone")
    /*@NotBlank*/
    private String phone;

    @Column(name = "email")
    /*@NotBlank*/
    private String email;

    @Column(name = "orderDate")
    @Type(type = "timestamp")
    private Date orderDate;

    @Column(name = "shipped")
    private Integer shipped;

    @Column(name = "confirmed")
    private Integer confirmed;
    
    @Column(name = "paid")
    private Integer paid;
    
    @Column(name = "paymentMethod")
    private String payment_method;

    @Column(name = "purchaseAmount")
    private Long purchaseAmount;
    
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderDetails> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}

	public Discount getOrderDiscount() {
		return orderDiscount;
	}

	public void setOrderDiscount(Discount orderDiscount) {
		this.orderDiscount = orderDiscount;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getShipped() {
		return shipped;
	}

	public void setShipped(Integer shipped) {
		this.shipped = shipped;
	}

	public Integer getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Integer confirmed) {
		this.confirmed = confirmed;
	}

	public Integer getPaid() {
		return paid;
	}

	public void setPaid(Integer paid) {
		this.paid = paid;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public OnepayRequest getOnepayRequest() {
		return onepayRequest;
	}

	public void setOnepayRequest(OnepayRequest onepayRequest) {
		this.onepayRequest = onepayRequest;
	}

	public OnepayResponse getOnepayResponse() {
		return onepayResponse;
	}

	public void setOnepayResponse(OnepayResponse onepayResponse) {
		this.onepayResponse = onepayResponse;
	}

	public Long getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(Long purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	
}
