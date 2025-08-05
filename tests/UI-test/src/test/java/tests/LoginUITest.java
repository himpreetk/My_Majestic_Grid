package tests;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import pages.LoginPage;
import utils.ExtentReportManager;

public class LoginUITest extends BaseTest {

    LoginPage loginPage;
    ExtentReports extent;
    ExtentTest test;
    String baseUrl = "http://localhost:3000/login";

    @BeforeSuite
    public void setUpReport() {
        extent = ExtentReportManager.getInstance();
    }

    @BeforeMethod
    public void openApp() {
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        test = extent.createTest("Login with Valid Credentials");
        loginPage.login("manager@majestic.com", "123456");
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Expected to be on dashboard.");
    }

    @Test
    public void testInvalidPassword() {
        test = extent.createTest("Login with Valid Email and Invalid Password");
        loginPage.login("manager@majestic.com", "wrongpass");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Expected to stay on login page.");
    }

    @Test
    public void testInvalidEmail() {
        test = extent.createTest("Login with Invalid Email and Valid Password");
        loginPage.login("wrong@majestic.com", "123456");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    public void testBlankEmail() {
        test = extent.createTest("Login with Blank Email and Valid Password");
        loginPage.login("", "123456");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    public void testBlankPassword() {
        test = extent.createTest("Login with Valid Email and Blank Password");
        loginPage.login("manager@majestic.com", "");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    public void testBlankBoth() {
        test = extent.createTest("Login with Blank Email and Blank Password");
        loginPage.login("", "");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    public void testInvalidBoth() {
        test = extent.createTest("Login with Invalid Email and Invalid Password");
        loginPage.login("abc@xyz.com", "wrong");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @AfterMethod
    public void captureResults(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
            String path = takeScreenshot(result.getName());
            test.addScreenCaptureFromPath(path);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test Skipped: " + result.getThrowable());
        }
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }

    public String takeScreenshot(String testName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String dest = "test-output/screenshots/" + testName + "_" + timestamp + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.createDir(new File("test-output/screenshots"));
            FileHandler.copy(src, new File(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
}
