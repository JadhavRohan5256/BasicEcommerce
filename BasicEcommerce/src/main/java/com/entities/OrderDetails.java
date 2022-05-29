package com.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private int orderQuntity;
	private int productId;
	private int userId;
	
//	@OneToOne(fetch = FetchType.EAGER)
//	private Product products; 
	
	@Column(length = 200)
	private String name;
	private String email;
	
	@Column(length = 1000)
	private String address;
	
	private String status;
	

	/**
	 * Getter and setter of Instance Variable of Class 
	 */
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderQuntity() {
		return orderQuntity;
	}

	public void setOrderQuntity(int orderQuntity) {
		this.orderQuntity = orderQuntity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * toString methods to print all instance variable values when 
	 * we print object of this class */
	
	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", orderQuntity=" + orderQuntity + ", productId=" + productId
				+ ", userId=" + userId + ", name=" + name + ", email=" + email + ", address=" + address + ", status="
				+ status + "]";
	}
	

}

