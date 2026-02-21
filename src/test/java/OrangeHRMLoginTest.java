package basicsetup;

import basicsetup.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import pages.OrangeHRMLoginPage;
import pages.PIMPage;
import pages.AddEmployeePage;

public class OrangeHRMLoginTest extends Base {

    @Test(priority = 1)
    public void testValidLogin() {
        OrangeHRMLoginPage loginPage = new OrangeHRMLoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();

        By dashboardHeader = By.xpath("//h6[normalize-space()='Dashboard']");
        WebElement header = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));

        Assert.assertTrue(header.isDisplayed(), "Login failed or dashboard not loaded!");
        System.out.println("✅ Login successful");
    }

    @Test(priority = 2)
    public void testPIMAddEmployeeNavigation() {
        OrangeHRMLoginPage loginPage = new OrangeHRMLoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();

        PIMPage pimPage = new PIMPage(driver);
        pimPage.clickPIM();
        pimPage.clickAddEmployee();

        By addEmployeeHeader = By.xpath("//h6[normalize-space()='Add Employee']");
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(addEmployeeHeader));

        Assert.assertTrue(driver.getPageSource().contains("Add Employee"),
                "Failed to navigate to Add Employee page!");
        System.out.println("✅ Navigated to Add Employee page");
    }

    @Test(priority = 3)
    public void testAddEmployee() {

        OrangeHRMLoginPage loginPage = new OrangeHRMLoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();

        PIMPage pimPage = new PIMPage(driver);
        pimPage.clickPIM();
        pimPage.clickAddEmployee();

        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.waitForAddEmployeePage();

        addEmployeePage.enterFirstName("Ram");
        addEmployeePage.enterMiddleName("Kumar");
        addEmployeePage.enterLastName("Krishnan");
        addEmployeePage.clearAndEnterEmployeeId("1234");

        addEmployeePage.clickSave();

        // ✅ Wait for redirect to Personal Details page
        addEmployeePage.waitForPersonalDetailsPage();

        Assert.assertTrue(driver.getCurrentUrl().contains("viewPersonalDetails"),
                "Did not redirect to Personal Details page!");

        System.out.println("✅ Employee added & redirected to Personal Details page!");
    }

    @Test(priority = 4)
    public void testAddCancelEmployee() {
        OrangeHRMLoginPage loginPage = new OrangeHRMLoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();

        PIMPage pimPage = new PIMPage(driver);
        pimPage.clickPIM();
        pimPage.clickAddEmployee();

        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.waitForAddEmployeePage();

        addEmployeePage.enterFirstName("Ram");
        addEmployeePage.enterMiddleName("Kumar");
        addEmployeePage.enterLastName("Krishnan");
        addEmployeePage.clearAndEnterEmployeeId("5678");

        addEmployeePage.clickCancel();

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("viewEmployeeList"));

        Assert.assertTrue(driver.getCurrentUrl().contains("viewEmployeeList"),
                "Cancel did not navigate back to Employee List page!");
        System.out.println("✅ Add Employee cancelled successfully");
    }


}