import java.util.Objects;

public class Product {
	private Integer productCode;
	private String productName;
	private Double productPrice;
	private Integer productStock;

	/**
	 * Parameterized Constructor that creates a Product object.
	 * 
	 * @param productCode  Unique integer to represent the Product.
	 * @param productName  Name of the Product.
	 * @param productPrice Price of the Product.
	 * @param productStock Stock of the Product.
	 */
	public Product(int productCode, String productName, Double productPrice, Integer productStock) {
		this.setProductCode(productCode);
		this.setProductName(productName);
		this.setProductPrice(productPrice);
		this.setProductStock(productStock);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (obj == null || !(obj instanceof Product))
			return false;

		final Product other = (Product) obj;

		return productCode == other.productCode && Objects.equals(productName, other.productName)
				&& Objects.equals(productPrice, other.productPrice) && productStock == other.productStock;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productCode, productName, productPrice, productStock);
	}

	/**
	 * This method returns the name of the Product.
	 * 
	 * @return String Name of Product.
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * This method sets the name of the Product.
	 * 
	 * @param productName Name of the Product.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * This method returns the stock of the Product.
	 * 
	 * @return Integer Stock of Product in Store.
	 */
	public Integer getProductStock() {
		return productStock;
	}

	/**
	 * This method sets the stock of the Product.
	 * 
	 * @param productStock Stock of the Product.
	 */
	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	/**
	 * This method return the price of the Product.
	 * 
	 * @return Double Price of the Product.
	 */
	public Double getProductPrice() {
		return productPrice;
	}

	/**
	 * This method sets the price of the Product.
	 * 
	 * @param productPrice Price of the Product.
	 */
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * This method return the code of the Product.
	 * 
	 * @return Integer Unique code of the Product.
	 */
	public Integer getProductCode() {
		return productCode;
	}

	/**
	 * This method sets the code of the Product.
	 * 
	 * @param productCode Unique code of the Product.
	 */
	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}
}