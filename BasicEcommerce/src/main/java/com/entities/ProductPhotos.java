package com.entities;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Entity
public class ProductPhotos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productPhotosId;
	private String productPhotosName;
	@ManyToOne
	private Product product;
	
	
	/**
	 * Without Parameter Constructor;
	 */
	public ProductPhotos() {
		
	}
	/**
	 * Without productPhotosId Constructor;
	 */
	public ProductPhotos(String productPhotosName, Product product) {
		super();
		this.productPhotosName = productPhotosName;
		this.product = product;
	}


	/**
	 * All Getter and Setter 
	 */
	public ProductPhotos(String productPhotosName) {
		super();
		this.productPhotosName = productPhotosName;
	}

	public int getProductPhotosId() {
		return productPhotosId;
	}
	
	public void setProductPhotosId(int productPhotosId) {
		this.productPhotosId = productPhotosId;
	}
	
	public String getProductPhotosName() {
		return productPhotosName;
	}
	
	public void setProductPhotosName(String productPhotosName) {
		this.productPhotosName = productPhotosName;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * All properties combine to and make a Object
	 */
	@Override
	public String toString() {
		return "ProductPhotos [productPhotosId=" + productPhotosId + ", productPhotosName=" + productPhotosName
				+ ", product=" + product + "]";
	}
	
	
}
