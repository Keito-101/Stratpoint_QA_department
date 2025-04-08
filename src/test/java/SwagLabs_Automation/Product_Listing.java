package SwagLabs_Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Product_Listing {
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

    @Test
    public void testCase11_ProductListingPageAccess() throws IOException {
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

        // Verify access to the product listing page
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='inventory_list']")).isDisplayed());
        System.out.println("Test Case 11: Users are able to access the product listing page.");
    }

    @Test
    public void testCase12_AllProductsDisplayed() throws IOException {
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
        // Verify all products are displayed
        List<WebElement> inventoryNames = driver.findElements(By.cssSelector(".inventory_item_name"));
        for (WebElement inventoryName : inventoryNames) {
            String name = inventoryName.getText().trim();
            System.out.println("Inventory Name: " + name);
            Assert.assertTrue(inventoryName.isDisplayed(), "Element for '" + name + "' is not displayed.");
        }
        System.out.println("Test Case 12: All products are displayed.");
    }

    @Test
    public void testCase13_VerifyProductDetails() throws IOException {
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

        // Wait for the page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item")));

        // Verify product details
        List<WebElement> products = driver.findElements(By.className("inventory_item"));

        // Iterate through each product and verify the required details
        for (WebElement product : products) {
            // Verify Product Title
            WebElement productTitle = product.findElement(By.className("inventory_item_name"));
            System.out.println("Product Title: " + productTitle.getText());
            assert productTitle.isDisplayed() : "Product Title is not displayed";

            // Verify if the product image is displayed on the detail page
            WebElement productImage = driver.findElement(By.cssSelector("img"));
            Assert.assertTrue(productImage.isDisplayed(), "Product Image is not displayed on the detail page.");
            System.out.println("Product Image Source: " + productImage.getAttribute("src"));

            // Verify Product Description
            WebElement productDescription = product.findElement(By.className("inventory_item_desc"));
            System.out.println("Product Description: " + productDescription.getText());
            assert productDescription.isDisplayed() : "Product Description is not displayed";

            // Verify Product Price
            WebElement productPrice = product.findElement(By.className("inventory_item_price"));
            System.out.println("Product Price: " + productPrice.getText());
            assert productPrice.isDisplayed() : "Product Price is not displayed";

            // Verify 'Add to Cart' Button
            WebElement addToCartButton = product.findElement(By.tagName("button"));
            System.out.println("Add to Cart Button Text: " + addToCartButton.getText());
            assert addToCartButton.isDisplayed() : "'Add to Cart' Button is not displayed";
        }

        // Print success message if all assertions pass
        System.out.println("Test_case 13 proven!");

        // Close the browser after the test
        driver.quit();
    }

    @Test
    public void testCase14_AddToCartButtonFunctionality() throws IOException {
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

        // Wait for the button to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Add to cart functionality
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']")));
        Assert.assertEquals(addToCartButton.getText(), "Add to cart", "Initial button text should be 'Add to cart'");

        addToCartButton.click();

        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@id='remove-sauce-labs-bolt-t-shirt']")));
        Assert.assertEquals(removeButton.getText(), "Remove", "Button text should change to 'Remove' after click");

        removeButton.click();

        addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")));
        Assert.assertEquals(addToCartButton.getText(), "Add to cart",
                "Button text should revert to 'Add to cart' after removal");

        System.out.println("Test Case 14: Add to cart button is clickable and text changes appropriately.");
    }

    @Test
    public void testCase15_SortByNameZtoA() throws IOException {
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

        // Select the sort option
        WebElement sortDropdown = driver.findElement(By.cssSelector(".product_sort_container"));
        sortDropdown.sendKeys("Name (Z to A)");

        // Extract product names
        List<WebElement> productElements = driver.findElements(By.cssSelector(".inventory_item_name"));
        List<String> actualProductNames = productElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        // Sort product names in descending order
        List<String> sortedDescendingProductNames = new ArrayList<>(actualProductNames);
        sortedDescendingProductNames.sort(Collections.reverseOrder());

        // Assert sorting
        Assert.assertEquals(actualProductNames, sortedDescendingProductNames,
                "The product list is not sorted correctly by name (Z to A).");

        System.out.println("Test Case 15: Products are sorted correctly by name from Z to A.");
    }
    
    public void testCase16_SortByNameAtoZ() {
    	loginMethod.login("standard_user", "secret_sauce");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product_sort_container")));

        WebElement sortDropdown = driver.findElement(By.cssSelector(".product_sort_container"));
        sortDropdown.sendKeys("Name (A to Z)");

        List<WebElement> productElements = driver.findElements(By.cssSelector(".inventory_item_name"));
        List<String> actualProductNames = productElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> sortedAscendingProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(sortedAscendingProductNames);

        Assert.assertEquals(actualProductNames, sortedAscendingProductNames,
                "The product list is not sorted correctly by name (A to Z).");

        System.out.println("Test Case 16: Products are sorted correctly by name from A to Z.");
    }

    @Test
    public void testCase17_SortByPriceLowToHigh() throws IOException {
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

        // Select the sort option
        WebElement sortDropdown = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        sortDropdown.click();
        driver.findElement(By.xpath("//option[text()='Price (low to high)']")).click();

        // Extract prices
        List<WebElement> priceElements = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        List<Double> prices = priceElements.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());

        // Sort prices in ascending order
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);

        // Assert sorting
        Assert.assertEquals(prices, sortedPrices, "The product list is not sorted correctly by price (Low to High).");

        System.out.println("Test Case 17: Products are sorted correctly by price from low to high.");
    }

    @Test
    public void testCase18_SortByPriceHighToLow() throws IOException {
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

        // Select the sort option
        WebElement sortDropdown = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        sortDropdown.click();
        driver.findElement(By.xpath("//option[text()='Price (high to low)']")).click();

        // Extract prices
        List<WebElement> priceElements = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        List<Double> prices = priceElements.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());

        // Sort prices in descending order
        List<Double> sortedPrices = new ArrayList<>(prices);
        sortedPrices.sort(Collections.reverseOrder());

        // Assert sorting
        Assert.assertEquals(prices, sortedPrices, "The product list is not sorted correctly by price (High to Low).");

        System.out.println("Test Case 18: Products are sorted correctly by price from high to low.");
    }
}