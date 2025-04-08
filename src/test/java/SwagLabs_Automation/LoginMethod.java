package SwagLabs_Automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginMethod {
    protected WebDriver driver;

    // Constructor to initialize the WebDriver
    public LoginMethod(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Reusable method to perform login
     *
     * @param username The username to log in with
     * @param password The password to log in with
     */
    public void login(String username, String password) {
        driver.get("https://www.saucedemo.com");
        // Enter username
        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.clear();
        usernameField.sendKeys(username);

        // Enter password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys(password);

        // Click the login button
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        System.out.println("Attempted login with Username: " + username + ", Password: " + password);
    }

    /**
     * Method to verify successful login by checking the presence of a product listing
     */
    public void verifySuccessfulLogin() {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        WebElement productListing = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_list']")));
        Assert.assertTrue(productListing.isDisplayed(), "Product listing is not displayed after login.");
        System.out.println("Login was successful.");
    }

    /**
     * Method to navigate to the shopping cart
     */
    public void navigateToShoppingCart() {
        WebElement shoppingCartLink = driver.findElement(By.className("shopping_cart_link"));
        shoppingCartLink.click();
        System.out.println("Navigated to the shopping cart.");
    }

    /**
     * Method to add an item to the cart by its ID
     *
     * @param itemId The ID of the item to add to the cart
     */
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

    /**
     * Method to fill out the checkout form
     *
     * @param firstName   The first name to enter
     * @param lastName    The last name to enter
     * @param postalCode  The postal code to enter
     */
    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        WebElement firstNameField = driver.findElement(By.id("first-name"));
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = driver.findElement(By.id("last-name"));
        lastNameField.sendKeys(lastName);

        WebElement postalCodeField = driver.findElement(By.id("postal-code"));
        postalCodeField.sendKeys(postalCode);

        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        System.out.println("Filled out the checkout form and proceeded to the next step.");
    }
}