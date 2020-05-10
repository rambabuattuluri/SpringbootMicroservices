package com.bootcamp.microservice.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {

	@Id
	@Column(name="Id")
	public Long itemId;
	@Column(name="product_name")
	public String productName;
	@Column(name="product_type")
	public String productType;
	@Column(name="price")
	public double price;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long itemId, String productName, String productType, double price) {
		super();
		this.itemId = itemId;
		this.productName = productName;
		this.productType = productType;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [itemId=" + itemId + ", productName=" + productName + ", productType=" + productType
				+ ", price=" + price + "]";
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
