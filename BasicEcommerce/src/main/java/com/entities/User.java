package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String firstName;
	private String lastName;	
	@Column(unique = true)
	private String userEmail;
	private String birthDate;
	private String userPassword;
	@Column(unique = true)
	private String userPhone;
	private String userPro;
	@OneToOne(fetch = FetchType.EAGER)
	private UserAddress userAddress;
	private int userType;
	
	
	/**
	 * Default contructor
	 */
	
	public User() {
		this.userType = 0;
	}
	
	/**
	 * Without userId contructor
	 */
	public User(String firstName, String lastName, String userEmail, String birthDate, String userPassword,
			String userPhone, String userPro, UserAddress userAddress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userEmail = userEmail;
		this.birthDate = birthDate;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userPro = userPro;
		this.userAddress = userAddress;
		this.userType =0;
	}

	/**
	 * All Getter and Setter 
	 */

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getUserPhone() {
		return userPhone;
	}


	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}


	public String getUserPro() {
		return userPro;
	}


	public void setUserPro(String userPro) {
		this.userPro = userPro;
	}


	public UserAddress getUserAddress() {
		return userAddress;
	}


	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userEmail="
				+ userEmail + ", birthDate=" + birthDate + ", userPassword=" + userPassword + ", userPhone=" + userPhone
				+ ", userPro=" + userPro + ", userAddress=" + userAddress + ", userType=" + userType + "]";
	}
	
	
}
