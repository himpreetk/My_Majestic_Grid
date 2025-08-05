package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.*;
import utils.ExtentReportManager;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class DashboardApiTest extends BaseTest {
    private String token;
    private String testBrandId;

    @BeforeClass
    public void authenticate() {
        token = given().contentType(ContentType.JSON)
                .body("{\"email\":\"manager@majestic.com\",\"password\":\"123456\"}")
                .post("/login")
                .jsonPath().getString("token");
    }

    @Test(priority = 1)
    public void testAddValidBrand() {
        ExtentReportManager.createTest("Add Valid Brand Test");

        Response res = given().header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Cittron\"}")
                .post("/brands");

        assertEquals(res.statusCode(), 201);
        testBrandId = res.jsonPath().getString("_id");
        ExtentReportManager.test.pass("Brand added successfully");
    }

    @Test(priority = 2)
    public void testDuplicateBrand() {
        ExtentReportManager.createTest("Add Duplicate Brand Test");

        Response res = given().header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Cittron\"}")
                .post("/brands");

        assertEquals(res.statusCode(), 400);
        ExtentReportManager.test.pass("Duplicate brand rejected");
    }

    @Test(priority = 3)
    public void testAddInvalidCharactersBrand() {
        ExtentReportManager.createTest("Add Brand with Invalid Characters");

        Response res = given().header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{\"name\":\"@#$$\"}")
                .post("/brands");

        assertEquals(res.statusCode(), 400);
        ExtentReportManager.test.pass("Invalid characters brand rejected");
    }

    @Test(priority = 4)
    public void testAddBlankBrand() {
        ExtentReportManager.createTest("Add Blank Brand Name");

        Response res = given().header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{\"name\":\"\"}")
                .post("/brands");

        assertEquals(res.statusCode(), 400);
        ExtentReportManager.test.pass("Blank brand name rejected");
    }

    @Test(priority = 5)
    public void testAddMoreThanFiveBrands() {
        ExtentReportManager.createTest("Add Brand after Limit");

        // Add 4 brands before the last (Mazda is already added)
        String[] brands = {"Brand1", "Brand2", "Brand3", "Brand4"};
        for (String b : brands) {
            given().header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body("{\"name\":\"" + b + "\"}")
                    .post("/brands");
        }

        // 6th brand should fail
        Response res = given().header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Brand6\"}")
                .post("/brands");

        assertEquals(res.statusCode(), 400);
        ExtentReportManager.test.pass("Limit exceeded brand rejected");
    }

    @Test(priority = 6)
    public void testEditBrandWithNewName() {
        ExtentReportManager.createTest("Edit Brand with New Name");

        Response res = given().header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{\"name\":\"UpdatedMazda\"}")
                .put("/brands/" + testBrandId);

        assertEquals(res.statusCode(), 200);
        ExtentReportManager.test.pass("Brand updated successfully");
    }

    @Test(priority = 7)
    public void testEditBrandWithDuplicateName() {
        ExtentReportManager.createTest("Edit Brand to Existing Name");

        // Assume "Brand1" already exists
        Response res = given().header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Brand1\"}")
                .put("/brands/" + testBrandId);

        assertEquals(res.statusCode(), 400);
        ExtentReportManager.test.pass("Duplicate name update rejected");
    }

    @Test(priority = 8)
    public void testEditBrandWithBlankName() {
        ExtentReportManager.createTest("Edit Brand with Blank Name");

        Response res = given().header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{\"name\":\"\"}")
                .put("/brands/" + testBrandId);

        assertEquals(res.statusCode(), 400);
        ExtentReportManager.test.pass("Blank name update rejected");
    }

    @Test(priority = 9)
    public void testDeleteBrand() {
        ExtentReportManager.createTest("Delete Brand");

        Response res = given().header("Authorization", "Bearer " + token)
                .delete("/brands/" + testBrandId);

        assertEquals(res.statusCode(), 200);
        ExtentReportManager.test.pass("Brand deleted successfully");
    }
}
