package com.simple.model;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class UserDto {
	@NotBlank(message="username shouldn't be null ")
    private String username;
	@NotBlank(message="password shouldn't be null ")
    private String password;
	@NotBlank(message="firstname shouldn't be null ")
    private String firstname;
	@NotBlank(message="lastname shouldn't be null ")
    private String lastname;
	@NotBlank(message="email shouldn't be null" )
    @Email(message="invalid email address")
    private String email;
    @NotNull(message="contact cannot be empty")
    @Pattern(regexp="\\d{10}$",message="not valid phone number")
    private String contact;
    private String role="user";
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

}
