package com.entities;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	private String categoryTitle;
	@Column(length = 1000)
	private String categoryDesc;
	private int categoryTotalQuntity;
	@OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
	private List<Product> product = new ArrayList<Product>();
	
	/**
	 * Empty contructor
	 */
	public Category() {
		
	}
	
	/**
	 * Without categoryId  Constructor;
	 */
	public Category(String categoryTitle, String categoryDesc, int categoryTotalQuntity, List<Product> product) {
		super();
		this.categoryTitle = categoryTitle;
		this.categoryDesc = categoryDesc;
		this.categoryTotalQuntity = categoryTotalQuntity;
		this.product = product;
	}
	
	
	/**
	 * Without categoryId and product list Constructor;
	 */
	public Category(String categoryTitle, String categoryDesc, int categoryTotalQuntity) {
		super();
		this.categoryTitle = categoryTitle;
		this.categoryDesc = categoryDesc;
		this.categoryTotalQuntity = categoryTotalQuntity;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public int getCategoryTotalQuntity() {
		return categoryTotalQuntity;
	}
	public void setCategoryTotalQuntity(int categoryTotalQuntity) {
		this.categoryTotalQuntity = categoryTotalQuntity;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categoryDesc="
				+ categoryDesc + ", categoryTotalQuntity=" + categoryTotalQuntity + ", product=" + product + "]";
	}
	
}
