package com.entities;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int productId;
	private String productTitle;
	@Column(length = 1000)
	private String productDesc;
	@OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
	private List<ProductPhotos> productPhotos = new ArrayList<ProductPhotos>();

	private int productPrice;
	private byte productDiscount;
	private int afterDiscountPrice;
	private int productQuantity;
	@ManyToOne
	private Category category;
	
	
	
	/**
	 * Without Parameter Constructor
	 */
	
	public Product() {
		
	}
	/**
	 * Without productId Constructor;
	 */
	public Product(String productTitle, String productDesc, List<ProductPhotos> productPhotos, int productPrice,
			byte productDiscount, int productQuantity, Category category) {
		super();
		this.productTitle = productTitle;
		this.productDesc = productDesc;
		this.productPhotos = productPhotos;
		this.productPrice = productPrice;
		this.productDiscount = productDiscount;
		this.productQuantity = productQuantity;
		this.category = category;
	}
	
	
	/**
	 * All Properties getter and setter; 
	 */
	
	public int getProductId() {
		return productId;
	}
	
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	
	public String getProductTitle() {
		return productTitle;
	}
	
	
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	
	
	public String getProductDesc() {
		return productDesc;
	}
	
	
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	
	public List<ProductPhotos> getProductPhotos() {
		return productPhotos;
	}
	
	
	public void setProductPhotos(List<ProductPhotos> productPhotos) {
		this.productPhotos = productPhotos;
	}
	
	
	
	
	public int getProductPrice() {
		return productPrice;
	}
	
	
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	
	
	public byte getProductDiscount() {
		return productDiscount;
	}
	
	
	public void setProductDiscount(byte productDiscount) {
		this.productDiscount = productDiscount;
	}
	
	
	public int getProductQuantity() {
		return productQuantity;
	}
	
	
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	
	public Category getCategory() {
		return category;
	}
	
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	public int getAfterDiscountPrice() {
		float percentage = (float)this.productPrice / 100;
		float afterDiscount = 100 - this.productDiscount;
		float price = percentage * afterDiscount;
		System.out.println(price + " "+percentage + " "+afterDiscount);
		this.afterDiscountPrice = (int)price;
		return afterDiscountPrice;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productTitle=" + productTitle + ", productDesc=" + productDesc
				+ ", productPhotos=" + productPhotos + ", productPrice=" + productPrice + ", productDiscount="
				+ productDiscount + ", afterDiscountPrice=" + afterDiscountPrice + ", productQuantity="
				+ productQuantity + ", category=" + category + "]";
	}
	
	
}
