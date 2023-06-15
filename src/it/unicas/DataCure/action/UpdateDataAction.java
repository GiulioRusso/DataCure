package it.unicas.DataCure.action;

import com.opensymphony.xwork2.ActionSupport;

import it.unicas.DataCure.dao.ProductManagementDAO;
import it.unicas.DataCure.pojo.Product;

public class UpdateDataAction extends ActionSupport{

	private String productId;
	private String productName;
	private String productCategory;
	private Integer productPrice;
	private String createdDate;
	
	public String execute() {
		Product product = ProductManagementDAO.getProductById(productId);
		productId = product.getProductId();
		productName = product.getProductName();
		productCategory = product.getProductCategory();
		productPrice = product.getProductPrice();
		createdDate = product.getCreatedDate();
		return "success";
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
}
