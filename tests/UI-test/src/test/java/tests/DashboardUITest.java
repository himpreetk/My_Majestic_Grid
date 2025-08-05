package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ExtentReportManager;

import com.aventstack.extentreports.*;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

public class DashboardUITest extends BaseTest {

    DashboardPage dashboard;
    LoginPage login;
    ExtentReports extent;
    ExtentTest test;

    String baseUrl = "http://localhost:3000";

    @BeforeSuite
    public void setUpReport() {
        extent = ExtentReportManager.getInstance();
    }

    @BeforeMethod
    public void initTest() {
        driver.get(baseUrl + "/login");
        login = new LoginPage(driver);
        dashboard = new DashboardPage(driver);
        login.login("manager@majestic.com", "123456");
    }

    @Test
    public void testAddCarBrand() {
        test = extent.createTest("Add a Car Brand");
        dashboard.addBrand("Toyota");
        Assert.assertTrue(dashboard.isBrandPresent("Toyota"), "Brand should be added");
    }

    @Test
    public void testAddDuplicateCarBrand() {
        test = extent.createTest("Add Duplicate Car Brand");
        dashboard.addBrand("Toyota");
        dashboard.addBrand("Toyota");
        Assert.assertTrue(dashboard.getErrorMessage().contains("already exists"));
    }

    @Test
    public void testAddCarBrandInvalidCharacters() {
        test = extent.createTest("Add Car Brand with Invalid Characters");
        dashboard.addBrand("@@###");
        Assert.assertTrue(dashboard.getErrorMessage().contains("invalid"));
    }

    @Test
    public void testAddCarBrandBeyondLimit() {
        test = extent.createTest("Add Car Brand Beyond Limit");
        dashboard.addBrand("Brand1");
        dashboard.addBrand("Brand2");
        dashboard.addBrand("Brand3");
        dashboard.addBrand("Brand4");
        dashboard.addBrand("Brand5");
        dashboard.addBrand("Brand6"); // should fail
        Assert.assertTrue(dashboard.getErrorMessage().contains("limit"));
    }

    @Test
    public void testAddCarBrandBlank() {
        test = extent.createTest("Add Blank Car Brand");
        dashboard.addBrand("");
        Assert.assertTrue(dashboard.getErrorMessage().contains("required"));
    }

    @Test
    public void testEditCarBrandToNewName() {
        test = extent.createTest("Edit Car Brand to New Name");
        dashboard.addBrand("Hyundai");
        dashboard.editBrand("Hyundai", "Honda");
        Assert.assertTrue(dashboard.isBrandPresent("Honda"));
    }

    @Test
    public void testEditCarBrandToDuplicate() {
        test = extent.createTest("Edit Car Brand to Duplicate Name");
        dashboard.addBrand("Mazda");
        dashboard.addBrand("BMW");
        dashboard.editBrand("Mazda", "BMW");
        Assert.assertTrue(dashboard.getErrorMessage().contains("already exists"));
    }

    @Test
    public void testEditCarBrandToBlank() {
        test = extent.createTest("Edit Car Brand to Blank Name");
        dashboard.addBrand("Nissan");
        dashboard.editBrand("Nissan", "");
        Assert.assertTrue(dashboard.getErrorMessage().contains("required"));
    }

    @Test
    public void testDeleteCarBrand() {
        test = extent.createTest("Delete Car Brand");
        dashboard.addBrand("Kia");
        dashboard.deleteBrand("Kia");
        Assert.assertFalse(dashboard.isBrandPresent("Kia"), "Brand should be deleted");
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test Failed: " + result.getThrowable());
            String screenshotPath = takeScreenshot(result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test Skipped: " + result.getThrowable());
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }

    public String takeScreenshot(String testName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "test-output/screenshots/" + testName + ".png";
        try {
            FileHandler.copy(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
