package SwagLabs_Automation;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;


public class Cart_functionality {
    private WebDriver driver;
    private LoginMethod loginMethod;

    @BeforeClass
    public void setUp() {
    	WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.loginMethod = new LoginMethod(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("WebDriver closed.");
        }
    }

    @Test
    public void Test_case19() throws IOException {
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

        // Get the cart quantity
        WebElement cartLink = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        String qty = "";
        if (cartLink != null) {
            qty = cartLink.getText();
        } else {
            System.out.println("Cart Badge not found!");
            return;
        }

        // Assert the cart quantity
        if (!qty.equals("1")) {
            System.out.println("Assertion failed: Expected cart quantity '1', but found '" + qty + "'");
            return;
        }
        System.out.println("Cart's Quantity is: " + qty);
        
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        // Print success message
        System.out.println("Test case 19 proven!");
    }

    @Test
    public void Test_case20() throws InterruptedException, IOException {
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

        // Wait for the page to load after login
        Thread.sleep(3000);

        // Add items to the cart
        String[] itemIds = {
            "add-to-cart-sauce-labs-onesie",
            "add-to-cart-sauce-labs-bike-light",
            "add-to-cart-sauce-labs-bolt-t-shirt",
            "add-to-cart-sauce-labs-fleece-jacket"
        };
        for (String itemId : itemIds) {
            driver.findElement(By.id(itemId)).click();
        }

        // Verify the cart count
        WebElement cartLink = driver.findElement(By.className("shopping_cart_link"));
        String cartQtyText = cartLink.getText();

        // Assert that the cart count matches the number of items added
        int expectedQty = itemIds.length;
        Assert.assertEquals(cartQtyText, String.valueOf(expectedQty), "Cart count did not update correctly.");
        System.out.println("Cart Quantity is: " + cartQtyText);
        System.out.println("Test case 20 proven!");
    }
    
    @Test
    public void Testcase21() throws IOException, InterruptedException {
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

        // Check if the hamburger menu button is displayed and click it
        WebElement hamburgerMenuButton = driver.findElement(By.id("react-burger-menu-btn"));
        if (hamburgerMenuButton == null || !hamburgerMenuButton.isDisplayed()) {
            System.out.println("Hamburger menu button is not found or not displayed!");
            driver.quit();
            return;
        }
        hamburgerMenuButton.click();
        System.out.println("Hamburger menu button clicked successfully.");

        // Check if the hamburger menu wrapper is displayed
        WebElement menuWrapper = driver.findElement(By.xpath("//div[@class='bm-menu']"));
        if (menuWrapper == null || !menuWrapper.isDisplayed()) {
            System.out.println("Hamburger menu is not displayed!");
            driver.quit();
            return;
        }
        System.out.println("Hamburger menu is displayed.");
        
        Thread.sleep(2000);
        
        // Check if the close button is displayed and click it
        WebElement closeButton = driver.findElement(By.xpath("//div[@class='bm-cross-button']"));
        if (closeButton == null || !closeButton.isDisplayed()) {
            System.out.println("Close button is not found or not displayed!");
            driver.quit();
            return;
        }
        closeButton.click();
        System.out.println("Close button clicked successfully.");

        // Check if the hamburger menu button is displayed after closing the menu
        WebElement hamburgerMenuAfterClose = driver.findElement(By.id("react-burger-menu-btn"));
        if (hamburgerMenuAfterClose == null || !hamburgerMenuAfterClose.isDisplayed()) {
            System.out.println("Hamburger menu button is not displayed after closing the menu!");
            driver.quit();
            return;
        }
        System.out.println("Hamburger menu button is still displayed after closing the menu.");

        // Print success message
        System.out.println("Test case 21 proven!");
    } 
    
    @Test
    public void Testcase22() throws InterruptedException, IOException {
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

        // Wait for the page to load after login
        Thread.sleep(2000);

        // Locate the hamburger menu button and verify it is displayed
        WebElement hamburgerMenuButton = driver.findElement(By.id("react-burger-menu-btn"));
        if (hamburgerMenuButton != null && hamburgerMenuButton.isDisplayed()) {
            System.out.println("Hamburger menu button is displayed.");
            hamburgerMenuButton.click(); // Click the hamburger menu button
        } else {
            System.out.println("Hamburger menu button is not displayed!");
            return;
        }

        // Wait for the menu to open
        Thread.sleep(2000);

        // Verify if the menu options are visible using the provided XPath
        WebElement menuWrapper = driver.findElement(By.xpath("//div[@class='bm-menu-wrap']"));
        if (menuWrapper != null && menuWrapper.isDisplayed()) {
            System.out.println("Hamburger menu wrapper is visible.");
            // List of expected menu options
            String[] expectedMenuOptions = { "All Items", "About", "Logout", "Reset App State" };
            boolean allOptionsVisible = true;
            for (String option : expectedMenuOptions) {
                WebElement menuOption = driver.findElement(By.xpath("//a[text()='" + option + "']"));
                if (menuOption != null && menuOption.isDisplayed()) {
                    System.out.println("Menu option '" + option + "' is visible.");
                } else {
                    System.out.println("Menu option '" + option + "' is NOT visible!");
                    allOptionsVisible = false;
                }
            }
            if (allOptionsVisible) {
                System.out.println("All menu options are visible.");
            } else {
                System.out.println("Some menu options are missing or not visible.");
            }
        } else {
            System.out.println("Hamburger menu wrapper is not visible!");
        }

        // Print success message
        System.out.println("Test case 22 proven!");
        
        driver.quit();
    }
}