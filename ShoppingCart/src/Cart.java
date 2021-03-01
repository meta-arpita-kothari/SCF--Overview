import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Cart class provides the methods for Cart Operations.
 */
public class Cart {
	private final Map<Integer, Integer> cartProducts = new HashMap<Integer, Integer>();
	private final List<Product> products = new ProductList().getProducts();

	/**
	 * This method adds an Product to the Cart.
	 * 
	 * @param productCode Unique code of the Product.
	 */
	public void addProductByProductCode(int productCode) {
		Product product = getProductByProductCode(productCode);

		if (product != null) {
			System.out.print("Enter Quantity: ");
			Integer productQuantity = Menu.getUserInput();

			if (isValidQuantity(productQuantity)) {
				int productStock = product.getProductStock();

				if (productStock >= productQuantity) {
					// Updating the Product Stock after adding the Product to the Cart
					getCartProducts().put(product.getProductCode(), productQuantity);
					product.setProductStock(productStock - productQuantity);
					System.out.println("\nProduct added successfully.");
				} else {
					System.out.println("\nInsufficient Stock!");
				}
			}
		} else {
			System.out.println("\nInvalid product code!");
		}
	}

	/**
	 * This method updates the quantity of an existing product of Cart.
	 * 
	 * @param productCode Unique code of the Product.
	 */
	public void updateProductQuantityInCart(int productCode) {
		System.out.print("Enter New Quantity: ");
		Integer newQuantity = Menu.getUserInput();

		if (isValidQuantity(newQuantity)) {
			Product product = getProductByProductCode(productCode);
			int productStock = product.getProductStock();
			int oldQuantity = getCartProducts().get(productCode);
			int deltaQuantity = newQuantity - oldQuantity;

			if (productStock >= deltaQuantity) {
				// Updating the Product Stock after updating the Product quantity in Cart
				getCartProducts().replace(productCode, newQuantity);
				product.setProductStock(productStock - deltaQuantity);
				System.out.println("\nQuantity updated successfully.");
			} else {
				System.out.println("\nInsufficient Stock!");
			}
		}
	}

	/**
	 * This method removes an Product from the Cart.
	 * 
	 * @param productCode Unique code of the Product.
	 */
	public void removeProductByProductCode(int productCode) {
		// Checking whether the product to remove exists in the Cart
		if (getCartProducts().containsKey(productCode)) {
			Product product = getProductByProductCode(productCode);
			int productStock = product.getProductStock();
			int productQuantity = getCartProducts().get(productCode);
			getCartProducts().remove(productCode);

			// Updating the Product Stock after removing the Product from Cart
			product.setProductStock(productStock + productQuantity);
			System.out.println("\nProduct removed successfully.");
		} else {
			System.out.println("\nProduct not found in cart!");
		}
	}

	/**
	 * This method returns the Product object that represents the given Product Code.
	 * 
	 * @param productCode Unique code of the Product.
	 * @return Product Product object representing the Product Code.
	 */
	private Product getProductByProductCode(int productCode) {
		Product productFound = null;

		for (Product product : products) {
			if (product.getProductCode() == productCode) {
				productFound = product;
				break;
			}
		}
		return productFound;
	}

	/**
	 * This method checks whether the user entered quantity of an Product is a positive
	 * integer.
	 * 
	 * @param quantity Quantity entered by user for an Product.
	 * @return boolean True if it is a valid quantity, False otherwise.
	 */
	private boolean isValidQuantity(Integer quantity) {
		if (quantity != null) {
			if (quantity > 0) {
				return true;
			} else {
				System.out.println("\nInvalid Quantity!");
			}
		}
		return false;
	}

	/**
	 * This method displays the list of ProductList in the Cart.
	 */
	public void printCartProducts() {
		// Checking whether Cart has some ProductList to display
		if (!getCartProducts().isEmpty()) {
			System.out.println("\nCART:");
			System.out.println("\nProduct\t\t\tQuantity");
			System.out.println("--------------------------------");

			for (Map.Entry<Integer, Integer> product : getCartProducts().entrySet()) {
				System.out.println(getProductByProductCode(product.getKey()).getProductName() + "\t\t" + product.getValue());
			}
		} else {
			System.out.println("\nCart is empty!");
		}
	}

	/**
	 * This method returns the updated list of ProductList according to the current state
	 * of Cart.
	 * 
	 * @return List<Product> Updated List of ProductList according to current state of Cart.
	 */
	public List<Product> getStoreProducts() {
		return products;
	}

	/**
	 * This method returns the Cart.
	 * 
	 * @return Map<Integer, Integer> HashMap with Product Code as key and Product Quantity
	 *         as value.
	 */
	public Map<Integer, Integer> getCartProducts() {
		return cartProducts;
	}

	/**
	 * This method displays the Billing for the ProductList in the Cart.
	 */
	public void generateBilling() {
		// Checking whether the Cart has any products for which the bill can be generated
		if (!getCartProducts().isEmpty()) {
			System.out.println("\nBilling:\n");
			System.out.println("CODE\tNAME\t\t\tRATE\tQUANTITY\tAMOUNT");
			System.out.println("--------------------------------------------------------------");

			Double totalAmount = 0.0;

			for (Map.Entry<Integer, Integer> product : getCartProducts().entrySet()) {
				int productCode = product.getKey();
				int quantity = product.getValue();
				Product billingProduct = getProductByProductCode(productCode);
				Double rate = billingProduct.getProductPrice();
				Double amount = rate * quantity;
				System.out.println(" " + productCode + "\t" + billingProduct.getProductName() + "\t\t" + rate + "\t" + quantity
						+ "\t\t" + amount);
				totalAmount += amount;
			}
			System.out.println("\nTOTAL BILL AMOUNT: " + totalAmount);
		} else {
			System.out.println("\nCart is empty!");
		}
	}
}