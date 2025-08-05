// base/BaseTest.java

package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import utils.ExtentReportManager;

public class BaseTest {

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = "http://localhost:5000/api";
        ExtentReportManager.initReport();
    }

    @AfterSuite
    public void tearDown() {
        ExtentReportManager.flushReport();
    }
}