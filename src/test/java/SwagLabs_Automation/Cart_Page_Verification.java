package SwagLabs_Automation;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Cart_Page_Verification {
	private WebDriver driver;
    private LoginMethod loginMethod; // Instance of LoginMethod for reusable login logic

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver and maximize the browser window
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Browser opened successfully.");

        // Initialize the LoginMethod class
        loginMethod = new LoginMethod(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }
    
    public void addItemToCart(String itemId) {
        WebElement addToCartButton = driver.findElement(By.id(itemId));
        addToCartButton.click();
        System.out.println("Added item with ID: " + itemId + " to the cart.");
    }
    
    public void RemoveItemToCart(String itemId) {
        WebElement RemoveItemCartButton = driver.findElement(By.id(itemId));
        RemoveItemCartButton.click();
        System.out.println("Removed item with ID: " + itemId + " to the cart.");
    }
    /**
     * Method to proceed to checkout
     */
    public void proceedToCheckout() {
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();
        System.out.println("Proceeded to checkout.");
    }


	@Test
	public void Testcase28() throws IOException {
		// Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }
        // Perform login using LoginMethod
        loginMethod.login(username, password);
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='cart_contents_container']")).isDisplayed());
		System.out.println("Test case 28: User can access the cart page.");
	}

	@Test
	public void Testcase29() throws IOException {
		// Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String Product1 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product1");
        String Product3 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product3");
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }
        // Perform login using LoginMethod
        loginMethod.login(username, password);
		// Add items to the cart
        loginMethod.addItemToCart(Product1);
        loginMethod.addItemToCart(Product3);
		// Navigate to the cart
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		// Verify if there are items in the cart
		int actualItemCount = driver.findElements(By.xpath("//div[@class='cart_item']")).size();
		Assert.assertTrue(actualItemCount > 0, "No items found in the cart.");
		// Verify item descriptions and quantities
		String[] expectedDescriptions = { "Sauce Labs Backpack", "Sauce Labs Bike Light" };
		for (int i = 0; i < actualItemCount; i++) {
			WebElement itemDescriptionElement = driver.findElement(
					By.xpath("//div[@class='cart_item'][" + (i + 1) + "]//div[@class='inventory_item_name']"));
			String actualDescription = itemDescriptionElement.getText();
			Assert.assertEquals(actualDescription, expectedDescriptions[i], "Item description does not match.");
			WebElement itemQuantityElement = driver
					.findElement(By.xpath("//div[@class='cart_item'][" + (i + 1) + "]//div[@class='cart_quantity']"));
			String actualQuantity = itemQuantityElement.getText();
			Assert.assertEquals(actualQuantity, "1", "Item quantity is incorrect.");
		}
		// Verify "Continue Shopping" button is displayed
		WebElement continueShoppingButton = driver.findElement(By.xpath("//button[@id='continue-shopping']"));
		Assert.assertTrue(continueShoppingButton.isDisplayed(), "'Continue Shopping' button is not displayed.");
		// Verify "Checkout" button is displayed
		WebElement checkoutButton = driver.findElement(By.xpath("//button[@id='checkout']"));
		Assert.assertTrue(checkoutButton.isDisplayed(), "'Checkout' button is not displayed.");
		System.out.println("Test case 29: All required details are displayed on the cart page.");
	}

	@Test
	public void Testcase30() throws IOException, InterruptedException {
		// Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }
        // Perform login using LoginMethod
        loginMethod.login(username, password);
		// Navigate to the shopping cart
		Thread.sleep(2000); // Wait for the dashboard to load
		driver.findElement(By.className("shopping_cart_link")).click();
		// Verify 'Continue Shopping' button is displayed and click it
		Thread.sleep(2000); // Wait for the cart page to load
		WebElement continueShoppingButton = driver.findElement(By.id("continue-shopping"));
		Assert.assertTrue(continueShoppingButton.isDisplayed(), "'Continue Shopping' button is not displayed.");
		continueShoppingButton.click();
		// Verify navigation back to the product listing page
		Thread.sleep(2000); // Wait for the product listing page to load
		WebElement inventoryContainer = driver.findElement(By.id("inventory_container"));
		Assert.assertTrue(inventoryContainer.isDisplayed(), "Failed to navigate back to the product listing page.");
		System.out.println("Test case 30 proven: Navigation back to product listing successful!");
	}

	@Test
	public void Testcase31() throws IOException {
		// Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String Product1 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product1");
        String Product3 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product3");
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }
        // Perform login using LoginMethod
        loginMethod.login(username, password);
		// Add products to the cart
        loginMethod.addItemToCart(Product1);
        loginMethod.addItemToCart(Product3);
		// Navigate to the cart
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		// Retrieve the product names in the cart
		List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cart_item_label']/a/div"));
		// Define the expected product names
		List<String> expectedProducts = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light");
		// Verify if the added products are displayed in the cart
		boolean allProductsFound = true;
		for (WebElement item : cartItems) {
			String productName = item.getText();
			if (!expectedProducts.contains(productName)) {
				allProductsFound = false;
				System.out.println("Product not found in cart: " + productName);
			} else {
				System.out.println("Product found in cart: " + productName);
			}
		}
		// Assert that all expected products are found in the cart
		Assert.assertTrue(allProductsFound, "Not all expected products are displayed in the cart.");
		System.out.println("Test case 31: All added products are displayed in the cart.");
	}

	@Test
	public void Testcase33() throws IOException {
		// Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String Product1 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product1");
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }
        // Perform login using LoginMethod
        loginMethod.login(username, password);
		// Add items to the cart
        loginMethod.addItemToCart(Product1);
		// Navigate to the cart
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		// Verify if there are items in the cart
		int actualItemCount = driver.findElements(By.xpath("//div[@class='cart_item']")).size();
		Assert.assertTrue(actualItemCount > 0, "No items found in the cart.");
		// Check for required details in each item
		for (int i = 0; i < actualItemCount; i++) {
			// Quantity
			WebElement quantityElement = driver
					.findElement(By.xpath("//div[@class='cart_item'][" + (i + 1) + "]//div[@class='cart_quantity']"));
			Assert.assertNotNull(quantityElement, "Quantity is missing for item " + (i + 1));
			// Product Title
			WebElement titleElement = driver.findElement(
					By.xpath("//div[@class='cart_item'][" + (i + 1) + "]//div[@class='inventory_item_name']"));
			Assert.assertNotNull(titleElement, "Product title is missing for item " + (i + 1));
			// Description
			WebElement descriptionElement = driver.findElement(
					By.xpath("//div[@class='cart_item'][" + (i + 1) + "]//div[@class='inventory_item_desc']"));
			Assert.assertNotNull(descriptionElement, "Description is missing for item " + (i + 1));
			// Price
			WebElement priceElement = driver.findElement(
					By.xpath("//div[@class='cart_item'][" + (i + 1) + "]//div[@class='inventory_item_price']"));
			Assert.assertNotNull(priceElement, "Price is missing for item " + (i + 1));
			// Remove Button
			WebElement removeButton = driver
					.findElement(By.xpath("//div[@class='cart_item'][" + (i + 1) + "]//button[text()='Remove']"));
			Assert.assertNotNull(removeButton, "'Remove' button is missing for item " + (i + 1));
		}
		// Verify "Continue Shopping" button is displayed
		WebElement continueShoppingButton = driver.findElement(By.xpath("//button[@id='continue-shopping']"));
		Assert.assertNotNull(continueShoppingButton, "'Continue Shopping' button is missing.");
		// Verify "Checkout" button is displayed
		WebElement checkoutButton = driver.findElement(By.xpath("//button[@id='checkout']"));
		Assert.assertNotNull(checkoutButton, "'Checkout' button is missing.");
		System.out.println("Test case 33: All required product details are present on the cart page.");
	}

	@Test
	public void Testcase34() throws IOException {
		// Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String Product1 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product1");
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }
        // Perform login using LoginMethod
        loginMethod.login(username, password);
		// Add one item to the cart
		addItemToCart(Product1);
		// Verify initial cart count
		WebElement cartBadge = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
		String initialCartCountText = cartBadge.getText();
		int initialCartCount = Integer.parseInt(initialCartCountText);
		System.out.println("Initial Cart Count: " + initialCartCount);
		// Navigate to the cart
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		// Remove the item from the cart
		driver.findElement(By.xpath("//button[text()='Remove']")).click();
		// Verify if the cart badge disappears (indicating the cart is empty)
		try {
			cartBadge = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
			System.out.println("Test Failed: Cart badge is still visible after removing the item.");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Test case 34: User can remove an item and the cart count updates.");
		}
	}
	
	@Test
	public void Testcase35() throws IOException {
		// Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String Product1 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product1");
        String Product2 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product2");
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }
        // Perform login using LoginMethod
        loginMethod.login(username, password);
		// Add one item to the cart
        loginMethod.addItemToCart(Product1);
        loginMethod.addItemToCart(Product2);
		// Verify initial cart count
		WebElement cartBadge = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
		String initialCartCountText = cartBadge.getText();
		int initialCartCount = Integer.parseInt(initialCartCountText);
		System.out.println("Initial Cart Count: " + initialCartCount);
		// Navigate to the cart
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		// Remove the item from the cart
		loginMethod.RemoveItemToCart("remove-sauce-labs-backpack");
		loginMethod.RemoveItemToCart("remove-sauce-labs-bolt-t-shirt");
		// Verify if the cart badge disappears (indicating the cart is empty)
		try {
			cartBadge = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
			System.out.println("Test Failed: Cart badge is still visible after removing the item.");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Test case 35: User is able to remove all items from the cart and if the cart count updates.");
		}
	}
	
	@Test
	public void Testcase36() throws IOException, InterruptedException {
		// Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String Product1 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product1");
        String Product3 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product3");
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }
        // Perform login using LoginMethod
        loginMethod.login(username, password);

	    // Step 2: Add items to the cart
        addItemToCart(Product1);
        addItemToCart(Product3);
	    // Step 3: Navigate to the cart
	    driver.findElement(By.className("shopping_cart_link")).click();

	    // Step 4: Verify items are in the cart before logout
	    boolean isBackpackPresent = driver.findElement(By.xpath("//a[@id='item_4_title_link']")).isDisplayed();
	    boolean isBikeLightPresent = driver.findElement(By.xpath("//a[@id='item_0_title_link']")).isDisplayed();
	    Assert.assertTrue(isBackpackPresent, "Backpack is not in the cart!");
	    Assert.assertTrue(isBikeLightPresent, "Bike Light is not in the cart!");

	    // Step 5: Logout
	    driver.findElement(By.id("react-burger-menu-btn")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();

	    // Step 6: Log back in using the reusable LoginMethod
	    loginMethod.login(username, password);

	    // Step 7: Navigate to the cart again
	    driver.findElement(By.className("shopping_cart_link")).click();

	    // Step 8: Verify if items persist in the cart after re-login
	    boolean isBackpackStillPresent = driver.findElements(By.xpath("//div[text()='Sauce Labs Backpack']")).size() > 0;
	    boolean isBikeLightStillPresent = driver.findElements(By.xpath("//div[text()='Sauce Labs Bike Light']")).size() > 0;

	    Assert.assertTrue(isBackpackStillPresent, "Backpack did not persist in the cart after re-login!");
	    Assert.assertTrue(isBikeLightStillPresent, "Bike Light did not persist in the cart after re-login!");

	    System.out.println("Test case 36: Cart items persist after logout and re-login.");
	}
}
