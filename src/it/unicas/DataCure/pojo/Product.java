package it.unicas.DataCure.pojo;

public class Product {

	private String productId;
	private String productName;
	private String productCategory;
	private Integer productPrice;
	private String createdDate;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String productId, String productName, String productCategory, Integer productPrice,
			String createdDate) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.createdDate = createdDate;
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

  @Override
  public String toString() {
    return "Product{" +
      "productId='" + productId + '\'' +
      ", productName='" + productName + '\'' +
      ", productCategory='" + productCategory + '\'' +
      ", productPrice=" + productPrice +
      ", createdDate='" + createdDate + '\'' +
      '}';
  }
}
