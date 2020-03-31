package com.cuong.phonestore.services.impl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cuong.phonestore.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

    private String username;
    
    private String fullname;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(String username, String fullname, String email, String password, 
			    		Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

   public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrinciple(
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

   public String getFullname() {
		return fullname;
	    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(username, user.username);
    }
}
