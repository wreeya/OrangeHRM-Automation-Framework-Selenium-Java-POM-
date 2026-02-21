package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrangeHRMLoginPage {

    private WebDriver driver;

    // Locators for OrangeHRM Login Page
    private By userNameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");

    // Constructor
    public OrangeHRMLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterUsername(String username){
        driver.findElement(userNameField).sendKeys(username);
    }

    public void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public String errorMessageDisplay(){
        return driver.findElement(errorMessage).getText();
    }
}