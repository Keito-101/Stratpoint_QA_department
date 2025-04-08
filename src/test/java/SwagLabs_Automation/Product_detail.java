package SwagLabs_Automation;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
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
import io.github.bonigarcia.wdm.WebDriverManager;

public class Product_detail {
    private WebDriver driver;
    private LoginMethod loginMethod;

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver and maximize the browser window
        WebDriverManager.chromedriver().setup();
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

    /**
     * Test Case 23: Verify product detail page functionality.
     */
    @Test
    public void test_case23() throws IOException {
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

        // Verify successful login
        loginMethod.verifySuccessfulLogin();

        // Get all product items in the inventory list
        List<WebElement> productItems = driver.findElements(By.cssSelector(".inventory_item"));
        Assert.assertFalse(productItems.isEmpty(), "No products found in the inventory list.");

        // Navigate to the first product's detail page
        WebElement firstProduct = productItems.get(0); // First product in the list
        String productName = firstProduct.findElement(By.cssSelector(".inventory_item_name")).getText();
        firstProduct.findElement(By.cssSelector(".inventory_item_name")).click();

        // Verify that the product detail page is loaded
        WebElement productDetailContainer = driver.findElement(By.cssSelector(".inventory_details"));
        Assert.assertTrue(productDetailContainer.isDisplayed(), "Product detail page is not loaded.");

        // Verify required elements on the product detail page
        verifyProductDetailsPageElements();

        // Verify the product name matches
        WebElement productNameOnDetailPage = driver.findElement(By.cssSelector(".inventory_details_name"));
        Assert.assertEquals(productNameOnDetailPage.getText(), productName, "Product Name does not match.");

        System.out.println("Test_case 23 - All required details are displayed on the product detail page!");
    }

    /**
     * Helper method to verify all required elements on the product detail page.
     */
    private void verifyProductDetailsPageElements() {
        List<WebElement> requiredElements = driver.findElements(By.cssSelector(
                ".inventory_details_img, " + // Product Image
                        ".inventory_details_name, " + // Product Name
                        ".inventory_details_desc, " + // Product Description
                        ".inventory_details_price, " + // Product Price
                        ".btn_inventory" // Add to Cart Button
        ));

        String[] expectedDescriptions = {
                "Product Image",
                "Product Name",
                "Product Description",
                "Product Details (Price)",
                "'Add to Cart' Button"
        };

        Assert.assertEquals(requiredElements.size(), expectedDescriptions.length,
                "Mismatch between expected and actual number of elements.");

        for (int i = 0; i < requiredElements.size(); i++) {
            WebElement element = requiredElements.get(i);
            String description = expectedDescriptions[i];
            Assert.assertTrue(element.isDisplayed(), description + " is not displayed.");
        }
    }

    /**
     * Test Case 24: Validate inventory names.
     */
    @Test
    public void test_case24() throws IOException {
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

        // Validate inventory names
        List<WebElement> inventoryNames = driver.findElements(By.cssSelector(".inventory_item_name"));
        for (WebElement inventoryName : inventoryNames) {
            String name = inventoryName.getText().trim();
            System.out.println("Inventory Name: " + name);

            // Perform web element validation
            if (!inventoryName.isDisplayed()) {
                System.out.println("Element for '" + name + "' is not displayed on the page.");
            } else if (!inventoryName.isEnabled()) {
                System.out.println("Element for '" + name + "' is disabled and cannot be interacted with.");
            } else {
                System.out.println("Element for '" + name + "' is valid and displayed.");
            }
        }

        System.out.println("Test_case 24 proven!");
    }

    /**
     * Test Case 25: Add and remove an item from the cart.
     */
    @Test
    public void test_case25() throws IOException {
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

        // Add an item to the cart
        loginMethod.addItemToCart(Product1);

        // Wait for the "Remove" button to appear and verify it's displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement removeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-backpack")));
        Assert.assertTrue(removeButton.isDisplayed(), "Remove button is not displayed.");

        // Remove the item from the cart
        String removeItemId = "remove-sauce-labs-backpack";
        loginMethod.RemoveItemToCart(removeItemId);

        System.out.println("Test_case 25 proven!");
    }

    /**
     * Test Case 26: Verify cart count updates after adding and removing an item.
     */
    @Test
    public void test_case26() throws IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String Product2 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product2");
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }

        // Perform login using LoginMethod
        loginMethod.login(username, password);

        // Add an item to the cart
        loginMethod.addItemToCart(Product2);

        // Verify the cart count has increased by 1
        String updatedCartCount = getCartCount();
        Assert.assertEquals(updatedCartCount, "1", "Cart count should update to 1 after adding an item");

        // Remove the item from the cart
        String removeItemId = "remove-sauce-labs-bolt-t-shirt";
        loginMethod.RemoveItemToCart(removeItemId);

        // Verify the cart count reverts to its initial state
        String finalCartCount = getCartCount();
        Assert.assertEquals(finalCartCount, "", "Cart count should revert to empty or 0 after removing the item");

        System.out.println("Test Case 26 proven!");
    }

    /**
     * Test Case 27: Verify add/remove button text and cart count behavior.
     */
    @Test
    public void test_case27() throws IOException {
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

        // Locate the "Add to Cart" button for the first product
        WebElement addToCartButton = driver.findElement(By.id(Product1));

        // Verify initial state of the button
        Assert.assertEquals(addToCartButton.getText(), "Add to cart", "Initial button text should be 'Add to cart'");

        // Get the initial cart count (should be empty or 0)
        String initialCartCount = getCartCount();
        Assert.assertEquals(initialCartCount, "", "Initial cart count should be empty or 0");

        // Click the "Add to Cart" button
        loginMethod.addItemToCart(Product1);

        // Wait for the button text to change to "Remove"
        String removeItemId = "remove-sauce-labs-backpack";
        WebElement removeButton = driver.findElement(By.id(removeItemId));
        Assert.assertEquals(removeButton.getText(), "Remove", "Button text should change to 'Remove' after click");

        // Verify the cart count has increased by 1
        String updatedCartCount = getCartCount();
        Assert.assertEquals(updatedCartCount, "1", "Cart count should update to 1 after adding an item");

        // Click the "Remove" button
        loginMethod.RemoveItemToCart(removeItemId);

        // Wait for the button to revert to "Add to Cart"
        addToCartButton = driver.findElement(By.id(Product1));
        Assert.assertEquals(addToCartButton.getText(), "Add to cart",
                "Button text should revert to 'Add to cart' after removal");

        // Verify the cart count reverts to its initial state
        String finalCartCount = getCartCount();
        Assert.assertEquals(finalCartCount, "", "Cart count should revert to empty or 0 after removing the item");

        System.out.println("Test Case 27 proven!");
    }

    /**
     * Helper method to retrieve the current cart count.
     *
     * @return The cart count as a string.
     */
    private String getCartCount() {
        try {
            WebElement cartBadge = driver.findElement(By.xpath("//a[@class='shopping_cart_link']/span"));
            return cartBadge.isDisplayed() ? cartBadge.getText() : "";
        } catch (Exception e) {
            // If the cart badge is not present, return an empty string
            return "";
        }
    }
}