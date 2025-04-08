package SwagLabs_Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Checkout_Overview {
    private WebDriver driver;
    private LoginMethod loginMethod;

    @BeforeClass
    public void setUp() {
        // Initialize WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginMethod = new LoginMethod(driver);
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after all tests
        if (driver != null) {
            driver.quit();
        }
    }

    
 

    @Test
    public void Testcase48() throws IOException {
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
        loginMethod.navigateToShoppingCart();

        // Click the checkout button
        loginMethod.proceedToCheckout();

        // Click the cancel button
        WebElement cancelButton = driver.findElement(By.id("cancel"));
        cancelButton.click();

        // Assert that the product listing is displayed
        WebElement productList = driver.findElement(By.xpath("//div[@class='cart_list']"));
        Assert.assertTrue(productList.isDisplayed(), "Product listing is not displayed.");

        System.out.println("Test case 48: Users can access the checkout page");
    }

    @Test
    public void Testcase49() throws InterruptedException, IOException {
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

        // Fetch checkout form details from Excel
        String firstName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "FirstName");
        String lastName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "LastName");
        String postalCode = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "PostalCode");

        if (firstName == null || lastName == null || postalCode == null) {
            throw new IllegalArgumentException("Checkout form details (First Name, Last Name, Postal Code) are missing in the Excel file.");
        }

        // Add an item to the cart with WebDriverWait
        loginMethod.addItemToCart(Product1);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Fill out the checkout form using the instance method
        loginMethod.fillCheckoutForm(firstName, lastName, postalCode);

        // Wait for the page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='summary_info']")));

        // Verify elements on the Checkout Overview page
        verifyCheckoutOverviewPage();

        System.out.println("Test case 49: All elements on the Checkout Overview page were validated successfully.");
        
        loginMethod.navigateToShoppingCart();
        
        loginMethod.RemoveItemToCart("remove-sauce-labs-backpack");
    }

    @Test
	    public void testcase50() throws IOException {
	    	// Define file path and sheet name
	        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
	        String sheetName = "Sheet1";
	
	        // Fetch the password for the username "standard_user"
	        String username = "standard_user";
	        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
	        String Product3 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product3");
	        if (password == null || password.isEmpty()) {
	            throw new IllegalArgumentException("Password not found for username: " + username);
	        }
	        // Perform login using LoginMethod
	        loginMethod.login(username, password);
	
	        // Add an item to the cart
	        loginMethod.addItemToCart(Product3);
	
	        // Navigate to the shopping cart
	        loginMethod.navigateToShoppingCart();
	
	        // Proceed to checkout and click the cancel button
	        loginMethod.proceedToCheckout();
	        WebElement cancelButton = driver.findElement(By.id("cancel"));
	        cancelButton.click();
	
	        // Validate redirection to the product listing page
	        WebElement inventoryContainer = driver.findElement(By.xpath("//div[@id='contents_wrapper']"));
	        Assert.assertTrue(inventoryContainer.isDisplayed(), "Inventory container is not displayed after clicking cancel.");
	
	        System.out.println("Test case 50: The page redirects to product listing after clicking cancel button.");
	        
	        loginMethod.navigateToShoppingCart();
	        
	        loginMethod.RemoveItemToCart("remove-sauce-labs-bike-light");
	    }

    @Test
    public void Testcase51() throws InterruptedException, IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch credentials and checkout form details from Excel
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String firstName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "FirstName");
        String lastName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "LastName");
        String postalCode = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "PostalCode");

        if (password == null || firstName == null || lastName == null || postalCode == null) {
            throw new IllegalArgumentException("Required data (Password, FirstName, LastName, PostalCode) is missing in the Excel file.");
        }

        // Fetch product ID from Excel
        String Product1 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product1");

        if (Product1 == null) {
            throw new IllegalArgumentException("Product ID (Product1) is missing in the Excel file.");
        }

        // Perform login
        loginMethod.login(username, password);
        // Add one item to the cart
        loginMethod.addItemToCart(Product1);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Fill out the checkout form
        loginMethod.fillCheckoutForm(firstName, lastName, postalCode);

        // Wait for the Checkout Overview page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='summary_info']")));
        
        //Verify the Product Details are displayed in the cart page
        verifyProductDetails();

        System.out.println("Test case 51: Price calculations on the Checkout Overview page were validated successfully.");
        
        loginMethod.navigateToShoppingCart();
        
        loginMethod.RemoveItemToCart("remove-sauce-labs-backpack");
    }

    @Test
    public void Testcase52() throws InterruptedException, IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch credentials and checkout form details from Excel
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String firstName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "FirstName");
        String lastName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "LastName");
        String postalCode = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "PostalCode");

        if (password == null || firstName == null || lastName == null || postalCode == null) {
            throw new IllegalArgumentException("Required data (Password, FirstName, LastName, PostalCode) is missing in the Excel file.");
        }

        // Fetch product IDs from Excel
        String Product1 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product1");
        String Product2 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product2");

        if (Product1 == null || Product2 == null) {
            throw new IllegalArgumentException("Product IDs (Product1, Product2) are missing in the Excel file.");
        }

        // Perform login
        loginMethod.login(username, password);

        // Add two different items to the cart
        loginMethod.addItemToCart(Product1);
        loginMethod.addItemToCart(Product2);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Fill out the checkout form
        loginMethod.fillCheckoutForm(firstName, lastName, postalCode);

        // Wait for the Checkout Overview page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='summary_info']")));

        // Verify price calculations
        verifyPriceCalculations();

        System.out.println("Test case 52: Price calculations on the Checkout Overview page were validated successfully.");
        
        loginMethod.navigateToShoppingCart();
        
        loginMethod.RemoveItemToCart("remove-sauce-labs-backpack");
        loginMethod.RemoveItemToCart("remove-sauce-labs-bolt-t-shirt");
    }

    /**
     * Helper method to verify elements on the Checkout Overview page
     */
    private void verifyCheckoutOverviewPage() {
        // Verify product list
        WebElement productList = driver.findElement(By.xpath("//div[@class='cart_item']"));
        Assert.assertTrue(productList.isDisplayed(), "Product list is not displayed.");

        // Verify payment information
        WebElement paymentInfo = driver.findElement(By.xpath("//div[@class='summary_info']/div[2]"));
        Assert.assertTrue(paymentInfo.isDisplayed(), "Payment information is not displayed.");
        Assert.assertEquals(paymentInfo.getText().trim(), "SauceCard #31337", "Incorrect payment information.");

        // Verify shipping information
        WebElement shippingInfo = driver.findElement(By.xpath("//div[@class='summary_info']/div[4]"));
        Assert.assertTrue(shippingInfo.isDisplayed(), "Shipping information is not displayed.");
        Assert.assertEquals(shippingInfo.getText().trim(), "Free Pony Express Delivery!", "Incorrect shipping information.");

        // Verify price total
        WebElement itemTotal = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
        WebElement tax = driver.findElement(By.xpath("//div[@class='summary_tax_label']"));
        WebElement total = driver.findElement(By.xpath("//div[@class='summary_total_label']"));
        Assert.assertTrue(itemTotal.isDisplayed(), "Item total is not displayed.");
        Assert.assertTrue(tax.isDisplayed(), "Tax is not displayed.");
        Assert.assertTrue(total.isDisplayed(), "Total is not displayed.");

        // Verify 'Cancel' and 'Finish' buttons
        WebElement cancelButton = driver.findElement(By.id("cancel"));
        Assert.assertTrue(cancelButton.isDisplayed(), "'Cancel' button is not displayed.");
        WebElement finishButton = driver.findElement(By.id("finish"));
        Assert.assertTrue(finishButton.isDisplayed(), "'Finish' button is not displayed.");
    }

    /**
     * Helper method to verify product details in the cart
     */
    private void verifyProductDetails() {
        WebElement productTitle = driver.findElement(By.className("inventory_item_name"));
        WebElement productPrice = driver.findElement(By.className("inventory_item_price"));
        WebElement productDescription = driver.findElement(By.className("inventory_item_desc"));
        WebElement productQuantity = driver.findElement(By.className("cart_quantity"));

        System.out.println("Product Title: " + productTitle.getText());
        System.out.println("Product Price: " + productPrice.getText());
        System.out.println("Product Description: " + productDescription.getText());
        System.out.println("Product Quantity: " + productQuantity.getText());
    }

    /**
     * Helper method to verify price calculations on the Checkout Overview page
     */
    /**
     * Helper method to verify price calculations on the Checkout Overview page
     */
    private void verifyPriceCalculations() {
        double totalItemPrice = 0.0;

        // Calculate total item price
        List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cart_item']"));
        for (WebElement item : cartItems) {
            WebElement itemPriceElement = item.findElement(By.xpath(".//div[@class='inventory_item_price']"));
            String priceText = itemPriceElement.getText().replace("$", "");
            totalItemPrice += Double.parseDouble(priceText);
        }

        // Retrieve displayed subtotal
        WebElement cartSubtotalElement = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
        String cartSubtotalText = cartSubtotalElement.getText().replace("Item total: $", "");
        double displayedSubtotal = Double.parseDouble(cartSubtotalText);

        // Validate subtotal
        Assert.assertEquals(totalItemPrice, displayedSubtotal, 0.01,
                "The calculated total does not match the displayed subtotal.");

        // Retrieve tax amount
        WebElement taxElement = driver.findElement(By.xpath("//div[@class='summary_tax_label']"));
        String taxText = taxElement.getText().replace("Tax: $", "");
        double taxAmount = Double.parseDouble(taxText);

        // Retrieve final total
        WebElement totalElement = driver.findElement(By.xpath("//div[@class='summary_total_label']"));
        String totalText = totalElement.getText().replace("Total: $", "");
        double finalTotal = Double.parseDouble(totalText);

        // Validate final total
        double expectedFinalTotal = displayedSubtotal + taxAmount;
        Assert.assertEquals(finalTotal, expectedFinalTotal, 0.01,
                "The calculated final total does not match the displayed total.");
    }
}