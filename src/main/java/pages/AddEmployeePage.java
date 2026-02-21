package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators (based on your inspect)
    private By firstNameField = By.name("firstName");
    private By middleNameField = By.name("middleName");
    private By lastNameField  = By.name("lastName");

    private By employeeIdField = By.xpath("//label[text()='Employee Id']/following::input[1]");

    private By saveButton   = By.xpath("//button[@type='submit' and .//text()='Save']");
    private By cancelButton = By.xpath("//button[.//text()='Cancel']");

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
    }

    public void enterMiddleName(String middleName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(middleNameField)).sendKeys(middleName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lastName);
    }

    public void clearAndEnterEmployeeId(String empId) {
        var empIdInput = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeIdField));
        empIdInput.clear();
        empIdInput.sendKeys(empId);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
    }
}
