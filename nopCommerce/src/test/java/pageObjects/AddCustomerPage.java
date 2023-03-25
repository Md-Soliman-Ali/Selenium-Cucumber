package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

    public WebDriver driver;

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By lnkCustomers_menu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
    By lnkCustomers_menuItem = By.xpath("//a[@href=\"/Admin/Customer/List\"]//*[contains(text(),'Customers')]");
    By btnAddNew = By.xpath("//a[@href='/Admin/Customer/Create'][@class='btn btn-primary']");
    By txtEmail = By.xpath("//input[@id='Email']");
    By txtPassword = By.xpath("//input[@id='Password']");
    By txtCustomerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
    By lstItemAdministrators = By.xpath("//li[contains(text(),'Administrators')]");
    By lstItemRegistered = By.xpath("//li[contains(text(),'Registered')]");
    By lstItemGuests = By.xpath("//li[contains(text(),'Guests')]");
    By lstItemVendors = By.xpath("//li[contains(text(),'Vendors')]");
    By drpMgrOfVendor = By.xpath("//*[@id='VendorId']");
    By rdMaleGender = By.id("Gender_Male");
    By rdFeMaleGender = By.id("Gender_Female");
    By txtFirstName = By.xpath("//input[@id='FirstName']");
    By txtLastName = By.xpath("//input[@id='LastName']");
    By txtDob = By.xpath("//input[@id='DateOfBirth']");
    By txtCompanyName = By.xpath("//input[@id='Company']");
    By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");
    By btnSave = By.xpath("//button[@name='save']");
    By lnkLogout = By.xpath("//*[text()='Logout']");

    //Actions Methods
    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickOnCustomersMenu() {
        driver.findElement(lnkCustomers_menu).click();
    }

    public void clickOnCustomersMenuItem() {
        driver.findElement(lnkCustomers_menuItem).click();
    }

    public void clickOnAddNew() {
        driver.findElement(btnAddNew).click();
    }

    public void setEmail(String email) {
        driver.findElement(txtEmail).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(txtPassword).sendKeys(password);
    }

    public void setCustomerRoles(String role) throws InterruptedException {
        if (!role.equals("Vendors"))    //If role is vendors should not delete Register as per req.
        {
            driver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
        }

        driver.findElement(txtCustomerRoles).click();

        WebElement listItem;

        Thread.sleep(3000);

        if (role.equals("Administrators")) {
            listItem = driver.findElement(lstItemAdministrators);
        } else if (role.equals("Guests")) {
            listItem = driver.findElement(lstItemGuests);
        } else if (role.equals("Registered")) {
            listItem = driver.findElement(lstItemRegistered);
        } else if (role.equals("Vendors")) {
            listItem = driver.findElement(lstItemVendors);
        } else {
            listItem = driver.findElement(lstItemGuests);
        }

//        listItem.click();
//        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", listItem);
    }

    public void setManagerOfVendor(String value) {
        Select drp = new Select(driver.findElement(drpMgrOfVendor));
        drp.selectByVisibleText(value);
    }

    public void setGender(String gender) {
        if (gender.equals("Male")) {
            driver.findElement(rdMaleGender).click();
        } else if (gender.equals("Female")) {
            driver.findElement(rdFeMaleGender).click();
        } else {
            driver.findElement(rdMaleGender).click();   //Default
        }
    }

    public void setFirstName(String fName) {
        driver.findElement(txtFirstName).sendKeys(fName);
    }

    public void setLastName(String lName) {
        driver.findElement(txtLastName).sendKeys(lName);
    }

    public void setDob(String dob) {
        driver.findElement(txtDob).sendKeys(dob);
    }

    public void setCompanyName(String comName) {
        driver.findElement(txtCompanyName).sendKeys(comName);
    }

    public void setAdminContent(String content) {
        driver.findElement(txtAdminContent).sendKeys(content);
    }

    public void clickOnSave() {
        driver.findElement(btnSave).click();
    }

    public void clickOnLogout() {
        driver.findElement(lnkLogout).click();
    }
}
