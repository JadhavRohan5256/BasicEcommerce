package com.entities;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Entity
public class UserAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userAddressId;
	private String userAddressCity;
	private long  userAddressPinCode;
	private String userAddressState;
	private String userAddressHouseNo;
	@OneToOne(mappedBy = "userAddress" )
	private User user;
	/**
	 * Empty contructor
	 */
	public UserAddress() {
		
	}
	
	/**
	 * Without userAddressId constructor
	 */
	public UserAddress(long userAddressPinCode, String userAddressState, String userAddressCity,
			String userAddressHouseNo) {
		super();
		this.userAddressPinCode = userAddressPinCode;
		this.userAddressState = userAddressState;
		this.userAddressCity = userAddressCity;
		this.userAddressHouseNo = userAddressHouseNo;
	}
	
	
	/**
	 * All Setter and Getter
	 */
	public int getUserAddressId() {
		return userAddressId;
	}
	public void setUserAddressId(int userAddressId) {
		this.userAddressId = userAddressId;
	}
	public long getUserAddressPinCode() {
		return userAddressPinCode;
	}
	public void setUserAddressPinCode(long userAddressPinCode) {
		this.userAddressPinCode = userAddressPinCode;
	}
	public String getUserAddressState() {
		return userAddressState;
	}
	public void setUserAddressState(String userAddressState) {
		this.userAddressState = userAddressState;
	}
	public String getUserAddressCity() {
		return userAddressCity;
	}
	public void setUserAddressCity(String userAddressCity) {
		this.userAddressCity = userAddressCity;
	}
	public String getUserAddressHouseNo() {
		return userAddressHouseNo;
	}
	public void setUserAddressHouseNo(String userAddressHouseNo) {
		this.userAddressHouseNo = userAddressHouseNo;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * This All properties Object;
	 */
	@Override
	public String toString() {
		return "UserAddress [userAddressId=" + userAddressId + ", userAddressPinCode=" + userAddressPinCode
				+ ", userAddressState=" + userAddressState + ", userAddressCity=" + userAddressCity + ", userAddressHouseNo=" + userAddressHouseNo + "]";
	}
	
	
	
}
