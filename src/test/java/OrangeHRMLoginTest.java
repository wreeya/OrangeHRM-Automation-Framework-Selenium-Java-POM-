import basicsetup.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

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

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"),
                "Login failed or dashboard not loaded!");

        System.out.println("OrangeHRM login successful!");
    }

    @Test(priority = 2)
    public void testPIMAddEmployeeNavigation() {

        // Since Base opens browser fresh, we must login again
        OrangeHRMLoginPage loginPage = new OrangeHRMLoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();

        // Click PIM -> Add Employee
        PIMPage pimPage = new PIMPage(driver);
        pimPage.clickPIM();
        pimPage.clickAddEmployee();

        // Assertion: verify Add Employee page
        String addEmployeeUrl = driver.getCurrentUrl();
        Assert.assertTrue(addEmployeeUrl.contains("addEmployee"),
                "Failed to navigate to Add Employee page!");

        System.out.println("PIM -> Add Employee navigation successful!");
    }

    @Test(priority = 3)
    public void testAddEmployee() {

        // 1️⃣ Login
        OrangeHRMLoginPage loginPage = new OrangeHRMLoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();

        // 2️⃣ Go to PIM → Add Employee
        PIMPage pimPage = new PIMPage(driver);
        pimPage.clickPIM();
        pimPage.clickAddEmployee();

        // 3️⃣ Fill Add Employee form
        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.enterFirstName("ram");
        addEmployeePage.enterMiddleName("Kumar");
        addEmployeePage.enterLastName("krishnan");
        addEmployeePage.clearAndEnterEmployeeId("EMP" + System.currentTimeMillis());

        // 4️⃣ Save
        addEmployeePage.clickSave();

        // 5️⃣ Simple assertion: URL changed to employee profile page
        Assert.assertTrue(driver.getCurrentUrl().contains("viewPersonalDetails"),
                "Employee not created successfully!");

        System.out.println("Employee added successfully!");
    }

    @Test(priority = 3)
    public void testAddCancelEmployee() {

        // 1️⃣ Login
        OrangeHRMLoginPage loginPage = new OrangeHRMLoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();

        // 2️⃣ Go to PIM -> Add Employee
        PIMPage pimPage = new PIMPage(driver);
        pimPage.clickPIM();
        pimPage.clickAddEmployee();

        // 3️⃣ Fill Add Employee form
        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.enterFirstName("ram");
        addEmployeePage.enterMiddleName("Kumar");
        addEmployeePage.enterLastName("krishnan");
        addEmployeePage.clearAndEnterEmployeeId("EMP" + System.currentTimeMillis());

        // 4️⃣ Save
        addEmployeePage.clickSave();

        // 5️⃣ Assertion
        Assert.assertTrue(driver.getCurrentUrl().contains("viewPersonalDetails"),
                "Employee not created successfully!");
    }
}