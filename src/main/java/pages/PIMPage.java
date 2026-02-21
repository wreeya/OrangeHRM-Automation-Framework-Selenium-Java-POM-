package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PIMPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By pimMenu = By.xpath("//span[text()='PIM']");
    private By addEmployeeMenu = By.xpath("//a[normalize-space()='Add Employee']");

    public PIMPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void clickPIM() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
    }

    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeMenu)).click();
    }

    public void openFirstEmployeeFromList() {
        By firstEmployee = By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell'])[2]");
        driver.findElement(firstEmployee).click();
    }
}