
import java.util.ArrayList;
import java.util.List;

/**
 * Defines the collection of ProductList in Store.
 */
public class ProductList {
	private final List<Product> products = new ArrayList<Product>();

	/**
	 * Constructor of class ProductList that initializes the list of ProductList available in
	 * Store.
	 */
	public ProductList() {
		String[] productNames = { "Maggie Pasta", "Lakme Facewash", "Britannia Cookies", "Nestle coffee", "Parachute Coconut Oil" };
		Double[] productPrices = { 25.75, 105.00, 50.0, 100.0, 10.0 };
		Integer[] productStocks = { 10, 8, 20, 5, 15 };

		for (int i = 0; i < productNames.length; i++) {
			this.products.add(new Product(i + 1, productNames[i], productPrices[i], productStocks[i]));
		}
	}

	/**
	 * This method returns the list of all available ProductList in Store.
	 * 
	 * @return List<Product> List of all ProductList.
	 */
	public List<Product> getProducts() {
		return products;
	}
}