package SwagLabs_Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Login_functionality {
    private WebDriver driver;
    private LoginMethod loginMethod;

    @BeforeMethod
    public void setUp() {
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
    public void testCase3_GeneralSetupVerification() {
        driver.get("https://www.saucedemo.com");
        System.out.println("If all test cases execute, this will show accordingly:");
        System.out.println("Test Case 3 proven!");
    }

    @Test
    public void testCase9_SuccessfulLogin() throws IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");

        // Validate credentials
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }

        // Perform login
        loginMethod.login(username, password);

        // Verify successful login
        if (driver.findElements(By.xpath("//div[@class='error-message-container error']")).isEmpty()) {
            System.out.println("Login successful!");
        } else {
            System.out.println(driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText());
        }
        System.out.println("Test Case 9 proven!");
    }

    @Test
    public void testCase10_LockedOutUser() throws IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "locked_out_user"
        String username = "locked_out_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");

        // Validate credentials
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }

        // Perform login
        loginMethod.login(username, password);

        // Verify error message
        String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
        System.out.println(errorMessage);
        System.out.println("Test Case 10 proven!");
    }

    @Test
    public void testCase4_WrongPassword() throws IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String username = "standard_user";
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", username, "Password");

        // Validate credentials
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: " + username);
        }

        // Perform login with incorrect password
        loginMethod.login(username, "thisisarandompassword");

        // Verify error message
        String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
        System.out.println(errorMessage);
        System.out.println("Test Case 4 proven!");
    }

    @Test
    public void testCase5_WrongUsername() throws IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", "standard_user", "Password");

        // Validate credentials
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: standard_user");
        }
        // Perform login with incorrect username
        loginMethod.login("nicosabrenicaArandomUsername", password);

        // Verify error message
        String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
        System.out.println(errorMessage);
        System.out.println("Test Case 5 proven!");
    }

    @Test
    public void testCase6_BlankUsername() throws IOException {
        // Define file path and sheet name
        String filePath = "C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx";
        String sheetName = "Sheet1";

        // Fetch the password for the username "standard_user"
        String password = ExcelUtils.fetchValueFromExcel(filePath, sheetName, "Username", "standard_user", "Password");

        // Validate credentials
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password not found for username: standard_user");
        }
        
        // Perform login with blank username
        loginMethod.login("", password);

        // Verify error message
        String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
        System.out.println(errorMessage);
        System.out.println("Test Case 6 proven!");
    }

    @Test
    public void testCase7_BlankPassword() throws IOException {
        // Fetch the username "standard_user"
        String username = "standard_user";

        // Perform login with blank password
        loginMethod.login(username, "");

        // Verify error message
        String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
        System.out.println(errorMessage);
        System.out.println("Test Case 7 proven!");
    }

    @Test
    public void testCase8_BlankFields() {

        // Perform login with blank fields
        loginMethod.login("", "");

        // Verify error message
        String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
        System.out.println(errorMessage);
        System.out.println("Test Case 8 proven!");
    }
}