package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Steps extends BaseClass {

    @Before
    public void setup() throws IOException {
        logger = logger.getLogger("nopCommerce");
        PropertyConfigurator.configure("log4j.properties");

        // Reading Config.Properties File
        configProperties = new Properties();
        FileInputStream configPropFile = new FileInputStream("config.properties");
        configProperties.load(configPropFile);

        String browser = configProperties.getProperty("browser");
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", configProperties.getProperty("chromePath"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", configProperties.getProperty("firefoxPath"));
           FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        }

        logger.info("********** Launching The Browser **********");
    }

    @Given("user launch chrome browser")
    public void user_launch_chrome_browser() {
        loginPage = new LoginPage(driver);
    }

    @When("user opens url {string}")
    public void user_opens_url(String url) {
        logger.info("********** Opening URL **********");
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("user enters email as {string} and password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) {
        logger.info("********** Proving User Info **********");
        loginPage.setUserName(email);
        loginPage.setPassword(password);
    }

    @When("click on login")
    public void click_on_login() throws InterruptedException {
        loginPage.clickLogin();
        Thread.sleep(1000);
    }

    @Then("page title should be {string}")
    public void page_title_should_be(String title) throws InterruptedException {
        if (driver.getPageSource().contains("Login was unsuccessful")) {
            driver.close();
            logger.info("********** Login Failed **********");
            Assert.assertTrue(false);
        } else {
            logger.info("********** Login SuccessFull **********");
            Assert.assertEquals(title, driver.getTitle());
        }
        Thread.sleep(1000);
    }

    @When("user click on log out link")
    public void user_click_on_log_out_link() throws InterruptedException {
        logger.info("********** Logout from Application **********");
        loginPage.clickLogout();
        Thread.sleep(1000);
    }

    @Then("close browser")
    public void close_browser() {
        logger.info("********** Closing Application **********");
        driver.quit();
    }

    // Add New Customer Step Definitions

    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
        addCustomerPage = new AddCustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration", addCustomerPage.getPageTitle());
    }

    @When("User click on customers menu")
    public void user_click_on_customers_menu() throws InterruptedException {
        Thread.sleep(1000);
        addCustomerPage.clickOnCustomersMenu();
    }

    @When("click on customers menu item")
    public void click_on_customers_menu_item() throws InterruptedException {
        Thread.sleep(1000);
        addCustomerPage.clickOnCustomersMenuItem();
    }

    @When("click on add new button")
    public void click_on_add_new_button() throws InterruptedException {
        addCustomerPage.clickOnAddNew();
        Thread.sleep(1000);
    }

    @Then("User can view add new customer page")
    public void user_can_view_add_new_customer_page() {
        Assert.assertEquals("Add a new customer / nopCommerce administration", addCustomerPage.getPageTitle());
    }

    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {
        String email = randomString() + "@gmail.com";
        addCustomerPage.setEmail(email);
        addCustomerPage.setPassword("test123");
        addCustomerPage.setFirstName("Md. Soliman");
        addCustomerPage.setLastName("Ali");
        addCustomerPage.setGender("Male");
        addCustomerPage.setDob("2/03/1996"); // Format: D/MM/YYY
        addCustomerPage.setCompanyName("SQA");

        // Registered - Default
        // The customer cannot be in both 'Guests' and 'Registered' customer roles
        // Add the customer to 'Guests' or 'Registered' customer role

        addCustomerPage.setCustomerRoles("Guest");
        addCustomerPage.setManagerOfVendor("Vendor 2");
        addCustomerPage.setAdminContent("Testing Purpose");
        Thread.sleep(2000);
    }

    @When("click on Save button")
    public void click_on_save_button() throws InterruptedException {
        addCustomerPage.clickOnSave();
        Thread.sleep(1000);
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String string) {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
    }

    // Steps for searching a customer using email id

    @When("Enter Customer Email")
    public void enter_customer_email() {
        searchCustomer = new SearchCustomerPage(driver);
        searchCustomer.setEmail("victoria_victoria@nopCommerce.com");
    }

    @When("Click on Search button")
    public void click_on_search_button() throws InterruptedException {
        searchCustomer.clickSearch();
        Thread.sleep(2000);
    }

    @Then("User should found Email in the search table")
    public void user_should_found_email_in_the_search_table() {
        boolean status = searchCustomer.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
        Assert.assertEquals(true, status);
    }

    // Steps for searching a customer using first name & last name

    @When("Enter Customer FirstName")
    public void enter_customer_first_name() {
        searchCustomer = new SearchCustomerPage(driver);
        searchCustomer.setFirstName("victoria");
    }

    @When("Enter Customer LastName")
    public void enter_customer_last_name() {
        searchCustomer.setLastName("Terces");
    }

    @Then("User should found Name in the search table")
    public void user_should_found_name_in_the_search_table() {
        boolean status = searchCustomer.searchCustomerByName("Victoria Terces");
        Assert.assertEquals(true, status);
    }
}
