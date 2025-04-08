package SwagLabs_Automation;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Order_Completion {
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

    private void completeOrderAndVerifyThankYouMessage() {
        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();

        // Verify the thank-you message is displayed
        WebElement thankYouMessage = driver.findElement(By.xpath("//h2[normalize-space()='Thank you for your order!']"));
        Assert.assertTrue(thankYouMessage.isDisplayed(), "Thank you message is not displayed.");
        System.out.println("Order completed successfully.");
    }

    /**
     * Test case 5354: Verify users can complete an order.
     */
    @Test
    public void Testcase5354() throws IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch test data from Excel
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String firstName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "FirstName");
        String lastName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "LastName");
        String postalCode = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "PostalCode");
        String Product1 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product1");

        // Perform login
        loginMethod.login(username, password);

        // Add an item to the cart
        loginMethod.addItemToCart(Product1);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Fill out the checkout form
        loginMethod.fillCheckoutForm(firstName, lastName, postalCode);

        // Complete the order and verify the thank-you message
        completeOrderAndVerifyThankYouMessage();

        System.out.println("Test case 5354: Users are able to complete an order.");
    }

    /**
     * Test case 5556: Verify users can complete an order with multiple items.
     */
    @Test
    public void Testcase5556() throws IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch test data from Excel
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String firstName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "FirstName");
        String lastName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "LastName");
        String postalCode = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "PostalCode");
        String Product1 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product1");
        String Product2 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product2");

        // Perform login
        loginMethod.login(username, password);

        // Add multiple items to the cart
        loginMethod.addItemToCart(Product1);
        loginMethod.addItemToCart(Product2);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Fill out the checkout form
        loginMethod.fillCheckoutForm(firstName, lastName, postalCode);

        // Complete the order and verify the thank-you message
        completeOrderAndVerifyThankYouMessage();

        System.out.println("Test case 5556: Users are able to complete an order with multiple items.");
    }
}