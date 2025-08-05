package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BaseTest;

public class DashboardPage extends BaseTest {

    public DashboardPage(WebDriver driver) {
        super();
    }

    By brandInput = By.xpath("//input[@placeholder='Enter brand name']");
    By addButton = By.xpath("//button[text()='Add']");
    By brandList = By.xpath("//ul/li");
    By errorText = By.className("error");

    public void addBrand(String brandName) {
        driver.findElement(brandInput).clear();
        driver.findElement(brandInput).sendKeys(brandName);
        driver.findElement(addButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorText).getText();
    }

    public void editBrand(String oldName, String newName) {
        By editBtn = By.xpath("//li[contains(text(),'" + oldName + "')]/button[text()='Edit']");
        By inputField = By.xpath("//li[contains(text(),'" + oldName + "')]//input");
        By saveBtn = By.xpath("//li[contains(text(),'" + oldName + "')]/button[text()='Save']");

        driver.findElement(editBtn).click();
        driver.findElement(inputField).clear();
        driver.findElement(inputField).sendKeys(newName);
        driver.findElement(saveBtn).click();
    }

    public void deleteBrand(String brandName) {
        By deleteBtn = By.xpath("//li[contains(text(),'" + brandName + "')]/button[text()='Delete']");
        driver.findElement(deleteBtn).click();
    }

    public boolean isBrandPresent(String brandName) {
        return driver.findElements(By.xpath("//li[contains(text(),'" + brandName + "')]")).size() > 0;
    }
}
