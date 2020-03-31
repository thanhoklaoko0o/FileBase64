package com.cuong.phonestore.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "discount")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"orderList"})
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discounID")
    private Long discounID;

    @OneToMany(mappedBy = "orderDiscount")
    @JsonIgnore
    private List<Order> orderList;

    @Column(name = "code")
    @NotBlank
    private String code;

    @Column(name = "discount_percent")
    @NotNull
    private Integer discountPercent;


    @Column(name = "expdate")
    @Type(type = "timestamp")
    private LocalDateTime expdate;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "quantity_used")
    private Integer quantityUsed;

}
