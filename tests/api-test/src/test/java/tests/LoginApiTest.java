package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ExtentReportManager;
import utils.TestData;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class LoginApiTest extends BaseTest {

    @Test(priority = 1)
    public void testValidLogin() {
        ExtentReportManager.createTest("Login with Valid Credentials");

        Response res = given()
            .contentType(ContentType.JSON)
            .body("{\"email\": \"" + TestData.VALID_EMAIL + "\", \"password\": \"" + TestData.VALID_PASSWORD + "\"}")
            .post("/login");

        assertEquals(res.statusCode(), 200);
        assertTrue(res.jsonPath().getString("token").length() > 0);
        ExtentReportManager.test.pass("Valid login passed");
    }

    @Test(priority = 2)
    public void testInvalidCredentials() {
        ExtentReportManager.createTest("Login with Invalid Credentials");

        Response res = given()
            .contentType(ContentType.JSON)
            .body("{\"email\": \"wrong@majestic.com\", \"password\": \"wrongpass\"}")
            .post("/login");

        assertEquals(res.statusCode(), 401);
        ExtentReportManager.test.pass("Invalid credentials correctly rejected");
    }

    @Test(priority = 3)
    public void testValidEmailInvalidPassword() {
        ExtentReportManager.createTest("Login with Valid Email & Invalid Password");

        Response res = given()
            .contentType(ContentType.JSON)
            .body("{\"email\": \"" + TestData.VALID_EMAIL + "\", \"password\": \"wrongpass\"}")
            .post("/login");

        assertEquals(res.statusCode(), 401);
        ExtentReportManager.test.pass("Invalid password correctly rejected");
    }

    @Test(priority = 4)
    public void testInvalidEmailValidPassword() {
        ExtentReportManager.createTest("Login with Invalid Email & Valid Password");

        Response res = given()
            .contentType(ContentType.JSON)
            .body("{\"email\": \"wrong@majestic.com\", \"password\": \"" + TestData.VALID_PASSWORD + "\"}")
            .post("/login");

        assertEquals(res.statusCode(), 401);
        ExtentReportManager.test.pass("Invalid email correctly rejected");
    }

    @Test(priority = 5)
    public void testBlankEmailValidPassword() {
        ExtentReportManager.createTest("Login with Blank Email & Valid Password");

        Response res = given()
            .contentType(ContentType.JSON)
            .body("{\"email\": \"\", \"password\": \"" + TestData.VALID_PASSWORD + "\"}")
            .post("/login");

        assertEquals(res.statusCode(), 400);
        ExtentReportManager.test.pass("Blank email correctly handled");
    }

    @Test(priority = 6)
    public void testValidEmailBlankPassword() {
        ExtentReportManager.createTest("Login with Valid Email & Blank Password");

        Response res = given()
            .contentType(ContentType.JSON)
            .body("{\"email\": \"" + TestData.VALID_EMAIL + "\", \"password\": \"\"}")
            .post("/login");

        assertEquals(res.statusCode(), 400);
        ExtentReportManager.test.pass("Blank password correctly handled");
    }

    @Test(priority = 7)
    public void testBlankEmailAndPassword() {
        ExtentReportManager.createTest("Login with Blank Email & Password");

        Response res = given()
            .contentType(ContentType.JSON)
            .body("{\"email\": \"\", \"password\": \"\"}")
            .post("/login");

        assertEquals(res.statusCode(), 400);
        ExtentReportManager.test.pass("Blank credentials correctly handled");
    }
}
