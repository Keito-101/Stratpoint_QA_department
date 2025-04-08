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


public class Checkout_Process_Verification {
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
    /**
     * Test case 37: Verify users can access the user information page.
     */
    @Test
    public void Testcase37() throws IOException {
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

        // Perform login
        loginMethod.login(username, password);

        // Add an item to the cart
        loginMethod.addItemToCart(Product1);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Fetch user details from Excel
        String firstName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "FirstName");
        String lastName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "LastName");
        String postalCode = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "PostalCode");

        // Fill out the checkout form
        loginMethod.fillCheckoutForm(firstName, lastName, postalCode);

        // Verify the checkout summary container is displayed
        WebElement checkoutSummaryContainer = driver.findElement(By.xpath("//div[@id='checkout_summary_container']"));
        Assert.assertTrue(checkoutSummaryContainer.isDisplayed(), "Checkout summary container is not displayed.");

        System.out.println("Test case 37: Users are able to access the user information page.");
        
        
    }

    /**
     * Test case 38: Verify all necessary details are displayed on the checkout info page.
     */
    @Test
    public void Testcase38() throws IOException {
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

        // Perform login
        loginMethod.login(username, password);

        // Add an item to the cart
        loginMethod.addItemToCart(Product2);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Verify that all necessary elements are displayed on the checkout info page
        WebElement firstNameField = driver.findElement(By.id("first-name"));
        WebElement lastNameField = driver.findElement(By.id("last-name"));
        WebElement postalCodeField = driver.findElement(By.id("postal-code"));
        WebElement cancelButton = driver.findElement(By.id("cancel"));
        WebElement continueButton = driver.findElement(By.id("continue"));

        Assert.assertTrue(firstNameField.isDisplayed(), "First name field is not displayed!");
        Assert.assertTrue(lastNameField.isDisplayed(), "Last name field is not displayed!");
        Assert.assertTrue(postalCodeField.isDisplayed(), "Postal code field is not displayed!");
        Assert.assertTrue(cancelButton.isDisplayed(), "Cancel button is not displayed!");
        Assert.assertTrue(continueButton.isDisplayed(), "Continue button is not displayed!");
        
        driver.findElement(By.id("cancel")).click();
        
        loginMethod.navigateToShoppingCart();
        
        loginMethod.RemoveItemToCart("remove-sauce-labs-bolt-t-shirt");

        System.out.println("Test case 38: All necessary details are displayed on the your information page.");
    }
    /**
     * Test case 39: Verify clicking the cancel button redirects the user to the cart page.
     */
    @Test
    public void Testcase39() throws IOException {
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

        // Perform login
        loginMethod.login(username, password);

        // Add an item to the cart
        loginMethod.addItemToCart(Product3);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Click the cancel button
        driver.findElement(By.id("cancel")).click();

        // Verify redirection to the cart page
        WebElement cartPage = driver.findElement(By.xpath("//div[@class='cart_list']"));
        Assert.assertTrue(cartPage.isDisplayed(), "User was not redirected to the cart page after clicking cancel.");
        
        loginMethod.navigateToShoppingCart();
        
        loginMethod.RemoveItemToCart("remove-sauce-labs-bike-light");

        System.out.println("Test case 39: Redirects the user to the cart page when clicking the cancel button.");
        
    }

    /**
     * Test case 40: Verify users cannot proceed without filling all required fields.
     */
    @Test
    public void Testcase40() throws IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String Product4 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product4");

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }

        // Perform login
        loginMethod.login(username, password);

        // Add an item to the cart
        loginMethod.addItemToCart(Product4);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Leave all fields blank and click continue
        driver.findElement(By.id("continue")).click();

        // Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed when required fields are blank.");
        
        driver.findElement(By.id("cancel")).click();
        
        loginMethod.navigateToShoppingCart();
        
        loginMethod.RemoveItemToCart("remove-sauce-labs-fleece-jacket");

        System.out.println("Test case 40: Users are unable to proceed without filling all required fields.");

    }
    /**
     * Test case 44: Verify users cannot proceed when only the first name is filled.
     * @throws IOException 
     */
    @Test
    public void Testcase41() throws InterruptedException, IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String Product5 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product5");

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }

        // Perform login
        loginMethod.login(username, password);

        // Add an item to the cart
        loginMethod.addItemToCart(Product5);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Fill in only the first name
        String firstName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "FirstName");
        loginMethod.fillCheckoutForm(firstName, "", "");

        // Click continue
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        // Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed when only the first name is filled.");
        
        driver.findElement(By.id("cancel")).click();
        
        loginMethod.navigateToShoppingCart();
        
        loginMethod.RemoveItemToCart("remove-test.allthethings()-t-shirt-(red)");

        System.out.println("Test case 41: Users are unable to proceed when only the first name is filled.");

    }

    /**
     * Test case 45: Verify users cannot proceed when only the last name is filled.
     * @throws IOException 
     */
    @Test
    public void Testcase42() throws InterruptedException, IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String Product6 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product6");

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }

        // Perform login
        loginMethod.login(username, password);

        // Add an item to the cart
        loginMethod.addItemToCart(Product6);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Fill in only the last name
        driver.findElement(By.id("first-name")).sendKeys("");
        driver.findElement(By.id("last-name")).sendKeys("Abrenica");
        driver.findElement(By.id("postal-code")).sendKeys("");

        // Click continue
        driver.findElement(By.id("continue")).click();

        // Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed when only the last name is filled.");
        driver.findElement(By.id("cancel")).click();
        System.out.println("Test case 42: Users are unable to proceed when only the last name is filled.");
        loginMethod.navigateToShoppingCart();
        loginMethod.RemoveItemToCart("remove-sauce-labs-onesie");
    }

    /**
     * Test case 46: Verify users cannot proceed when only the postal code is filled.
     * @throws IOException 
     */
    @Test
    public void Testcase43() throws InterruptedException, IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String Product6 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product6");

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }

        // Perform login
        loginMethod.login(username, password);

        // Add an item to the cart
        loginMethod.addItemToCart(Product6);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Fill in only the postal code
        driver.findElement(By.id("first-name")).sendKeys("");
        driver.findElement(By.id("last-name")).sendKeys("");
        driver.findElement(By.id("postal-code")).sendKeys("1016");

        // Click continue
        driver.findElement(By.id("continue")).click();

        // Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed when only the postal code is filled.");
        
        driver.findElement(By.id("cancel")).click();
        
        loginMethod.navigateToShoppingCart();
        
        loginMethod.RemoveItemToCart("remove-sauce-labs-onesie");
        
        System.out.println("Test case 43: Users are unable to proceed when only the postal code is filled.");
        
       
    }
    
    @Test
    public void Testcase44() throws IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password and product details for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String product2 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product2");

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }

        // Perform login
        loginMethod.login(username, password);

        // Add an item to the cart
        loginMethod.addItemToCart(product2);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Fetch user details from Excel
        String firstName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "FirstName");

        // Fill in only the first name
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys("");
        driver.findElement(By.id("postal-code")).sendKeys("");

        // Click continue
        driver.findElement(By.id("continue")).click();

        // Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed when only the first name is entered.");
        
        driver.findElement(By.id("cancel")).click();
        
        loginMethod.navigateToShoppingCart();
        
        loginMethod.RemoveItemToCart("remove-sauce-labs-bolt-t-shirt");

        System.out.println("Test case 44: Users are unable to proceed when only the First Name is entered.");
        
    }
    
    @Test
    public void Testcase45() throws IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password and product details for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String Product2 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product2");

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }

        // Perform login
        loginMethod.login(username, password);

        // Add an item to the cart
        loginMethod.addItemToCart(Product2);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Fetch user details from Excel
        String lastName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "LastName");

        // Fill in only the last name
        driver.findElement(By.id("first-name")).sendKeys("");
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys("");

        // Click continue
        driver.findElement(By.id("continue")).click();

        // Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed when only the last name is entered.");
        driver.findElement(By.id("cancel")).click();
        System.out.println("Test case 45: Users are unable to proceed when only the Last Name is entered.");
        loginMethod.navigateToShoppingCart();
        loginMethod.RemoveItemToCart("remove-sauce-labs-bolt-t-shirt");
    }
    
    @Test
    public void Testcase46() throws IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password and product details for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String Product3 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product3");

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }

        // Perform login
        loginMethod.login(username, password);

        // Add an item to the cart
        loginMethod.addItemToCart(Product3);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Fetch user details from Excel
        String postalCode = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "PostalCode");

        // Fill in only the postal code
        driver.findElement(By.id("first-name")).sendKeys("");
        driver.findElement(By.id("last-name")).sendKeys("");
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);

        // Click continue
        driver.findElement(By.id("continue")).click();

        // Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed when only the postal code is entered.");
        driver.findElement(By.id("cancel")).click();
        loginMethod.navigateToShoppingCart();
        loginMethod.RemoveItemToCart("remove-sauce-labs-bike-light");
        System.out.println("Test case 46: Users are unable to proceed when only the Zip/Postal Code is entered.");
        
    }

    /**
     * Test case 47: Verify users can proceed when all required fields are filled.
     */
    @Test
    public void Testcase47() throws IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password and product details for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");
        String product4 = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Product4");

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }

        // Perform login
        loginMethod.login(username, password);

        // Add an item to the cart
        loginMethod.addItemToCart(product4);

        // Navigate to the shopping cart
        loginMethod.navigateToShoppingCart();

        // Proceed to checkout
        loginMethod.proceedToCheckout();

        // Fetch user details from Excel
        String firstName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "FirstName");
        String lastName = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "LastName");
        String postalCode = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "PostalCode");

        // Fill out all required fields
        loginMethod.fillCheckoutForm(firstName, lastName, postalCode);

        // Verify the checkout summary container is displayed
        WebElement checkoutSummaryContainer = driver.findElement(By.xpath("//div[@id='checkout_summary_container']"));
        Assert.assertTrue(checkoutSummaryContainer.isDisplayed(), "Checkout summary container is not displayed after filling all fields.");
        driver.findElement(By.id("cancel")).click();
        System.out.println("Test case 47: Users are able to proceed when all required fields are filled.");
        loginMethod.navigateToShoppingCart();
        loginMethod.RemoveItemToCart("remove-sauce-labs-fleece-jacket");
    }
}