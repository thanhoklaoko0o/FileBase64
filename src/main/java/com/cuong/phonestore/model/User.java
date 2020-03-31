package com.cuong.phonestore.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@NoArgsConstructor
/*@ToString(exclude = "cart")*/
public class User {

    @Id
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    @Size(min = 6, max = 26)
    @Column(name = "username")
    private String username;

//    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
//    @JsonIgnoreProperties("user") //Prevents infinite recursion
//    private List<Order> orderList;

    /*@OneToOne(mappedBy = "cartUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;*/

    //    @CustomEmail no email field sent from the client - refer to EmailResetDTO
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    @Size(min = 6, max = 26)
    @Column(name = "fullName")
    private String fullName;

    @Size(min = 5, max = 6)
    @Column(name = "zip")
    private String zip;

    @Column(name = "email_verified")
    private Integer emailVerified;

    @Column(name = "registration_date", insertable = false)
    @Type(type = "timestamp")
    private Date RegistrationDate;

    @Pattern(regexp = "[0-9]+")
    @Size(min = 8, max = 11)
    @Column(name = "phone")
    private String phone;

    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    @Size(min = 3, max = 40)
    @Column(name = "city")
    private String city;
    
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    @Size(min = 3, max = 40)
    @Column(name = "district")
    private String district;

    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    @Size(min = 3, max = 40)
    @Column(name = "village")
    private String village;
    
    @Pattern(regexp = "[0-9a-zA-Z #,-]+")
    @Size(min = 3, max = 240)
    @Column(name = "address")
    private String address;

    @Pattern(regexp = "[0-9a-zA-Z #,-]+")
    @Size(min = 3, max = 240)
    @Column(name = "address2")
    private String address2;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
    	joinColumns = @JoinColumn(name = "user_name"), 
    	inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {}
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Integer getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Integer emailVerified) {
		this.emailVerified = emailVerified;
	}

	public Date getRegistrationDate() {
		return RegistrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		RegistrationDate = registrationDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}