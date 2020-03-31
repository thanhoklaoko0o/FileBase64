package com.cuong.phonestore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "onepayresponse", schema = "targetSchemaName")
@JsonAutoDetect(getterVisibility=Visibility.NONE, isGetterVisibility=Visibility.NONE, 
fieldVisibility=Visibility.NONE)
public class OnepayResponse {

	@Id
	private String transactionID;
	private Long purchaseAmount;
	private int responseCode;
	private String orderInfo;
	private String secure_Hash;
	private String title;
	private String message;
	private String merchantID;
	private String transStatus;
	
	/*@OneToOne(mappedBy = "onepayResponse",cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	private Order order;*/
	
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public Long getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(Long purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}
	public String getSecure_Hash() {
		return secure_Hash;
	}
	public void setSecure_Hash(String secure_Hash) {
		this.secure_Hash = secure_Hash;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMerchantID() {
		return merchantID;
	}
	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	
	
	/*public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		if (order == null) {
            if (this.order != null) {
                this.order.setOnepayResponse(null);
            }
        }
        else {
            order.setOnepayResponse(this);
        }
        this.order = order;
	}*/
}
