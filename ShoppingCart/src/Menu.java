import java.util.List;
import java.util.Scanner;

/**
 * Creates a command-line User Interface.
 */
public class Menu {
	private static Scanner input;
	private Cart cart = new Cart();

	/**
	 * Constructor for class Menu
	 */
	public Menu() {
		Integer choice;

		// Showing Main Menu until user chooses to exit
		do {
			getMainMenu();

			System.out.print("\nEnter your choice: ");
			choice = getUserInput();

			if (choice != null) {
				switch (choice) {
				case 1:
					cartOperations();
					break;
				case 2:
					showCart();
					break;
				case 3:
					cart.generateBilling();
					break;
				case 4:
					System.exit(0);
					break;
				default:
					break;
				}
			} else {
				choice = 0; // converting null value to valid integer
			}
		} while (choice != 4);
	}

	/**
	 * This method shows the Inner Menu with options for Cart Operations.
	 */
	private void cartOperations() {
		Integer choice;

		// Showing Inner Menu until user chooses to checkout
		do {
			displayStoreProducts();
			getInnerMenu();

			System.out.print("\nEnter your choice: ");
			choice = getUserInput();

			if (choice != null) {
				switch (choice) {
				case 1:
					addProductToCart();
					showCart();
					break;
				case 2:
					updateProductQuantity();
					showCart();
					break;
				case 3:
					removeProductFromCart();
					showCart();
					break;
				default:
					break;
				}
			} else {
				choice = 0; // converting null value to valid integer
			}
		} while (choice != 4);
	}

	/**
	 * This method displays Main Menu on standard output.
	 */
	private void getMainMenu() {
		System.out.println("\nMENU\n");
		System.out.println("1. Display Store ProductList");
		System.out.println("2. Display Cart");
		System.out.println("3. Generate Bill");
		System.out.println("4. Exit");
	}

	/**
	 * This method displays list of Store ProductList on standard output.
	 */
	private void displayStoreProducts() {
		System.out.println("\nStore Products :\n");
		System.out.println("CODE\tNAME\t\t\tPRICE\tSTOCK");
		System.out.println("---------------------------------------------");

		List<Product> items = cart.getStoreProducts();

		for (Product item : items) {
			System.out.println(item.getProductCode() + "\t" + item.getProductName() + "\t\t" + item.getProductPrice() + "\t"
					+ item.getProductStock());
		}
	}

	/**
	 * This method displays Inner Menu on standard output.
	 */
	private void getInnerMenu() {
		System.out.println("\nOptions:\n");
		System.out.println("1. Add item to Cart");
		System.out.println("2. Update item Quantity");
		System.out.println("3. Remove item from Cart");
		System.out.println("4. Checkout");
	}

	/**
	 * This method adds a new item to the Cart.
	 */
	private void addProductToCart() {
		System.out.print("Enter Product Code: ");
		Integer itemCode = getUserInput();

		if (itemCode != null) {
			// Checking whether Cart already has this item
			if (!cart.getCartProducts().containsKey(itemCode)) {
				cart.addProductByProductCode(itemCode);
			} else {
				System.out.println("\nProduct already exists in cart!");
			}
		}
	}

	/**
	 * This methods updates the quantity of an existing item in the Cart.
	 */
	private void updateProductQuantity() {
		// Checking whether Cart has some items to update
		if (!cart.getCartProducts().isEmpty()) {
			System.out.print("Enter Product Code: ");
			Integer itemCode = getUserInput();

			if (itemCode != null) {
				// Checking whether Cart has this item or not
				if (cart.getCartProducts().containsKey(itemCode)) {
					cart.updateProductQuantityInCart(itemCode);
				} else {
					System.out.println("\nProduct not found in cart!");
				}
			}
		}
	}

	/**
	 * This method removes an item from the Cart.
	 */
	private void removeProductFromCart() {
		// Checking whether Cart has some items to remove
		if (!cart.getCartProducts().isEmpty()) {
			System.out.print("Enter Product Code: ");
			Integer itemCode = getUserInput();

			if (itemCode != null) {
				cart.removeProductByProductCode(itemCode);
			}
		}
	}

	/**
	 * This method displays the contents of Cart.
	 */
	private void showCart() {
		cart.printCartProducts();
	}

	/**
	 * This is method takes input from user.
	 * 
	 * @return Integer This is the number entered by user.
	 */
	public static Integer getUserInput() {
		Integer ch = null;

		input = new Scanner(System.in);

		try {
			ch = Integer.parseInt(input.nextLine());
		} catch (Exception e) {
			System.out.println("\nInvalid input!");
		}

		return ch;
	}
	
	/**
	 * This is the main method which makes use of Menu class.
	 * 
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		new Menu();
	}
}