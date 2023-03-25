import Pages.Login;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class StepDefinitions {

    public WebDriver driver;
    WebDriverWait wait;

    Login login;

    @Given("user visits e-commerce website")
    public void user_visits_e_commerce_website() {
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions ops = new FirefoxOptions();
        ops.addArguments("--headed");
        driver = new FirefoxDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com");
    }

    @When("user enters valid {string} and {string}")
    public void user_enters_valid_and(String email, String password) throws InterruptedException {
        login = new Login(driver);
        login.doLogin(email, password);
    }

    @Then("user can logged in successfully")
    public void user_can_logged_in_successfully() {
        wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Test User')]")));

        WebElement lblUserName = driver.findElement(By.xpath("//span[contains(text(),'Test User')]"));
        Assert.assertEquals(lblUserName.getText(), "Test User");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
