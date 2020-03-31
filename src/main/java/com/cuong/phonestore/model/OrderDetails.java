package com.cuong.phonestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orderdetail")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "order")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderdetailID")
    private Long orderdetailID;

    @ManyToOne
    @JoinColumn(name = "orderID")
    @JsonIgnoreProperties("orderDetailsList")
    @JsonIgnore
    private Order order;

    @ManyToOne //TODO IS IT ONETOONE OR MANY TO ONE
    @JoinColumn(name = "productID")
    private Product product;

    @Column(name = "amount")
    @NotNull
    private Integer amount;
    
    @Column(name = "price")
    @NotNull
    private Long price;

	public Long getOrderdetailID() {
		return orderdetailID;
	}

	public void setOrderdetailID(Long orderdetailID) {
		this.orderdetailID = orderdetailID;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

}
