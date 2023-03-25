package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.time.Duration;
import java.util.List;

public class SearchCustomerPage {

    public WebDriver driver;
    WaitHelper waitHelper;

    public SearchCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    @FindBy(how = How.XPATH, using = "//div[@class='search-text']")
    @CacheLookup
    WebElement SearchBtnHover;

    @FindBy(how = How.ID, using = "SearchEmail")
    @CacheLookup
    WebElement txtEmail;

    @FindBy(how = How.ID, using = "SearchFirstName")
    @CacheLookup
    WebElement txtFirstName;

    @FindBy(how = How.ID, using = "SearchLastName")
    @CacheLookup
    WebElement txtLastName;

    @FindBy(how = How.ID, using = "search-customers")
    @CacheLookup
    WebElement btnSearch;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
    WebElement table;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
    @CacheLookup
    List<WebElement> tableRows;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
    @CacheLookup
    List<WebElement> tableColumns;


    public void clickSearchBtnHover() {
        waitHelper.WaitForElement(txtEmail, Duration.ofSeconds(30));
        SearchBtnHover.click();
    }

    public void setEmail(String email) {
        waitHelper.WaitForElement(txtEmail, Duration.ofSeconds(30));
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void setFirstName(String fName) {
        waitHelper.WaitForElement(txtFirstName, Duration.ofSeconds(30));
        txtFirstName.clear();
        txtFirstName.sendKeys(fName);
    }

    public void setLastName(String lName) {
        waitHelper.WaitForElement(txtLastName, Duration.ofSeconds(30));
        txtLastName.clear();
        txtLastName.sendKeys(lName);
    }

    public void clickSearch() {
        btnSearch.click();
    }

    public int getNoOfRows() {
        return (tableRows.size());
    }

    public int getNoOfColumns() {
        return (tableColumns.size());
    }

    public boolean searchCustomerByEmail(String email) {
        boolean flag = false;
        for (int i = 1; i <= getNoOfRows(); i++) {
            String emailID = driver.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[2]")).getText();
            System.out.println(emailID);
            if (emailID.equalsIgnoreCase(email)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

//    public boolean searchCustomerByName(String name) {
//        boolean flag = false;
//        for (int i = 1; i <= getNoOfRows(); i++) {
//            String Name = driver.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[3]")).getText();
//            String names[] = name.split(" ");
//            if (names[0].equals("Victoria") && names[1].equals("Terces")) {
//                flag = true;
//                break;
//            }
//        }
//        return flag;
//    }

    public boolean searchCustomerByName(String name) {
        boolean flag = false;
        for (int i = 1; i <= getNoOfRows(); i++) {
            String Name = driver.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[3]")).getText();
            System.out.println(Name);
            if (Name.equalsIgnoreCase(name)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
